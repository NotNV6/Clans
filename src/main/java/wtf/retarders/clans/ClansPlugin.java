package wtf.retarders.clans;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import wtf.retarders.clans.handler.HandlerManager;
import wtf.retarders.clans.listener.PlayerListener;

@Getter
public class ClansPlugin extends JavaPlugin {

    private HandlerManager handlerManager;

    @Override
    public void onEnable() {
        // register handler manager
        this.handlerManager = new HandlerManager();

        // register listeners
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer("The server is restarting..."));
    }

    public static ClansPlugin getPlugin() {
        return ClansPlugin.getPlugin(ClansPlugin.class);
    }

}