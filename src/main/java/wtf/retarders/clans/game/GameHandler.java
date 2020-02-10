package wtf.retarders.clans.game;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import wtf.retarders.clans.ClansConstants;
import wtf.retarders.clans.ClansPlugin;
import wtf.retarders.clans.clan.ClanHandler;
import wtf.retarders.clans.clan.types.PlayerClan;
import wtf.retarders.clans.game.map.MapHandler;
import wtf.retarders.clans.handler.IHandler;
import wtf.retarders.clans.profile.Profile;
import wtf.retarders.clans.profile.ProfileHandler;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class GameHandler implements IHandler {

    private BukkitRunnable task;

    private ClanHandler clanHandler = ClansPlugin.getPlugin().getHandlerManager().findHandler(ClanHandler.class);
    private ProfileHandler profileHandler = ClansPlugin.getPlugin().getHandlerManager().findHandler(ProfileHandler.class);
    private MapHandler mapHandler = ClansPlugin.getPlugin().getHandlerManager().findHandler(MapHandler.class);

    public void startGame() {
        if (this.task != null) {
            // cancel task if it already existed
            this.task.cancel();
        }

        if(this.mapHandler.getCurrentMap() == null) {
            this.mapHandler.setCurrentMap(this.mapHandler.getMaps().get(new Random().nextInt(this.mapHandler.getMaps().size())));
        }

        this.task = new BukkitRunnable() {
            int countdown = 60;

            @Override
            public void run() {

                if (countdown > 0 && (countdown < 6 || countdown == 10 || countdown == 30 || countdown == 45 || countdown == 60)) {
                    Bukkit.broadcastMessage(ClansConstants.GAME_STARTING.replace("%time%", countdown + ""));
                } else if (countdown == 0) {

                    List<PlayerClan> playerClans = clanHandler.getClans().stream().filter(clan -> clan instanceof PlayerClan).map(clan -> (PlayerClan) clan).collect(Collectors.toList());
                    Stream<Profile> profileStream = profileHandler.getProfiles().stream();

                    // add all profiles without a clan to a non-full random clan.
                    profileStream.filter(profile -> profile.getCurrentClan() == null)
                            .forEach(profile -> Objects.requireNonNull(playerClans.stream()
                                    .filter(clan -> clan.getMembers().size() < ClansConstants.MAX_PLAYERS_PER_CLAN).findAny().orElse(null)).addPlayer(profile));


                    // set dtr of all clans
                    playerClans.forEach(clan -> clan.setDtr(ClansConstants.DTR_PER_PLAYER * clan.getMembers().size()));

                    // teleport player to the spawn
                    profileStream.forEach(profile -> Bukkit.getPlayer(profile.getUuid()).teleport(mapHandler.getCurrentMap().getSpawnByClan(profile.getCurrentClan()).getLocation()));

                    // cancel task so it does not keep running.
                    this.cancel();
                }

                countdown--;
            }
        };

        // actually run the task
        this.task.runTaskTimerAsynchronously(ClansPlugin.getPlugin(), 20L, 20L);
    }

    public void handleDeath(Player killer, Player died) {
        Profile diedProfile = this.profileHandler.findProfile(died.getUniqueId());
        PlayerClan clan = diedProfile.getCurrentClan();

        // reduce dtr
        clan.setDtr(clan.getDtr() - ClansConstants.DTR_REDUCE);
    }
}