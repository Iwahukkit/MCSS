package me.iwahu.mcss;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SSRenderer {

    private static String cc(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static void sendMessage(Player player) {
        String serverIp = JavaPlugin.getPlugin(MCSS.class).getConfig().getString("screenshare.ip", "ts.example.com");

        String w = "&f\u2588";
        String r = "&c\u2588";
        String y = "&e\u2588";
        String b = "&0\u2588";

        player.sendMessage(cc(w + w + w + w + w + w + w + w + w));
        player.sendMessage(cc(w + w + w + w + r + w + w + w + w) + cc("  &c&lATTENTION"));
        player.sendMessage(cc(w + w + w + r + b + r + w + w + w));
        player.sendMessage(cc(w + w + r + y + b + y + r + w + w) + cc("  &cYou have been frozen. &c&lDO NOT LOGOUT!"));
        player.sendMessage(cc(w + w + r + y + b + y + r + w + w) + cc("  &cIf you logout you will be &c&lBANNED."));
        player.sendMessage(cc(w + r + y + y + b + y + y + r + w) + cc("  &cJoin our Teamspeak &f" + serverIp));
        player.sendMessage(cc(w + r + y + y + y + y + y + r + w));
        player.sendMessage(cc(r + r + y + y + b + y + y + r + r));
        player.sendMessage(cc(r + r + r + r + r + r + r + r + r));
        player.sendMessage(cc(w + w + w + w + w + w + w + w + w));
    }
}