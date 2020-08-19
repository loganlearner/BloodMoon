package me.OverlordBleck.BloodMoon;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BloodMoonConfig {

    private static File file;
    private static FileConfiguration customFile;

    public static void setupConfig() {
        file = new File( Bukkit.getServer().getPluginManager().getPlugin( "BloodMoon" ).getDataFolder(), "bloodmoonconfig.yml" );

        if ( !file.exists() ) {
            try {
                file.createNewFile();
            } catch ( IOException e ) {
                Bukkit.getLogger().warning( "Unable to create bloodmoonconfig.yml" );
            }
        }

        customFile = YamlConfiguration.loadConfiguration( file );
    }

    public static FileConfiguration getConfig() {
        return customFile;
    }

    public static void saveConfig() {
        try {
            customFile.save( file );
        } catch ( IOException e ) {
            Bukkit.getLogger().warning( "Could not save bloodmoonconfig.yml" );
        }
    }

    public static void reloadConfig() {
        customFile = YamlConfiguration.loadConfiguration( file );
    }
}
