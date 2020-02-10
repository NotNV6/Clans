package wtf.retarders.clans.clan.types;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import wtf.retarders.clans.clan.IClan;
import wtf.retarders.clans.profile.Profile;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class PlayerClan implements IClan {

    private String clanName;
    private ChatColor displayColor;

    private Set<Profile> members;

    private double dtr = 0.0D;

    public PlayerClan(String clanName, ChatColor displayColor) {
        this.clanName = clanName;
        this.displayColor = displayColor;
        this.members = new HashSet<>();
    }

    public void addPlayer(Profile profile) {
        this.members.add(profile);
        profile.setCurrentClan(this);
    }

    public void removePlayer(Profile profile) {
        this.members.remove(profile);
        profile.setCurrentClan(null);
    }

    public String getDisplayName() {
        return this.displayColor + clanName;
    }
}