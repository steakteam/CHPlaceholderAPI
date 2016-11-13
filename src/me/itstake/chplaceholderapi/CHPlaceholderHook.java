package me.itstake.chplaceholderapi;

import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.Globals;
import com.laytonsmith.core.constructs.CNull;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.exceptions.MarshalException;
import com.laytonsmith.persistence.DataSourceException;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import me.clip.placeholderapi.external.EZPlaceholderHook;
import org.bukkit.entity.Player;

/**
 * Created by bexco on 2016-11-13.
 */
public class CHPlaceholderHook extends EZPlaceholderHook {

    public CHPlaceholderHook() {
        super(CommandHelperPlugin.self, "ch");
    }
    @Override
    public String onPlaceholderRequest(Player player, String s) {
        if(s.startsWith("get_value_")) {
            String key = s.replaceFirst("get_value_", s);
            Object value = null;
            try {
                value = CommandHelperPlugin.self.persistenceNetwork.get(("storage." + key).split("\\."));
            } catch (DataSourceException e) {
                return "";
            }
            if(value == null) {
                return "";
            }
            try {
                return Construct.json_decode(value.toString(), Target.UNKNOWN).toString();
            } catch (MarshalException e) {
                return "";
            }
        } else if(s.startsWith("import_")) {
            String key = s.replaceFirst("import_", s);
            Construct c = Globals.GetGlobalConstruct(key);
            if(c instanceof CNull) {
                return "";
            }
            return c.toString();
        }
        return "";
    }
}
