package org.sct.jin.bind.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SCT_jin
 * @date 2019-03-27 12:48
 */

public class BasicUtil {

    public static String convert(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> convert(List<String> messages) {
        List<String> msg = new ArrayList<>();
        for (String string : messages) {
            msg.add(convert(string));
        }
        return msg;
    }

}
