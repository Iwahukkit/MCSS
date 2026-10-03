package me.iwahu.mcss;

import org.bukkit.plugin.java.JavaPlugin;

public class MCSS extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        // Event kaydı
        getServer().getPluginManager().registerEvents(new FreezeManager(), this);

        // Komut kaydı
        if (getCommand("ss") != null) {
            getCommand("ss").setExecutor(new SSCommand());
        }
        if (getCommand("unfreeze") != null) {
            getCommand("unfreeze").setExecutor(new UnfreezeCommand());
        }
    }
}