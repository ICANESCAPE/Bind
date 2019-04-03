package org.sct.jin.bind.util;

import org.sct.jin.bind.Bind;
import org.sct.jin.bind.cache.ItemCache;
import org.sct.jin.bind.file.Config;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

/**
 * @author SCT_jin
 * @date 2019-03-27 12:50
 */

public class BindUtil {

    public static boolean isBind(ItemStack item) {
        if (item == null || !item.hasItemMeta()) {
            return false;
        }
        String before = (String) Config.get("Before");
        for (String key : item.getItemMeta().getLore()) {
            if (key.contains(before)) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasBind(ItemStack item) {
        if (item == null || !item.hasItemMeta()) {
            return false;
        }
        String before = (String) Config.get("Before");
        for (String key : item.getItemMeta().getLore()) {
            if (key.contains(before)) {
                return true;
            }
        }
        return false;
    }

    public static ItemStack bind(ItemStack item, Player player) {
        String before = BasicUtil.convert((String) Config.get("Before"));
        String bind = BasicUtil.convert((String) Config.get("After"));
        item = ItemUtil.replace(item, before, bind.replace("%player%", player.getDisplayName()));
        return item;
    }

    public static ItemStack unbind(ItemStack item, Player player) {
        String before = BasicUtil.convert((String) Config.get("Before"));
        String bind = BasicUtil.convert((String) Config.get("After")).replace("%player%", player.getDisplayName());
        item = ItemUtil.replace(item, bind, before);
        return item;
    }

    public static boolean checkItem(ItemStack unbind) {
        if (unbind == null || !unbind.hasItemMeta()) {
            return false;
        }
        return unbind.isSimilar(ItemCache.getUnbind());
    }

    public static String getBindPlayer(ItemStack item) {
        if (!isBind(item)) {
            return null;
        }
        List<String> lores = item.getItemMeta().getLore();
        String target = (String) Config.get("After");
        for (String key : lores) {
            if (key.contains(target.split(": ")[0])) {
                return key.split(": ")[1];
            }
        }
        return null;
    }

    public static boolean checkHasBindItem(Player player) {
        PlayerInventory inventory = player.getInventory();
        for (ItemStack item : inventory.getContents()) {
            return isBind(item);
        }
        return false;
    }

    public static boolean checkPermission(Player player) {
        return player.hasPermission(Bind.getInstance().getConfig().getString("Permissions"));
    }
}
