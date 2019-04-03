package org.sct.jin.bind.listener;

import org.sct.jin.bind.cache.ItemCache;
import org.sct.jin.bind.file.Config;
import org.sct.jin.bind.util.BasicUtil;
import org.sct.jin.bind.util.BindUtil;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * @author SCT_jin
 * @date 2019-03-27 13:09
 */
public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    void onUse(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (BindUtil.hasBind(item)) {
            if (!BindUtil.isBind(item)) {
                player.getInventory().setItemInMainHand(BindUtil.bind(item, player));
                player.sendMessage(BasicUtil.convert("&a绑定成功！"));
                return;
            } else {
                player.sendMessage(BasicUtil.convert("&c你手里的武器已经绑定过了"));
                return;
            }
        }
        if (BindUtil.isBind(item) && !BindUtil.getBindPlayer(item).equals(player.getDisplayName())) {
            player.sendMessage(BasicUtil.convert("&c绑定物品的持有人不是你，你无法使用"));
            return;
        }
    }

    @EventHandler
    void onClick(InventoryClickEvent e) {
        int slot = e.getSlot();
        Inventory inventory = e.getInventory();
        ItemStack item = inventory.getItem((Integer) Config.get("Gui.itemslot"));
        Player player = (Player) e.getWhoClicked();

        if (inventory.getName().equals(BasicUtil.convert((String) Config.get("Gui.title")))) {
            if (inventory.getItem(slot).isSimilar(ItemCache.getBlank())) {
                e.setCancelled(true);
            }
            if (slot == (Integer) Config.get("Gui.confirmslot")) {
                e.setCancelled(true);
                if (!BindUtil.checkItem(inventory.getItem((Integer) Config.get("Gui.usageslot")))) {
                    player.sendMessage(BasicUtil.convert("&c这个不是解绑物品哦"));
                    return;
                }
                if (BindUtil.isBind(item)) {
                    player.getInventory().setItemInMainHand( BindUtil.unbind(item, (Player) e.getWhoClicked()));
                    e.getWhoClicked().sendMessage(BasicUtil.convert("&a解绑成功"));
                    e.getWhoClicked().closeInventory();
                } else {
                    player.sendMessage(BasicUtil.convert("&c你的物品没有绑定过，无法解绑"));
                    player.closeInventory();
                }
            }
        }
    }

    @EventHandler
    void onDrop(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        ItemStack item = e.getItemDrop().getItemStack();
        if (BindUtil.isBind(item)) {
            e.setCancelled(true);
            player.sendMessage(BasicUtil.convert("&c绑定武器无法丢弃"));
        }
    }

    @EventHandler
    void onCmd(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();
        if (BindUtil.checkHasBindItem(player)) {
            List<String> commands = (List<String>) Config.get("BanCommands");
            if (commands.contains(e.getMessage())) {
                e.setCancelled(true);
                player.sendMessage(BasicUtil.convert("&c你身上有不属于你的绑定物品，因此无法使用这个命令"));
            }
        }
    }

    @EventHandler
    void onHeld(PlayerItemHeldEvent e) {
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getItem(e.getNewSlot());
        if (BindUtil.checkItem(item) && !BindUtil.getBindPlayer(item).equals(player.getDisplayName())) {
            e.setCancelled(true);
            player.sendMessage(BasicUtil.convert("&c你手里这件物品的所有人不是你"));
        }
       if (BindUtil.checkPermission(player) && !BindUtil.isBind(item)) {
            player.getInventory().setItemInMainHand(BindUtil.bind(item, player));
        }
    }

}
