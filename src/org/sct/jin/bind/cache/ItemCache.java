package org.sct.jin.bind.cache;

import org.sct.jin.bind.Bind;
import org.sct.jin.bind.file.Config;
import org.sct.jin.bind.util.BasicUtil;
import org.sct.jin.bind.util.ItemUtil;

import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * @author SCT_jin
 * @date 2019-03-27 13:39
 */

public class ItemCache {

    public static ItemStack getUnbind() {
        return ItemUtil.buildItem(
                 (String) Config.get("Item.id"),
                (short) getInt("Item.dur"),
                1,
                BasicUtil.convert((String) Config.get("Item.display")),
                BasicUtil.convert((List<String>) Config.get("Item.lore")),
                null,
                (boolean) Config.get("Item.unbreak")
        );
    }

    public static ItemStack getConfirm() {
        return ItemUtil.buildItem(
                (String) Config.get("Gui.confirm.id"),
                (short) 0,
                1,
                BasicUtil.convert((String) Config.get("Gui.confirm.display")),
                BasicUtil.convert((List<String>) Config.get("Gui.confirm.lore")),
                null,
                (boolean) Config.get("Gui.confirm.unbreak")
        );
    }

    public static ItemStack getBlank() {
        return ItemUtil.buildItem(
                (String) Config.get("Gui.blank.id"),
                (short) getInt("Item.dur"),
               // (short) Config.get("Gui.blank.dur"),
                1,
                BasicUtil.convert((String) Config.get("Gui.blank.display")),
                BasicUtil.convert((List<String>) Config.get("Gui.blank.lore")),
                null,
                (boolean) Config.get("Gui.blank.unbreak")
        );
    }

    private static int getInt(String path) {
        return Bind.getInstance().getConfig().getInt(path);
    }

}
