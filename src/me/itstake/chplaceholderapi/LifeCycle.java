package me.itstake.chplaceholderapi;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.AliasCore;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * Created by bexco on 2016-11-13.
 */
@MSExtension("CHPlaceholderAPI")
public class LifeCycle extends AbstractExtension {

    private static CHPlaceholderHook hook = null;
    @Override
    public Version getVersion() {
        return new SimpleVersion(1, 1, 0);
    }

    @Override
    public void onStartup() {
            hook = new CHPlaceholderHook();
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[CHPlaceholderAPI] &bCHPlaceholderAPI 1.0.0 Enabled."));
        BukkitScheduler scheduler = Bukkit.getScheduler();
        if(isPluginExists()) {
            scheduler.scheduleSyncRepeatingTask(CommandHelperPlugin.self, () -> {
                if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                    hook.hook();
                    scheduler.cancelAllTasks();
                }
            }, 0L, 1L);
        }
    }

    public boolean isPluginExists() {
        Plugin[] plugins = Bukkit.getPluginManager().getPlugins();
        boolean pluginExists = false;
        for(Plugin plugin : plugins) {
            if(plugin.getName().equals("PlaceholderAPI")) {
                pluginExists = true;
            }
        }
        return pluginExists;
    }
}
