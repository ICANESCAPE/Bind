package org.sct.jin.bind.file;

import org.sct.jin.bind.Bind;
import org.sct.jin.bind.util.BasicUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;


/**
 * @author SCT_jin
 * @date 2019-03-27 12:51
 */

public class Config {

    static FileConfiguration config = Bind.getInstance().getConfig();
    private static Map<String, String> permission = new HashMap<>();

    public static Object get(String path) {
        return config.get(path);
    }

    public static void loadMap() {
        ConfigurationSection cs = config.getConfigurationSection("Permissions");
        for (String key : cs.getKeys(false)) {
            permission.put(BasicUtil.convert(cs.getString(key + ".lore")), cs.getString(key + ".permission"));
        }
    }

    public static String getPermission(String lore) {
        loadMap();
        for (String key : permission.keySet()) {
            if (lore.equals(key)) {
                return permission.get(key);
            }
        }
        return null;
    }

}
