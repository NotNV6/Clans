package wtf.retarders.clans.clan.claim;

import lombok.Getter;
import org.bukkit.Location;
import wtf.retarders.clans.clan.IClan;
import wtf.retarders.clans.util.Cuboid;

@Getter
public class Claim extends Cuboid {

    private IClan clan;
    private boolean protection;

    public Claim(IClan clan, boolean protection, Location location1, Location location2) {
        super(location1, location2);

        this.clan = clan;
        this.protection = protection;
    }
}
