package me.alchemy.bind;

import me.alchemy.bind.command.BindCommand;
import me.alchemy.bind.file.Config;
import me.alchemy.bind.listener.Listener;
import me.alchemy.bind.util.BasicUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author SCT_Alchemy
 * @date 2019-03-27 12:47
 */

public final class Bind extends JavaPlugin {

    private static Bind instance;

    @Override
    public void onEnable() {
        instance = this;
        info("&f[&d绑定&f] &a加载成功");
        Bukkit.getPluginManager().registerEvents(new Listener(), this);
        Bukkit.getPluginCommand("bind").setExecutor(new BindCommand());
        saveDefaultConfig();
        Config.loadMap();
    }

    @Override
    public void onDisable() {

    }

    public static void info(String msg) {
        Bukkit.getConsoleSender().sendMessage(BasicUtil.convert(msg));
    }

    public static Bind getInstance() {
        return instance;
    }
}
