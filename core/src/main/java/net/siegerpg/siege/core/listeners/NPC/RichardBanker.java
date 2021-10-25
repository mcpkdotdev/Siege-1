package net.siegerpg.siege.core.listeners.NPC;

import kotlin.Pair;
import net.siegerpg.siege.core.Core;
import net.siegerpg.siege.core.utils.Bank;
import net.siegerpg.siege.core.utils.Scoreboard;
import net.siegerpg.siege.core.utils.Utils;
import net.siegerpg.siege.core.utils.VaultHook;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.Objects;

public class RichardBanker implements Listener {

    @EventHandler
    public void onRightClickOnEntity(final PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getName().contains("Richard") && e.getRightClicked().getName().contains("6")) {
            final Inventory shop = this.getMenu(e.getPlayer());
            e.getPlayer().openInventory(shop);
        }
    }

    @EventHandler
    public void guiClick(final InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        if (e.getWhoClicked().getMetadata("RichardBank").size() > 0 &&
                Objects.equals(e.getWhoClicked().getMetadata("RichardBank").get(0).value(), e.getInventory())) {
            this.clickMenu(e);
            e.setCancelled(true);
        }
    }

    private void clickMenu(final InventoryClickEvent e) {
        final Player player = (Player) e.getWhoClicked();
        final int slot = e.getSlot();
        Pair<Short, Integer> bankLvlAmt = Bank.INSTANCE.blockingGetBankLevelAmount(player);
        if (bankLvlAmt == null) bankLvlAmt = new Pair<>((short) 0, 0);
        short bankLvl = bankLvlAmt.getFirst();
        int bankAmt = bankLvlAmt.getSecond();
        final int upgradeCost = bankLvl * 2000;
        final int maxAmt = bankLvl * 7500;
        final short upgradedLvl = (short) (bankLvl + 1);
        final double pocketBal = VaultHook.econ.getBalance(player);

        if (slot > 27 && slot < 31) { //if slots are withdrawing from bank
            if (slot == 28) {
                VaultHook.econ.depositPlayer(player, bankAmt);
                bankAmt = 0;
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1.0f, 1.0f);
            } else if (slot == 29) {
                if (bankAmt >= 1000) {
                    VaultHook.econ.depositPlayer(player, 1000);
                    bankAmt = bankAmt - 1000;
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1.0f, 1.0f);
                } else {
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
                }
            } else {
                if (bankAmt >= 100) {
                    VaultHook.econ.depositPlayer(player, 100);
                    bankAmt = bankAmt - 100;
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1.0f, 1.0f);
                } else {
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
                }
            }
        } else if (slot > 31 && slot < 35) { //if slots are depositing to the bank
            if (slot == 32) {
                if (pocketBal >= 100 && bankAmt + 100 <= maxAmt) {
                    VaultHook.econ.withdrawPlayer(player, 100);
                    bankAmt = bankAmt + 100;
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1.0f, 1.0f);
                } else {
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
                }
            } else if (slot == 33) {
                if (pocketBal >= 1000 && bankAmt + 1000 <= maxAmt) {
                    VaultHook.econ.withdrawPlayer(player, 1000);
                    bankAmt = bankAmt + 1000;
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1.0f, 1.0f);
                } else {
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
                }
            } else {
                int diff = (maxAmt - bankAmt);
                if (pocketBal < diff) {
                    diff = (int) pocketBal;
                }
                VaultHook.econ.withdrawPlayer(player, diff);
                Bank.INSTANCE.setBankAmount(player, diff);
                bankAmt = bankAmt + diff;
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1.0f, 1.0f);
            }
        } else if (slot == 31) { //if slot is upgrading
            if (pocketBal >= upgradeCost) {
                VaultHook.econ.withdrawPlayer(player, upgradeCost);
                Bank.INSTANCE.setBankLevel(player, upgradedLvl);
                bankLvl = upgradedLvl;
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1.0f, 1.0f);
            } else if (bankAmt + pocketBal >= upgradeCost) {
                final int subBankDiff = (int) (upgradeCost - pocketBal);
                VaultHook.econ.withdrawPlayer(player, pocketBal);
                bankLvl = upgradedLvl;
                bankAmt = bankAmt - subBankDiff;
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1.0f, 1.0f);
            } else {
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
            }
        }
        Bank.INSTANCE.setBankLevel(player, bankLvl);
        Bank.INSTANCE.setBankAmount(player, bankAmt);
        player.openInventory(this.getMenu(player));
        Scoreboard.updateScoreboard(player);
        Bank.INSTANCE.setBankAmount(player, bankAmt);
        Bank.INSTANCE.setBankLevel(player, bankLvl);
    }


    private Inventory getMenu(final Player player) {
        final Inventory gui = Bukkit.createInventory(null, 45, "Richard the Banker");

        //Fill in the GUI
        final ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        for (int i = 0; i < gui.getSize(); i++) {
            gui.setItem(i, filler);
        }
        Pair<Short, Integer> bankLvlAmt = Bank.INSTANCE.blockingGetBankLevelAmount(player);
        if (bankLvlAmt == null) bankLvlAmt = new Pair<>((short) 0, 0);
        final short bankLvl = bankLvlAmt.getFirst();
        final int bankAmt = bankLvlAmt.getSecond();
        final int upgradeCost = bankLvl * 2000;
        final short upgradedLvl = (short) (bankLvl + 1);
        final int pocketBal = (int) VaultHook.econ.getBalance(player);
        final int maxAmt = bankLvl * 7500;
        int diff = (maxAmt - bankAmt);
        if (pocketBal < diff) {
            diff = pocketBal;
        }

        final ItemStack bankAcc = new ItemStack(Material.GOLD_INGOT);
        final ItemMeta bankAccMeta = bankAcc.getItemMeta();
        bankAccMeta.displayName(Utils.lore("<gray>Bank Account <gold><bold>Lvl. " + bankLvl));
        bankAccMeta.lore(new ArrayList<>() {
            {
                this.add(Utils.lore("<gold>Profile <gray>" + player.getName()));
                this.add(Utils.lore("  <gray>Bank <yellow>" + bankAmt));
                this.add(Utils.lore("  <gray>Pocket <yellow>" + String.format("%,d", pocketBal)));
                this.add(Utils.lore(""));
                this.add(Utils.lore("<gold>Max Bank <yellow>" + String.format("%,d", maxAmt)));
            }
        });
        bankAcc.setItemMeta(bankAccMeta);

        final ItemStack subAll = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        final ItemMeta subAllMeta = subAll.getItemMeta();
        subAllMeta.displayName(Utils.lore("<red><bold>Withdraw"));
        subAllMeta.lore(new ArrayList<>() {
            {
                this.add(Utils.lore("<red>-" + String.format("%,d", bankAmt) + " <gray>Bank"));
                this.add(Utils.lore("<green>+" + String.format("%,d", bankAmt) + " <gray>Pocket"));
            }
        });
        subAll.setItemMeta(subAllMeta);

        final ItemStack sub1000 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        final ItemMeta sub1000Meta = sub1000.getItemMeta();
        sub1000Meta.displayName(Utils.lore("<red><bold>Withdraw"));
        sub1000Meta.lore(new ArrayList<>() {
            {
                this.add(Utils.lore("<red>-1000 <gray>Bank"));
                this.add(Utils.lore("<green>+" + String.format("%,d", 1000) + " <gray>Pocket"));
            }
        });
        sub1000.setItemMeta(sub1000Meta);

        final ItemStack sub100 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        final ItemMeta sub100Meta = sub100.getItemMeta();
        sub100Meta.displayName(Utils.lore("<red><bold>Withdraw"));
        sub100Meta.lore(new ArrayList<>() {
            {
                this.add(Utils.lore("<red>-100 <gray>Bank"));
                this.add(Utils.lore("<green>+100 <gray>Pocket"));
            }
        });
        sub100.setItemMeta(sub100Meta);

        final ItemStack bankUpgrade = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        final ItemMeta bankUpgradeMeta = bankUpgrade.getItemMeta();
        bankUpgradeMeta.displayName(Utils.lore("<gold>Click to Upgrade"));
        bankUpgradeMeta.lore(new ArrayList<>() {
            {
                this.add(Utils.lore("<gold>" + bankLvl + " <gray>\u2192 <gold>" + upgradedLvl));
                this.add(Utils.lore("<gray>Cost <yellow>" + String.format("%,d", upgradeCost)));
                this.add(Utils.lore(""));
            }
        });
        bankUpgrade.setItemMeta(bankUpgradeMeta);

        final ItemStack add100 = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        final ItemMeta add100Meta = add100.getItemMeta();
        add100Meta.displayName(Utils.lore("<green><bold>Deposit"));
        add100Meta.lore(new ArrayList<>() {
            {
                this.add(Utils.lore("<green>+100 <gray>Bank"));
                this.add(Utils.lore("<red>-100 <gray>Pocket"));
            }
        });
        add100.setItemMeta(add100Meta);

        final ItemStack add1000 = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        final ItemMeta add1000Meta = add1000.getItemMeta();
        add1000Meta.displayName(Utils.lore("<green><bold>Deposit"));
        add1000Meta.lore(new ArrayList<>() {
            {
                this.add(Utils.lore("<green>+1000 <gray>Bank"));
                this.add(Utils.lore("<red>-" + String.format("%,d", 1000) + " <gray>Pocket"));
            }
        });
        add1000.setItemMeta(add1000Meta);

        final ItemStack addAll = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        final ItemMeta addAllMeta = addAll.getItemMeta();
        addAllMeta.displayName(Utils.lore("<green><bold>Deposit"));
        final int finalDiff = diff;
        addAllMeta.lore(new ArrayList<>() {
            {
                this.add(Utils.lore("<green>+" + String.format("%,d", finalDiff) + " <gray>Bank"));
                this.add(Utils.lore("<red>-" + String.format("%,d", finalDiff) + " <gray>Pocket"));
            }
        });
        addAll.setItemMeta(addAllMeta);

        //This is where you decide what slot the item goes into
        gui.setItem(13, bankAcc);
        gui.setItem(28, subAll);
        gui.setItem(29, sub1000);
        gui.setItem(30, sub100);
        gui.setItem(31, bankUpgrade);
        gui.setItem(32, add100);
        gui.setItem(33, add1000);
        gui.setItem(34, addAll);

        player.setMetadata("RichardBank", new FixedMetadataValue(Core.plugin(), gui));
        return gui;
    }
}