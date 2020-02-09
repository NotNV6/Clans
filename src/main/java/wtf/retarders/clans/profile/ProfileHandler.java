package wtf.retarders.clans.profile;

import lombok.Getter;
import wtf.retarders.clans.handler.IHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class ProfileHandler implements IHandler {

    private List<Profile> profiles;

    public ProfileHandler() {
        this.profiles = new ArrayList<>();
    }

    /**
     * finds a profile
     *
     * @param uuid the uuid of the player
     * @return the profile found with the specified uuid
     */
    public Profile findProfile(UUID uuid) {
        return this.profiles.stream()
                .filter(profile -> profile.getUuid().equals(uuid))
                .findFirst().orElse(null);
    }
}