package io.guthub.zyb3rwolfi.wild2;

import org.bukkit.event.EventPriority;
import org.bukkit.plugin.java.JavaPlugin;
import javax.annotation.OverridingMethodsMustInvokeSuper;

public final class Wild2 extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ExampleListener(), this);
        getCommand("wild").setExecutor(new WildCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
