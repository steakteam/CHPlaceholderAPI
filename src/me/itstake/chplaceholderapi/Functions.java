package me.itstake.chplaceholderapi;

import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCPlayer;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.CommandHelperEnvironment;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREPlayerOfflineException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by bexco on 2016-11-13.
 */
public class Functions {
    public static String docs() {
        return "This extension brings Intergration with PlaceholderAPI in CommandHelper";
    }

    @api
    public static class placeholderapi_parse extends AbstractFunction {

        @Override
        public Class<? extends CREThrowable>[] thrown() {
            return new Class[]{};
        }

        @Override
        public boolean isRestricted() {
            return false;
        }

        @Override
        public Boolean runAsync() {
            return null;
        }

        @Override
        public Construct exec(Target target, Environment environment, Construct... constructs) throws ConfigRuntimeException {
            Player player = null;
            String parse = null;
            if(constructs.length == 1) {
                if(environment.getEnv(CommandHelperEnvironment.class).GetCommandSender() instanceof BukkitMCPlayer) {
                    player = ((BukkitMCPlayer) environment.getEnv(CommandHelperEnvironment.class).GetPlayer())._Player();
                    parse = constructs[0].toString();
                } else {
                    throw new CREPlayerOfflineException("You can't parse placeholder when player isn't given. If you want parse placeholder in console or command block, You have to insert player name in first arg.", target);
                }
            } else if(constructs.length == 2) {
                player = Bukkit.getPlayerExact(constructs[0].toString());
                parse = constructs[1].toString();
            }
            return new CString(PlaceholderAPI.setPlaceholders(player, parse), target);
        }

        @Override
        public Version since() {
            return null;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public Integer[] numArgs() {
            return new Integer[]{1, 2};
        }

        @Override
        public String docs() {
            return null;
        }
    }
}
