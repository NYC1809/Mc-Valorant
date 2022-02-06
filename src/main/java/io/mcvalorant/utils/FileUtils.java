package io.mcvalorant.utils;

import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FileUtils {

    /**
     * Copies the resource of the Bukkit plugin into the data folder of the Bukkit plugin.
     * @param main The main class of the Bukkit plugin
     * @param resourceName The file name of the resource
     * @return Whether the operation succeeded. Will also return true if the file already exists in the data folder.
     */
    @SuppressWarnings("UnstableApiUsage")
    public static boolean copyResource(JavaPlugin main, String resourceName) {
        File destination = new File(main.getDataFolder(), resourceName);
        URL resource = main.getClass().getClassLoader().getResource(resourceName);

        if (resource == null) {
            main.getLogger().severe("The resource " + resourceName + " does not exist.");
            return false;
        }

        if (destination.exists()) {
            main.getLogger().info("The file " + resourceName + " already exists.");
            return true;
        }

        try {
            //noinspection ResultOfMethodCallIgnored
            destination.createNewFile();
            Resources.asByteSource(resource).copyTo(Files.asByteSink(destination));
            main.getLogger().info("The file " + resourceName + " has been created.");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
