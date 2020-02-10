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

    /**
     * gets a string from the config
     *
     * @param path the path of the string
     * @return the string at the path
     */
    public String getString(String path) {
        return CC.translate(config.getString(path));
    }

    /**
     * gets a list from the config
     *
     * @param path the path of the list
     * @return the list at the path
     */
    public List<String> getStringList(String path) {
        return CC.translate(config.getStringList(path));
    }

    /**
     * gets a int from the config
     *
     * @param path the path of the int
     * @return the int at the path
     */
    public int getInt(String path) {
        return config.getInt(path);
    }
}