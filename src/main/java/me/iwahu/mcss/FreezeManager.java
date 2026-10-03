package me.iwahu.mcss;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static me.iwahu.mcss.SSRenderer.*;

public class FreezeManager implements Listener {

    private static final Set<UUID> frozenPlayers = new HashSet<>();

    public static boolean isFrozen(Player player) {
        return frozenPlayers.contains(player.getUniqueId());
    }

    public static void freezePlayer(Player player) {
        frozenPlayers.add(player.getUniqueId());
        SSRenderer.sendMessage(player);
    }

    public static void unfreezePlayer(Player player) {
        frozenPlayers.remove(player.getUniqueId());
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (isFrozen(player)) {
            if (event.getFrom().getX() != event.getTo().getX() ||
                    event.getFrom().getY() != event.getTo().getY() ||
                    event.getFrom().getZ() != event.getTo().getZ()) {

                event.setTo(event.getFrom());
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player victim = (Player) event.getEntity();
            if (isFrozen(victim)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player attacker = (Player) event.getDamager();
            if (isFrozen(attacker)) {
                event.setCancelled(true);
            }
        }
    }

    // 4. Blok Kırmayı Engelle
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (isFrozen(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    // 5. Blok Koymayı Engelle
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (isFrozen(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (isFrozen(event.getPlayer())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You can't use commands while screenshare!");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (isFrozen(player)) {
            unfreezePlayer(player);

            Bukkit.getBanList(BanList.Type.NAME).addBan(
                    player.getName(),
                    "Avoiding from SS",
                    null,
                    "Console"
            );

            org.bukkit.Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + player.getName() + " banned because avoiding from screenshare!");
        }
    }
}