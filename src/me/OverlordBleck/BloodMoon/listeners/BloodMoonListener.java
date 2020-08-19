package me.OverlordBleck.BloodMoon.listeners;

import me.OverlordBleck.BloodMoon.BloodMoon;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class BloodMoonListener implements Listener {
    Player closestPlayer( Location loc ) {
        Player closest = null;
        double closestDist = Double.POSITIVE_INFINITY;

        for ( Player ply : Bukkit.getOnlinePlayers() ) {
            double dist = loc.distanceSquared( ply.getLocation() );

            if ( dist < closestDist ) {
                closest = ply;
                closestDist = dist;
            }
        }

        return closest;
    }

    @EventHandler
    public void onMobSpawn( CreatureSpawnEvent event ) {
        Entity ent = event.getEntity();

        if ( BloodMoon.instance.isBloodMoon() ) {
            if ( ent instanceof Monster ) {
                if ( ( ent instanceof Zombie ) || ( ent instanceof Skeleton ) || ( ent instanceof Spider ) || ( ent instanceof Creeper ) ) {
                    ( (Monster) ent ).setTarget( closestPlayer( ent.getLocation() ) );
                }
            }
        }
    }
}
