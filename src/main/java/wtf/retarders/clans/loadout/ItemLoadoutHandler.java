package wtf.retarders.clans.loadout;

import wtf.retarders.clans.handler.IHandler;
import wtf.retarders.clans.loadout.impl.LobbyLoadout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemLoadoutHandler implements IHandler {

    private List<ItemLoadout> loadouts;

    public ItemLoadoutHandler() {
        this.loadouts = new ArrayList<>();

        loadouts.addAll(Arrays.asList(
                new LobbyLoadout()
                // more to come
        ));
    }

    @SuppressWarnings("unchecked")
    public <T> T findLoadout(Class<T> loadoutClass) {
        return (T) this.loadouts.stream()
                .filter(loadout -> loadout.getClass().equals(loadoutClass))
                .findFirst().orElse(null);
    }

}
