package wtf.retarders.clans.handler.impl;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import wtf.retarders.clans.util.CC;

import java.io.File;
import java.util.List;

@Getter
public class Config {

    private String name;
    private FileConfiguration config;

    public Config(String name) {
        this.name = name;
        this.config = YamlConfiguration.loadConfiguration(new File(name));
    }

    public String getString(String path) {
        return CC.translate(config.getString(path));
    }

    public List<String> getStringList(String path) {
        return CC.translate(config.getStringList(path));
    }

    public int getInt(String path) {
        return config.getInt(path);
    }
}
