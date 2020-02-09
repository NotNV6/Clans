package wtf.retarders.clans.clan.types;

import lombok.Getter;
import org.bukkit.ChatColor;
import wtf.retarders.clans.clan.IClan;

@Getter
public class PlayerClan implements IClan {

    private String clanName;
    private ChatColor displayColor;

    private double dtr = 0.0D;

    public PlayerClan(String clanName, ChatColor displayColor) {
        this.clanName = clanName;
        this.displayColor = displayColor;
    }

    public String getDisplayName() {
        return this.displayColor + clanName;
    }
}
