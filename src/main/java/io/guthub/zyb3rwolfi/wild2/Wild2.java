package io.guthub.zyb3rwolfi.wild2;

import org.bukkit.event.EventPriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public final class Wild2 extends JavaPlugin {

    public static Wild2 instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        BukkitScheduler scheduler = this.getServer().getScheduler();

        // Getting data from config.yml
        saveResource("config.yml", false);
        saveDefaultConfig();
        // Setting the timeout
        int timeout = getConfig().getInt("timeout");
        // Debugging
        getLogger().info("Timeout is set to: " + timeout);
        // We are getting the command
        getCommand("wild").setExecutor(new WildCommand(timeout));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null;
    }
    public static Wild2 getInstance() {
        return instance;
    }
}
