package me.OverlordBleck.BloodMoon;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class BloodMoon extends JavaPlugin {
	public static BloodMoon instance;
	
	private boolean activeBloodMoon = false;
	private boolean didTry = false;
	
	@Override
	public void onEnable() {
		setupInstance();
		setupScheduler();
		
		Bukkit.getPluginManager().registerEvents(new BloodMoonListener(), this);
	}
	
	@Override
	public void onDisable() {
	}
	
	private void setupInstance() {
		instance = this;
	}
	
	public boolean isBloodMoon() {
		return activeBloodMoon;
	}
	
	private boolean isNightTime(World world) {
		long time = world.getTime();
		
		if(time >= 13000L && time <= 23000L) {
			return true;
		}
		
		return false;
	}
	
	private void setupScheduler() {
		BukkitScheduler scheduler = getServer().getScheduler();
		
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				World overworld = Bukkit.getWorlds().get(0);
				if(isNightTime(overworld)) {
					if(!isBloodMoon() && !didTry) {
						boolean shouldStart = ((Math.random() < 0.05D) ? true : false);
						
						Bukkit.getLogger().info("Attempting Blood Moon");
						
						if(shouldStart) {
							Bukkit.getLogger().info("Blood Moon Initiating");
							activeBloodMoon = true;
							didTry = true;
						} else {
							Bukkit.getLogger().info("Blood Moon Failed");
							didTry = true;
						}
					}
				} else {
					if(isBloodMoon()) {
						// RESET VALUES
						
						activeBloodMoon = false;
					}
					
					didTry = false;
				}
			}
		}, 0L, 10L);
	}
}
