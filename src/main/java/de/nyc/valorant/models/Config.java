package de.nyc.valorant.models;

import de.nyc.valorant.MCValorant;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    private final File file;
    private final YamlConfiguration config;

    public Config(MCValorant main, String name) {
        if (!main.getDataFolder().exists()) {
            //noinspection ResultOfMethodCallIgnored
            main.getDataFolder().mkdirs();
        }

        file = new File(main.getDataFolder(), name);
        if (!file.exists()){
            try {
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public void saveConfig(){
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
