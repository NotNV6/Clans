package wtf.retarders.clans.handler.impl;

import lombok.Getter;
import wtf.retarders.clans.handler.IHandler;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ConfigHandler implements IHandler {

    private List<Config> configs;

    public ConfigHandler() {
        this.configs = new ArrayList<>();
    }

    public Config createConfig(String configName) {
        Config config = new Config(configName);
        this.configs.add(config);

        return config;
    }

    public Config findConfig(String configName) {
        return this.configs.stream()
                .filter(config -> config.getName().equalsIgnoreCase(configName))
                .findFirst().orElse(null);
    }
}