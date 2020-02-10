package wtf.retarders.clans.util;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CC {

    /**
     * translates a String
     *
     * @param string the string to be translated
     * @return the translated string
     */
    public static String translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    /**
     * translates as string list
     *
     * @param strings the list to be translated
     * @return the translated string list
     */
    public static List<String> translate(List<String> strings) {
        return strings.stream().map(CC::translate).collect(Collectors.toList());
    }

}