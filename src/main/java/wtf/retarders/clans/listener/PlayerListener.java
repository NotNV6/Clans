package wtf.retarders.clans.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import wtf.retarders.clans.ClansConstants;
import wtf.retarders.clans.ClansPlugin;
import wtf.retarders.clans.game.GameHandler;
import wtf.retarders.clans.loadout.impl.LobbyLoadout;
import wtf.retarders.clans.profile.Profile;
import wtf.retarders.clans.profile.ProfileHandler;

public class PlayerListener implements Listener {

    private GameHandler gameHandler = ClansPlugin.getPlugin().getHandlerManager().findHandler(GameHandler.class);
    private ProfileHandler profileHandler = gameHandler.getProfileHandler();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Profile profile = new Profile(player.getUniqueId());

        // set player's loadout
        profile.setLoadout(LobbyLoadout.class);

        if (Bukkit.getOnlinePlayers().size() == ClansConstants.MIN_PLAYERS_PER_CLAN * 4) {
            gameHandler.startGame();
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Profile profile = profileHandler.findProfile(player.getUniqueId());

        if (profile != null) {
            profile.unload();
        }

        if (Bukkit.getOnlinePlayers().size() < ClansConstants.MIN_PLAYERS_PER_CLAN * 4 && this.gameHandler.getTask() != null) {
            // cancel the active game start task
            gameHandler.getTask().cancel();
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            Player player = event.getEntity();
            Player killer = player.getKiller();

            gameHandler.handleDeath(killer, player);
        }
    }
}