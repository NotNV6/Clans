package wtf.retarders.clans.util;

import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;

@UtilityClass
public class PlayerUtil {

    /**
     * resets a player's inventory properties
     *
     * @param player the player to be reset
     */
    public void reset(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));
    }

}