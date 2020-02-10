package wtf.retarders.clans;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;
import wtf.retarders.clans.clan.claim.ClaimListeners;
import wtf.retarders.clans.game.map.Map;
import wtf.retarders.clans.game.map.util.SpawnLocation;
import wtf.retarders.clans.handler.HandlerManager;
import wtf.retarders.clans.listener.PlayerListener;

import java.util.Arrays;

@Getter
public class ClansPlugin extends JavaPlugin {

    private HandlerManager handlerManager;

    @Override
    public void onEnable() {
        // register handler manager
        this.handlerManager = new HandlerManager();

        // configuration serialization
        ConfigurationSerialization.registerClass(Map.class);
        ConfigurationSerialization.registerClass(SpawnLocation.class);

        // register listeners
        Arrays.asList(new PlayerListener(), new ClaimListeners()).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().forEach(player -> player.kickPlayer("The server is restarting..."));
    }

    public static ClansPlugin getPlugin() {
        return ClansPlugin.getPlugin(ClansPlugin.class);
    }

}