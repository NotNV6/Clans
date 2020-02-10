package wtf.retarders.clans.profile;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import wtf.retarders.clans.ClansPlugin;
import wtf.retarders.clans.clan.types.PlayerClan;
import wtf.retarders.clans.loadout.ItemLoadoutHandler;
import wtf.retarders.clans.util.PlayerUtil;
import wtf.retarders.clans.loadout.ItemLoadout;

import java.util.UUID;

@Data
public class Profile {

    private UUID uuid;

    private PlayerClan currentClan;

    // handlers
    private ProfileHandler profileHandler = ClansPlugin.getPlugin().getHandlerManager().findHandler(ProfileHandler.class);

    public Profile(UUID uuid) {
        this.uuid = uuid;

        this.profileHandler.getProfiles().add(this);
    }

    public void unload() {
        if (this.currentClan != null) {
            this.currentClan.removePlayer(this);
        }

        this.profileHandler.getProfiles().remove(this);
    }

    public void setLoadout(Class<? extends ItemLoadout> loadoutClass) {
        Player player = Bukkit.getPlayer(uuid);
        ItemLoadout loadout = ClansPlugin.getPlugin().getHandlerManager().findHandler(ItemLoadoutHandler.class).findLoadout(loadoutClass);

        // clear inventory
        PlayerUtil.reset(player);

        // set contents
        player.getInventory().setContents(loadout.getItems());
    }
}