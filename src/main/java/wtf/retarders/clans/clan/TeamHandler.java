package wtf.retarders.clans.clan;

import org.bukkit.ChatColor;
import wtf.retarders.clans.clan.types.PlayerClan;
import wtf.retarders.clans.handler.IHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeamHandler implements IHandler {

    private List<IClan> clans;

    public TeamHandler() {
        this.clans = new ArrayList<>();

        // add default clans
        this.clans.addAll(Arrays.asList(
                new PlayerClan("Red", ChatColor.RED),
                new PlayerClan("Blue", ChatColor.BLUE),
                new PlayerClan("Green", ChatColor.GREEN),
                new PlayerClan("Yellow", ChatColor.YELLOW)
        ));
    }

    public IClan findClan(String clanName) {
        return this.clans.stream()
                .filter(clan -> clan.getClanName().equalsIgnoreCase(clanName))
                .findFirst().orElse(null);
    }
}
