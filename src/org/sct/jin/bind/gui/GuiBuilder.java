package org.sct.jin.bind.gui;

import org.sct.jin.bind.cache.ItemCache;
import org.sct.jin.bind.file.Config;
import org.sct.jin.bind.util.BasicUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * @author SCT_jin
 * @date 2019-03-27 13:42
 */

public class GuiBuilder {

    private static Inventory inventory;

    private static int item = (Integer) Config.get("Gui.itemslot");
    private static int usage = (Integer) Config.get("Gui.usageslot");
    private static int confirm = (Integer) Config.get("Gui.confirmslot");
    private static int size = (Integer) Config.get("Gui.size");
    private static String title = BasicUtil.convert((String) Config.get("Gui.title"));

    public static void open(Player player) {
        inventory = Bukkit.createInventory(null, size, title);
        add();
        player.closeInventory();
        player.openInventory(inventory);
    }

    private static void add() {
        for (int i = 0; i < size; i++) {
            if (i == item || i == usage) {
                inventory.setItem(i, null);
            } else {
                inventory.setItem(i, ItemCache.getBlank());
            }
        }
        inventory.setItem(confirm, ItemCache.getConfirm());
    }
}
