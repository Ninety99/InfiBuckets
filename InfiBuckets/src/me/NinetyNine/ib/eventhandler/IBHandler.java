package me.NinetyNine.ib.eventhandler;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import me.NinetyNine.ib.InfiBuckets;
import me.NinetyNine.ib.hooks.EconomyHook;
import me.NinetyNine.ib.utils.IBUtils;
import net.milkbowl.vault.economy.EconomyResponse;

public class IBHandler implements Listener {

	@EventHandler
	public void onPlayerBucketEmpty(PlayerBucketEmptyEvent e) {
		Player player = e.getPlayer();
		Material bucket = e.getBucket();
		ItemStack b = new ItemStack(bucket);

		int cost = InfiBuckets.plugin.getConfig().getInt("ibprice");

		useBucket(player, cost, b);
	}

	private void useBucket(Player player, int cost, ItemStack bucket) {
		if (hasMoney(player, cost) == true) {
			if (bucket.getItemMeta().hasEnchant(Enchantment.ARROW_INFINITE)) {
				if (bucket.getType().toString().contains("lava")) {
					EconomyResponse r = EconomyHook.economy.withdrawPlayer(player, cost);
					if (r.transactionSuccess())
						spawnBack(player, bucket);
					else {
						IBUtils.sendPlayerMessage(player, "&cTransaction failed.");
						return;
					}
				}

				if (bucket.getType().toString().contains("water")) {
					EconomyResponse r = EconomyHook.economy.withdrawPlayer(player, cost);
					if (r.transactionSuccess())
						spawnBack(player, bucket);
					else {
						IBUtils.sendPlayerMessage(player, "&cTransaction failed.");
						return;
					}
				}
			} else
				return;
		} else
			return;
	}

	private void spawnBack(Player player, ItemStack bucket) {
		PlayerInventory playerinv = player.getInventory();
		Material b = bucket.getType();
		ItemStack buck = new ItemStack(Material.BUCKET);
		int slot = playerinv.getSize();

		new BukkitRunnable() {
			public void run() {
				if (playerinv.contains(b)) {
					for (int i = 0; i < slot; i++) {
						if (playerinv.getItem(i) == buck) {
							playerinv.getItem(i).setType(b);
							break;
						}
					}
				}
			}
		}.runTaskLater(InfiBuckets.plugin, 2L);
	}

	private boolean hasMoney(Player player, int cost) {
		if (EconomyHook.economy.has(player, cost))
			return true;
		else {
			IBUtils.sendPlayerMessage(player, "&cYou do not have enough money to use this!");
			return false;
		}
	}

}