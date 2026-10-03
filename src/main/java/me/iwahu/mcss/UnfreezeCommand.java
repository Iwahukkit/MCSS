package me.iwahu.mcss;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnfreezeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("mcss.staff")) {
            sender.sendMessage(ChatColor.RED + "You don't have permissions!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /unfreeze <player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found or offline!");
            return true;
        }

        if (!FreezeManager.isFrozen(target)) {
            sender.sendMessage(ChatColor.RED + target.getName() + " named player is not frozen!");
            return true;
        }

        FreezeManager.unfreezePlayer(target);

        sender.sendMessage(ChatColor.GREEN + target.getName() + " named player is unfrozen succesfully!");

        return true;
    }
}