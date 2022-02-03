package de.nyc.valorant.Utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    private static File file;
    private static YamlConfiguration config;

    public Config() {
        File dir = new File("./plugins/Valorant/");
        if (!dir.exists()) {
            dir.mkdirs();

        }

        file = new File(dir, "config.yml");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file);

    }

    public static YamlConfiguration getConfig() {
        return config;
    }

    public static void saveConfig(){
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
