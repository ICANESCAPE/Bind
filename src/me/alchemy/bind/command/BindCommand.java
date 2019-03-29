package me.alchemy.bind.command;

import me.alchemy.bind.Bind;
import me.alchemy.bind.cache.ItemCache;
import me.alchemy.bind.file.Config;
import me.alchemy.bind.gui.GuiBuilder;
import me.alchemy.bind.util.BasicUtil;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author SCT_Alchemy
 * @date 2019-03-27 13:37
 */
public class BindCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("/bind open - 打开GUI");
            sender.sendMessage("/bind give <player> - 给指定玩家一个解绑道具");
            sender.sendMessage("/bind reload - 重载");
        }
        if (args.length == 1 && args[0].equals("reload") ) {
            Bind.getInstance().saveDefaultConfig();
            Config.loadMap();
            sender.sendMessage(BasicUtil.convert("&a重载成功"));
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1 && args[0].equals("open")) {
                GuiBuilder.open(player);
            }
            if (args.length == 2 && args[0].equals("give")) {
                Player target = Bukkit.getPlayer(args[1]);
                target.getInventory().addItem(new ItemStack[]{ ItemCache.getUnbind()});
            }
        }
        return false;
    }
}
