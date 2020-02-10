package wtf.retarders.clans.game.map;

import lombok.Getter;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import wtf.retarders.clans.ClansPlugin;
import wtf.retarders.clans.clan.types.PlayerClan;
import wtf.retarders.clans.game.map.util.SpawnLocation;

import java.util.HashMap;
import java.util.List;

@Getter
public class Map implements ConfigurationSerializable {

    private String mapName;
    private int maxPlayersPerTeam;
    private List<SpawnLocation> locations;

    public Map(String mapName, int maxPlayersPerTeam, List<SpawnLocation> spawnLocations) {
        this.mapName = mapName;
        this.locations = spawnLocations;
        this.maxPlayersPerTeam = maxPlayersPerTeam;

        ClansPlugin.getPlugin().getHandlerManager().findHandler(MapHandler.class).getMaps().add(this);
    }

    /**
     * finds a spawn location by clan
     *
     * @param clan the clan to be searched for
     * @return the location
     */
    public SpawnLocation getSpawnByClan(PlayerClan clan) {
        return this.locations.stream()
                .filter(location -> location.getClan().equals(clan))
                .findFirst().orElse(null);
    }

    @Override
    public java.util.Map<String, Object> serialize() {
        java.util.Map<String, Object> map = new HashMap<>();
        map.put("mapName", this.mapName);
        map.put("maxPlayersPerTeam", this.maxPlayersPerTeam);
        map.put("locations", locations.toString());

        return map;
    }
}
