package wtf.retarders.clans.util;

import lombok.Data;
import org.bukkit.Location;

@Data
public class Cuboid {

    private int maxX, maxY, maxZ, minX, minY, minZ;

    /**
     * the main constructor for a Cuboid
     *
     * @param location1 the first location of the cuboid
     * @param location2 the second location of the cuboid
     */
    public Cuboid(Location location1, Location location2) {
        if(location1.getWorld() != location2.getWorld()) {
            throw new IllegalArgumentException("world of location1 does not equal location2's world");
        }

        this.maxX = Math.max(location1.getBlockX(), location2.getBlockX());
        this.maxY = Math.max(location1.getBlockX(), location2.getBlockX());
        this.maxZ = Math.max(location1.getBlockX(), location2.getBlockX());

        this.minX = Math.max(location1.getBlockX(), location2.getBlockX());
        this.minY = Math.max(location1.getBlockX(), location2.getBlockX());
        this.minZ = Math.max(location1.getBlockX(), location2.getBlockX());
    }

    /**
     * checks if location is in cuboid
     *
     * @param location the location to be checked
     * @return whether the location is in the cuboid
     */
    public boolean isLocationInCuboid(Location location) {
        return (minX <= location.getBlockX() && minY <= location.getBlockY() && minZ <= location.getBlockZ()) && (maxX >= location.getBlockX() && maxY >= location.getBlockY() && maxZ >= location.getBlockZ());
    }

}
