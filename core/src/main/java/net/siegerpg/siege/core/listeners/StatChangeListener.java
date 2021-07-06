package net.siegerpg.siege.core.listeners;

import net.siegerpg.siege.core.Core;
import net.siegerpg.siege.core.utils.cache.playerData;
import net.siegerpg.siege.core.items.CustomItem;
import net.siegerpg.siege.core.items.CustomItemUtils;
import net.siegerpg.siege.core.items.enums.StatTypes;
import net.siegerpg.siege.core.items.types.misc.CustomMaterial;
import net.siegerpg.siege.core.listeners.ArmorEquip.ArmorEquipEvent;
import net.siegerpg.siege.core.utils.Levels;
import net.siegerpg.siege.core.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginEnableEvent;

import javax.annotation.Nullable;
import java.util.HashMap;

public class StatChangeListener implements Listener, Runnable {

    public static HashMap<Player, Double> playerHealth = new HashMap<>();
    public static HashMap<Player, Double> playerToughness = new HashMap<>();

    public static void addHealthTough(Player player) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.plugin(), () -> {
            playerHealth.put(
                    player,
                    CustomItemUtils.INSTANCE.getPlayerStat(player, StatTypes.HEALTH) + player.getMaxHealth() + player.getLevel() * 2);

            playerToughness.put(
                    player,
                    CustomItemUtils.INSTANCE.getPlayerStat(player, StatTypes.TOUGHNESS));
        }, 2);
    }

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        for (Player player : Bukkit.getOnlinePlayers()){
            addHealthTough(player);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        addHealthTough(e.getPlayer());
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        playerHealth.remove(e.getPlayer());
        playerToughness.remove(e.getPlayer());
    }

    @EventHandler
    public void onEquip(ArmorEquipEvent e){
        @Nullable CustomItem item = CustomItemUtils.INSTANCE.getCustomItem(e.getNewArmorPiece());
        if (item == null) {
            addHealthTough(e.getPlayer());
            return;
        }
        if (item.getLevelRequirement() == null) {return;}
        if (item.getLevelRequirement() > Levels.INSTANCE.getExpLevel(e.getPlayer()).getFirst()) {
            e.getPlayer().sendTitle("", ChatColor.RED + "Too weak to use this armor's stats", 1, 80, 1);
            return;
        }
        addHealthTough(e.getPlayer());
    }

    @EventHandler
    public void toolSwitch(PlayerItemHeldEvent e) {
        CustomItem item = CustomItemUtils.INSTANCE.getCustomItem(e.getPlayer().getInventory().getItemInMainHand());
        if (item != null) {
            if (!(item instanceof CustomMaterial)) {
                addHealthTough(e.getPlayer());
            }
        }
    }

    public static void statBarDisplayTask() {
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Core.plugin(), () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (playerHealth.get(p) == null) continue;
                if (playerData.hasActionBar.get(p)) continue;
                double health = Utils.round(playerHealth.get(p), 1);
                double toughness = Utils.round(playerToughness.get(p), 1);
                double customHealth = Utils.round(CustomItemUtils.INSTANCE.getCustomHealth(p), 1);
                playerData.hasActionBar.put(p, true);
                p.sendActionBar(Utils.parse("<red>"
                        + customHealth + "<dark_red>/" + health + " \u2764"
                        + "          <dark_aqua>" + Utils.round(toughness, 1) + " \uD83D\uDEE1       "));
            }
            for (Player p : Bukkit.getOnlinePlayers()) {
                playerData.hasActionBar.put(p, false);
            }
        }, 0, 40);
    }

    @Override
    public void run() {

    }
}
