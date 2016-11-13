package me.itstake.chplaceholderapi;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.core.AliasCore;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 * Created by bexco on 2016-11-13.
 */
@MSExtension("CHPlaceholderAPI")
public class LifeCycle extends AbstractExtension {

    private static CHPlaceholderHook hook = null;
    @Override
    public Version getVersion() {
        return new SimpleVersion(1, 0, 0);
    }

    @Override
    public void onStartup() {
            hook = new CHPlaceholderHook();
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[CHPlaceholderAPI] &bCHPlaceholderAPI 1.0.0 Enabled."));
    }

    @Override
    public void onPreReloadAliases(AliasCore.ReloadOptions options) {
        if(!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[CHPlaceholderAPI] &4Can't find PlaceholderAPI!! extension will not working."));
        } else {
            if(!hook.isHooked()) {
                hook.hook();
            }
        }
    }
}
