package me.OverlordBleck.BloodMoon.commands;

import me.OverlordBleck.BloodMoon.BloodMoonConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.function.Supplier;

public class BloodMoonReload implements CommandExecutor {
    @Override
    public boolean onCommand( CommandSender sender, Command cmd, String label, String[] args ) {
        if ( sender instanceof Player ) {
            Player ply = (Player) sender;

            if ( ply.hasPermission( "bloodmoon.reload" ) ) {
                ply.sendMessage( "Reloading Blood Moon config" );
                Bukkit.getLogger().info( "Reloading config" );
                BloodMoonConfig.reloadConfig();
            }
        } else {
            Bukkit.getLogger().info( "Reloading config" );
            BloodMoonConfig.reloadConfig();
        }

        return false;
    }
}
