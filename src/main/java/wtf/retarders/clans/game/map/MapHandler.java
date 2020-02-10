package wtf.retarders.clans.game.map;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import wtf.retarders.clans.ClansPlugin;
import wtf.retarders.clans.clan.ClanHandler;
import wtf.retarders.clans.clan.types.PlayerClan;
import wtf.retarders.clans.game.map.util.SpawnLocation;
import wtf.retarders.clans.handler.IHandler;
import wtf.retarders.clans.handler.impl.Config;
import wtf.retarders.clans.handler.impl.ConfigHandler;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MapHandler implements IHandler {

    private List<Map> maps;
    private Map currentMap;

    public MapHandler() {
        this.maps = new ArrayList<>();

        ConfigHandler configHandler = ClansPlugin.getPlugin().getHandlerManager().findHandler(ConfigHandler.class);

        Config config = configHandler.createConfig("maps.yml");
        // loop through all maps
        config.getConfig().getConfigurationSection("map").getKeys(false).forEach(key -> {
            String pathName = "map." + key + '.';

            String mapName = config.getString(pathName + "mapName");
            int maxPlayers = config.getInt(pathName + "maxPlayersPerTeam");
            List<SpawnLocation> spawnLocations = new ArrayList<>();

            // load all spawn points
            ConfigurationSection configurationSection = config.getConfig().getConfigurationSection(pathName + "locations");
            configurationSection.getKeys(false).forEach(key2 -> {
                String pathName2 = pathName + "locations." + key2 + '.';

                int x = config.getInt(pathName2 + 'x');
                int y = config.getInt(pathName2 + 'y');
                int z = config.getInt(pathName2 + 'z');
                String worldName = config.getString(pathName2 + "world");
                String clanName = config.getString(pathName2 + "clan");
                ClanHandler clanHandler = ClansPlugin.getPlugin().getHandlerManager().findHandler(ClanHandler.class);

                spawnLocations.add(new SpawnLocation((PlayerClan) clanHandler.findClan(clanName), new Location(Bukkit.getWorld(worldName), x, y, z)));
            });

            new Map(mapName, maxPlayers, spawnLocations);
        });
    }

}