package net.siegerpg.siege.core.listeners.NPC;

import de.tr7zw.nbtapi.NBTItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.siegerpg.siege.core.Core;
import net.siegerpg.siege.core.fishing.fish.Fish;
import net.siegerpg.siege.core.fishing.fish.FishCore;
import net.siegerpg.siege.core.utils.Scoreboard;
import net.siegerpg.siege.core.utils.Utils;
import net.siegerpg.siege.core.utils.VaultHook;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class Henry implements Listener {

    @EventHandler
    public void onRightClickEntity(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getName().contains("Henry") && e.getRightClicked().getName().contains("6")) {
            Player person = e.getPlayer();
            buyFish(person);
        }
    }
    private void buyFish(Player player) {
        ItemStack hand = player.getInventory().getItemInMainHand();
        if (hand.getType().equals(Material.AIR)) return;
        NBTItem nbt = new NBTItem(hand);
        String fishName = nbt.getString("Name");
        if (fishName == null) {
            player.sendMessage(Utils.parse("<red>This is not a fish."));
            return;
        }
        Fish fish = FishCore.getFish(fishName);
        int goldAmount;
        if (fish == null) {
            List<Component> lore = hand.lore();
            if (lore != null && lore.get(1).contains(Utils.lore("Size "))) {
                Component lineOne = lore.get(1);
                String line = PlainTextComponentSerializer.plainText().serialize(lineOne);
                String newLine = line.replace("Size ", "").replace(" cm", "");
                try {
                    goldAmount = Integer.parseInt(newLine);
                } catch(Exception ignored){
                    player.sendMessage(Utils.parse("<red>This is not a fish!"));
                    return;
                }

            } else {
                player.sendMessage(Utils.parse("<red>This is not a fish!"));
                return;
            }
        } else {
            goldAmount = (int)fish.actualSize;
        }

        VaultHook.econ.depositPlayer(player, goldAmount);
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
        player.sendActionBar(Utils.parse("<yellow>+ " + goldAmount + " <yellow>Gold"));
        player.sendMessage(Utils.parse("\n<green>You sold a <aqua>"+ goldAmount +" cm "+fish.name+" <yellow>for "+goldAmount+" coins!\n"));
        Bukkit.getServer().getScheduler().runTaskLater(Core.plugin(), new Runnable() {
            public void run() {
                Scoreboard.updateScoreboard(player);
            }
        }, 20);

        player.getInventory().removeItem(hand.asOne());
    }
}
