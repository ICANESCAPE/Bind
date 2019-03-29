package me.alchemy.bind.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Iterator;
import java.util.List;

/**
 * @author SCT_Alchemy
 * @date 2019-03-27 12:59
 */

public class ItemUtil {

    /**
     * 查找指定Lore的行数
     *
     * @param item 物品
     * @param lore 被查找的Lore
     * @return 行数，不存在未-1
     */
    public static int getLoreIndex(ItemStack item, String lore) {
        if (item == null || !item.hasItemMeta() || lore == null || "".equals(lore)) {
            return -1;
        }
        List<String> lores = item.getItemMeta().getLore();
        int count = 0;
        for (String target : lores) {
            if (lore.equalsIgnoreCase(target)) {
                return count;
            }
            count++;
        }
        return -1;
    }

    /**
     * 在指定行数插入Lore
     *
     * @param item 物品
     * @param lore 插入的Lore
     * @return 插入Lore后的物品
     */
    public static ItemStack insert(ItemStack item, String lore) {
        if (item == null || !item.hasItemMeta() || lore == null || "".equals(lore)) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        List<String> lores = meta.getLore();
        lores.add(BasicUtil.convert(lore));
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * 在指定行数插入Lore
     *
     * @param item 物品
     * @param lore 插入的Lore
     * @param index 指定行数
     * @return 插入Lore后的物品
     */
    public static ItemStack insert(ItemStack item, String lore, int index) {
        if (item == null || !item.hasItemMeta() || lore == null || "".equals(lore) || index < 0) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        List<String> lores = meta.getLore();
        lores.add(index, BasicUtil.convert(lore));
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * 删除指定的Lore(可能存在多个)
     *
     * @param item 物品
     * @param lore 指定Lore
     * @return 完成后的物品
     */
    public static ItemStack delete(ItemStack item, String lore) {
        if (item == null || !item.hasItemMeta() || lore == null) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        List<String> lores = meta.getLore();
        Iterator iterator = lores.iterator();
        while (iterator.hasNext()) {
            String target = (String) iterator.next();
            if (target.equalsIgnoreCase(BasicUtil.convert(lore))) {
                iterator.remove();
            }
        }
    /*    for (String target : lores) {
            if (target.equalsIgnoreCase(BasicUtil.convert(lore))) {
                lores.remove(target);
            }
        }*/
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * 删除指定行数的Lore
     *
     * @param item 物品
     * @param index 指定行数
     * @return 完成后的物品
     */
    public static ItemStack delete(ItemStack item, int index) {
        if (item == null || !item.hasItemMeta() || index < 0) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        List<String> lores = meta.getLore();
        lores.remove(index);
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * 替换指定的Lore
     *
     * @param item 物品
     * @param old 被替换的lore
     * @param target 替换的Lore
     * @return 替换后的物品
     */
    public static ItemStack replace(ItemStack item, String old, String target) {
        if (item == null || !item.hasItemMeta() || "".equals(old) || "".equals(target)) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        List<String> lores = meta.getLore();
        Iterator iterator = lores.iterator();
        while (iterator.hasNext()) {
            String lore = (String) iterator.next();
            if (lore.equals(BasicUtil.convert(old))) {
                lores.set(getLoreIndex(item, old), BasicUtil.convert(target));
            }
        }
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * 替换指定行的Lore
     *
     * @param item 物品
     * @param  index 指定位置
     * @param target 替换的Lore
     * @return 替换后的物品
     */
    public static ItemStack replace(ItemStack item, int index, String target) {
        if (item == null || !item.hasItemMeta() || index < 0 || "".equals(target)) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        List<String> lores = meta.getLore();
        lores.set(index, BasicUtil.convert(target));
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * 构建一个物品
     *
     * @param material 材质
     * @param durability 子id
     * @param amount 数量
     * @param display 名字
     * @param lore lore
     * @param flags flags
     * @param unbreakable 是否不可破坏
     * @return 构建的物品
     */
    public static ItemStack buildItem(String material, short durability, int amount, String display, List<String> lore, ItemFlag[] flags, boolean unbreakable) {
        int id;
        ItemStack item;
        try {
            id = Integer.parseInt(material);
            item = new ItemStack(Material.getMaterial(id));
        } catch (NumberFormatException e) {
            item = new ItemStack(Material.getMaterial(material));
        }
        item.setAmount(amount);
        item.setDurability(durability);
        ItemMeta meta = item.getItemMeta();
        if (display != null) {
            meta.setDisplayName(BasicUtil.convert(display));
        }
        if (lore != null) {
            meta.setLore(BasicUtil.convert(lore));
        }
        if (unbreakable) {
            item.getItemMeta().setUnbreakable(true);
        }
        if (flags != null) {
            meta.addItemFlags(flags);
        }
        item.setItemMeta(meta);
        return item;
    }
    
}
