package wtf.retarders.clans.handler;

import wtf.retarders.clans.clan.ClanHandler;
import wtf.retarders.clans.handler.impl.GameHandler;
import wtf.retarders.clans.profile.ProfileHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HandlerManager {

    private List<IHandler> handlers;

    public HandlerManager() {
        this.handlers = new ArrayList<>();

        this.handlers.addAll(Arrays.asList(
                new ClanHandler(),
                new ProfileHandler(),
                new GameHandler()
        ));
    }

    /**
     * Gets a handler instance by class
     *
     * @param handlerClass the class to find a handler instance with
     * @param <T>          the type of object of the returning handler
     * @return the handler
     */
    @SuppressWarnings("unchecked")
    public <T> T findHandler(Class<T> handlerClass) {
        return (T) this.handlers.stream()
                .filter(handler -> handler.getClass().equals(handlerClass))
                .findFirst().orElseThrow(() -> new IllegalArgumentException(handlerClass.getName() + " is not a registered handler."));
    }
}
