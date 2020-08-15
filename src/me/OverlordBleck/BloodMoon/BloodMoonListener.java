package me.OverlordBleck.BloodMoon;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class BloodMoonListener implements Listener {
	@EventHandler
	public void onMobSpawn(EntitySpawnEvent event) {
		Entity ent = (Entity) event.getEntity();
		
		Bukkit.getLogger().info("SOMEONE PLEASE");
		Bukkit.getLogger().info(ent.getType().getEntityClass().getName());
	}
}
