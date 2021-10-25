package net.siegerpg.siege.core.listeners;

import net.siegerpg.siege.core.Core;
import net.siegerpg.siege.core.database.DatabaseManager;
import net.siegerpg.siege.core.items.CustomItem;
import net.siegerpg.siege.core.items.CustomItemUtils;
import net.siegerpg.siege.core.items.implemented.misc.cosmetics.TestingCosmetic;
import net.siegerpg.siege.core.items.implemented.misc.cosmetics.TestingCosmetic2;
import net.siegerpg.siege.core.items.implemented.misc.cosmetics.legendary.HexShifter;
import net.siegerpg.siege.core.items.implemented.misc.cosmetics.legendary.Rainbow;
import net.siegerpg.siege.core.items.implemented.misc.food.Beetroot;
import net.siegerpg.siege.core.items.implemented.misc.food.Drumstick;
import net.siegerpg.siege.core.items.implemented.misc.food.GoldenCarrot;
import net.siegerpg.siege.core.items.implemented.misc.food.Sugar;
import net.siegerpg.siege.core.items.implemented.misc.keys.cosmetic.CommonKey;
import net.siegerpg.siege.core.items.implemented.misc.keys.cosmetic.NormalKey;
import net.siegerpg.siege.core.items.implemented.misc.keys.cosmetic.UncommonKey;
import net.siegerpg.siege.core.items.implemented.misc.wands.BeginnerLivingTwig;
import net.siegerpg.siege.core.items.implemented.weapons.melee.TestSword;
import net.siegerpg.siege.core.items.implemented.weapons.melee.heavy.BeginnerClub;
import net.siegerpg.siege.core.items.implemented.weapons.melee.light.BeginnerTwig;
import net.siegerpg.siege.core.items.implemented.weapons.ranged.BeginnerScrapyardBow;
import net.siegerpg.siege.core.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void connectEvent(final AsyncPlayerPreLoginEvent e) {
        new BukkitRunnable() { // We create a runnable to run asynchronously (on another thread, not the main one, so that the server won't lag if this one does)
            @Override
            public void run() {
                final String ip = e.getAddress().getHostAddress();
                final String uuid = e.getUniqueId().toString();
                // Add user ips to the db (So that we can in the future find all alts of an user)
                try (final Connection conn = DatabaseManager.INSTANCE.getConnection()) {
                    final PreparedStatement stat = conn.prepareStatement("SELECT ip FROM ipData WHERE uuid=? AND ip=?");
                    stat.setString(1, uuid);
                    stat.setString(2, ip);
                    final ResultSet set = stat.executeQuery();
                    if (!set.isBeforeFirst()) {
                        final PreparedStatement statement = conn.prepareStatement("INSERT INTO ipData (uuid, ip) VALUES (?, ?)");
                        statement.setString(1, uuid);
                        statement.setString(2, ip);
                        statement.executeUpdate();
                    }
                } catch (final SQLException ignored) {
                }
            }
        }.runTaskAsynchronously(Core.plugin());
    }


    @EventHandler
    public void joinEvent(final PlayerJoinEvent event) {

        final Player player = event.getPlayer();

        final String prefix = net.siegerpg.siege.core.utils.VaultHook.perms.getPrimaryGroup(player);
        String joinMessage = Utils.tacc("&a&lJOIN &7[&a+&7] " + prefix + " &7" + player.getName());
        player.teleport(Core.plugin().getServer().getWorld("Hub").getSpawnLocation());

        Levels.INSTANCE.getExpLevel(player, shortIntegerPair -> {
            if (shortIntegerPair == null) {
                try (final Connection conn = DatabaseManager.INSTANCE.getConnection()) {
                    // Add the user to the db if he doesn't exist
                    final PreparedStatement userData = conn.prepareStatement("INSERT INTO userData (uuid) VALUES (?)");
                    userData.setString(1, player.getUniqueId().toString());
                    userData.executeUpdate();
                } catch (final SQLException ignored) {
                }

            }
            return null;
        });

        if (!player.hasPlayedBefore()) {
            this.newPlayerReward(player);
            joinMessage = Utils.tacc("&a&lWELCOME&r &7[&a+&7] " + prefix + " &7" + player.getName());
        } else {
            new BukkitRunnable() { // We create a runnable to run asynchronously (on another thread, not the main one, so that the server won't lag if this one does)
                @Override
                public void run() {
                    PlayerJoinListener.this.updateInventory(player);
                }
            }.runTaskAsynchronously(Core.plugin());
        }
        event.setJoinMessage(joinMessage);

        for (final Player p : Bukkit.getOnlinePlayers()) {
            Scoreboard.updateScoreboard(p);
            Tablist.tablistUpdate(p);
        }

        /*if (event.getPlayer().getName().equals("Wrys")) {
            player.getInventory().addItem(new TestSword(150).getUpdatedItem(false));
            if (Skills.INSTANCE.getSkills(player).equals("")) {
                try (Connection conn = DatabaseManager.INSTANCE.getConnection()) {
                    PreparedStatement skillsData = conn.prepareStatement("INSERT INTO skillsData (uuid) VALUES (?)");
                    skillsData.setString(1, player.getUniqueId().toString());
                    skillsData.executeUpdate();
                } catch (SQLException ignored) {
                }
            }
            Skills.INSTANCE.setSkills(player, "A_1_3");
        }*/
    }

    public void updateInventory(final Player player) {
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            final CustomItem CusItem = CustomItemUtils.INSTANCE.getCustomItem(player.getInventory().getItem(i));
            if (CusItem == null) continue;
            player.getInventory().setItem(i, CusItem.getUpdatedItem(false));
        }
    }

    public void newPlayerReward(final Player player) {
        player.getInventory().clear();
        player.getInventory().addItem(new BeginnerTwig(50).getUpdatedItem(false));
        player.getInventory().addItem(new BeginnerClub(50).getUpdatedItem(false));
        player.getInventory().addItem(new BeginnerScrapyardBow(50).getUpdatedItem(false));
        player.getInventory().addItem(new BeginnerLivingTwig(50).getUpdatedItem(false));
        final ItemStack food = new Drumstick(0).getUpdatedItem(false).asQuantity(10);
        player.getInventory().addItem(food);
        VaultHook.econ.withdrawPlayer(player, VaultHook.econ.getBalance(player));
        VaultHook.econ.depositPlayer(player, 400.0);
    }

}
