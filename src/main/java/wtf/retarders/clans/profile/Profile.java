package wtf.retarders.clans.profile;

import lombok.Data;
import wtf.retarders.clans.ClansPlugin;

import java.util.UUID;

@Data
public class Profile {

    private UUID uuid;

    // handlers
    private ProfileHandler profileHandler = ClansPlugin.getPlugin().getHandlerManager().findHandler(ProfileHandler.class);

    public Profile(UUID uuid) {
        this.uuid = uuid;

        this.profileHandler.getProfiles().add(this);
    }

}
