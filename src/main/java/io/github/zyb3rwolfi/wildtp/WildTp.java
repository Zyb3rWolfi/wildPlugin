package io.github.zyb3rwolfi.wildtp;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class WildTp extends JavaPlugin {

    public static WildTp instance;
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
    public static WildTp getInstance() {
        return instance;
    }
}
