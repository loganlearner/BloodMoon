package me.OverlordBleck.BloodMoon;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class BloodMoon extends JavaPlugin {
    public static BloodMoon instance;

    private int defaultSpawnLimit;
    private int defaultSpawnRates;

    private boolean activeBloodMoon = false;
    private boolean didTry = false;

    @Override
    public void onEnable() {
        setupDefaults();
        setupInstance();
        setupScheduler();

        Bukkit.getPluginManager().registerEvents( new BloodMoonListener(), this );
    }

    @Override
    public void onDisable() {
    }

    private void setupDefaults() {
        defaultSpawnLimit = getServer().getMonsterSpawnLimit();
        defaultSpawnRates = getServer().getTicksPerMonsterSpawns();
    }

    private void setupInstance() {
        instance = this;
    }

    public boolean isBloodMoon() {
        return activeBloodMoon;
    }

    private boolean isNightTime( World world ) {
        long time = world.getTime();

        return time >= 13000L && time <= 23000L;
    }

    private void startBloodMoon( World world ) {
        getServer().broadcastMessage( ChatColor.DARK_RED + "[Blood Moon] Initiating" );
        world.setMonsterSpawnLimit( defaultSpawnLimit * 2 );
        world.setTicksPerMonsterSpawns( defaultSpawnRates * 2 );

        Bukkit.getLogger().info( String.valueOf( world.getMonsterSpawnLimit() ) );
        Bukkit.getLogger().info( String.valueOf( world.getTicksPerMonsterSpawns() ) );
        activeBloodMoon = true;
    }

    private void stopBloodMoon( World world ) {
        if ( isBloodMoon() )
            getServer().broadcastMessage( ChatColor.DARK_RED + "[BloodMoon] Ending" );

        world.setMonsterSpawnLimit( defaultSpawnLimit );
        world.setTicksPerMonsterSpawns( defaultSpawnRates );

        Bukkit.getLogger().info( String.valueOf( defaultSpawnLimit ) );
        Bukkit.getLogger().info( String.valueOf( defaultSpawnRates ) );

        activeBloodMoon = false;
        didTry = false;
    }

    private void setupScheduler() {
        BukkitScheduler scheduler = getServer().getScheduler();

        scheduler.scheduleSyncRepeatingTask( this, new Runnable() {
            final World overworld = Bukkit.getWorlds().get( 0 );

            @Override
            public void run() {
                if ( isNightTime( overworld ) ) {
                    if ( !isBloodMoon() && !didTry ) {
                        boolean shouldStart = ( Math.random() < 0.05D );

                        Bukkit.getLogger().info( "Attempting Blood Moon" );

                        if ( shouldStart ) {
                            Bukkit.getLogger().info( "Blood Moon Initiating" );
                            startBloodMoon( overworld );
                        } else {
                            Bukkit.getLogger().info( "Blood Moon Failed" );
                        }

                        didTry = true;
                    }
                } else {
                    if ( isBloodMoon() )
                        stopBloodMoon( overworld );
                }
            }
        }, 0L, 10L );
    }
}
