package wtf.retarders.clans;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

@UtilityClass
public class ClansConstants {

    public int MAX_PLAYERS_PER_CLAN = 4;
    public int MIN_PLAYERS_PER_CLAN = 1;

    public double DTR_PER_PLAYER = 1.0D;
    public double DTR_REDUCE = 1.0D;

    public String GAME_STARTING = ChatColor.YELLOW + "The game will begin in " + ChatColor.LIGHT_PURPLE + " %time% seconds" + ChatColor.YELLOW + ".";
    public String TEAM_IS_FULL = ChatColor.RED + "The team you tried to join is already full.";

}