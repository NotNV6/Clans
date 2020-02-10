package wtf.retarders.clans.game.map.util;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import wtf.retarders.clans.clan.types.PlayerClan;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class SpawnLocation implements ConfigurationSerializable {

    private PlayerClan clan;
    private Location location;

    public SpawnLocation(PlayerClan clan, Location location) {
        this.clan = clan;
        this.location = location;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();

        map.put("clan", clan.getClanName());
        map.put("x", location.getX());
        map.put("y", location.getY());
        map.put("z", location.getZ());
        map.put("world", location.getWorld().getName());

        return map;
    }
}
