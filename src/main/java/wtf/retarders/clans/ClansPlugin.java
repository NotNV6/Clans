package wtf.retarders.clans;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import wtf.retarders.clans.handler.HandlerManager;

@Getter
public class ClansPlugin extends JavaPlugin {

    private HandlerManager handlerManager;

    @Override
    public void onEnable() {
        // register handler manager
        this.handlerManager = new HandlerManager();

    }

    @Override
    public void onDisable() {

    }

    public static ClansPlugin getPlugin() {
        return ClansPlugin.getPlugin(ClansPlugin.class);
    }

}
