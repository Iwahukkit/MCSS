package me.iwahu.mcss;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SSCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // 1. Yetki Kontrolü
        if (!sender.hasPermission("mcss.staff")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }

        // 2. Argüman Sayısı Kontrolü
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /ss <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        // 3. Oyuncu Bulunamadıysa
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found or offline!");
            return true;
        }

        // 4. Freeze/Unfreeze İşlemi
        if (FreezeManager.isFrozen(target)) {
            FreezeManager.unfreezePlayer(target);
            target.sendMessage(ChatColor.GREEN + "Your screenshare session has ended. You are now unfrozen.");
            sender.sendMessage(ChatColor.GREEN + target.getName() + " is no longer frozen.");
        } else {
            FreezeManager.freezePlayer(target);
            sender.sendMessage(ChatColor.YELLOW + target.getName() + " has been frozen and put into screenshare.");
        }

        return true;
    }
}