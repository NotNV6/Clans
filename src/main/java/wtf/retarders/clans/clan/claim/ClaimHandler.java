package wtf.retarders.clans.clan.claim;

import lombok.Getter;
import org.bukkit.Location;
import wtf.retarders.clans.handler.IHandler;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ClaimHandler implements IHandler {

    private List<Claim> claims;

    public ClaimHandler() {
        this.claims = new ArrayList<>();
    }

    /**
     * gets a claim at a specific location
     *
     * @param location the location to check
     * @return the claim at the location
     */
    public Claim getClaimAtLocation(Location location) {
        return this.claims.stream()
                .filter(claim -> claim.isLocationInCuboid(location))
                .findFirst().orElse(null);
    }

}
