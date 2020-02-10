package wtf.retarders.clans.clan.claim;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import wtf.retarders.clans.ClansConstants;
import wtf.retarders.clans.ClansPlugin;
import wtf.retarders.clans.game.GameHandler;
import wtf.retarders.clans.profile.Profile;
import wtf.retarders.clans.profile.ProfileHandler;

public class ClaimListeners implements Listener {

    private ClaimHandler claimHandler = ClansPlugin.getPlugin().getHandlerManager().findHandler(ClaimHandler.class);
    private GameHandler gameHandler = ClansPlugin.getPlugin().getHandlerManager().findHandler(GameHandler.class);
    private ProfileHandler profileHandler = gameHandler.getProfileHandler();

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Profile profile = profileHandler.findProfile(player.getUniqueId());
        Claim claim = claimHandler.getClaimAtLocation(player.getLocation());

        if(claim == null) {
            event.setCancelled(true);
            return;
        }

        // if claim's clan does not equal the profile's clan, cancel block break.
        if(claim.getClan() != profile.getCurrentClan() && claim.isProtection()) {
            event.setCancelled(true);
        } else if (ClansConstants.DESTROYABLE_BLOCKS.stream().anyMatch(material -> material.equals(event.getBlock().getType()))) {
            Block block = event.getBlock();


            if(block.getType().name().toLowerCase().contains("ore")) {
                // remove block if material is an ore
                block.setType(Material.AIR);

                // place the ore back 5 seconds later.
                Bukkit.getScheduler().runTaskLater(ClansPlugin.getPlugin(), () -> block.setType(block.getType()), 100L);
            }
        }
    }
}
