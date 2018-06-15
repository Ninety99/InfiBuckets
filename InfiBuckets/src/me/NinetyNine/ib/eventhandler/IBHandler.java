package me.NinetyNine.ib.eventhandler;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
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
		ItemStack b = new ItemStack(Material.valueOf(bucket.toString()));

		int cost = InfiBuckets.plugin.getConfig().getInt("ibprice");

		useBucket(e, player, cost, b);
	}

	private void useBucket(PlayerBucketEmptyEvent e, Player player, int cost, ItemStack bucket) {
		if (hasMoney(e, player, cost)) {
			if (bucket.getItemMeta().hasEnchant(Enchantment.ARROW_INFINITE)) {
				getMoney(player, cost);
				if (isLava(bucket)) {
					doAll(player, bucket);
					return;
				} else {
					doAll(player, bucket);
					return;
				}
			} else
				return;
		}
	}
	
	public void doAll(Player player, ItemStack bucket) {
		setBucket(player, bucket);
		back(player, bucket);
	}

	public void getMoney(Player player, int cost) {
		EconomyResponse r = EconomyHook.economy.withdrawPlayer(player, cost);
		if (r.transactionSuccess())
			return;
		else {
			System.out.println("notSuccess");
			IBUtils.sendPlayerMessage(player, "&cTransaction failed.");
			return;
		}
	}

	public void setBucket(Player player, ItemStack bucket) {
		PlayerInventory playerinv = player.getInventory();
		int slots = playerinv.getSize();

		if (playerinv.contains(bucket)) {
			for (int i = 0; i < slots; i++) {
				if (playerinv.getItem(i).getType() == Material.BUCKET)
					playerinv.getItem(i).getItemMeta().addEnchant(Enchantment.ARROW_INFINITE, 1, false);
			}
		} else
			return;
	}

	public void back(Player player, ItemStack bucket) {
		PlayerInventory playerinv = player.getInventory();
		int slots = playerinv.getSize();

		new BukkitRunnable() {
			public void run() {
				if (playerinv.contains(bucket)) {
					for (int i = 0; i < slots; i++) {
						if (isLava(bucket)) {
							if (playerinv.getItem(i).getType() == Material.BUCKET
									&& playerinv.getItem(i).getItemMeta().hasEnchants()) {
								playerinv.setItem(i, getBucket("Lava"));
								break;
							}
						} else {
							if (playerinv.getItem(i).getType() == Material.BUCKET
									&& playerinv.getItem(i).getItemMeta().hasEnchants()) {
								playerinv.setItem(i, getBucket("Water"));
								break;
							}
						}
					}
				} else
					cancel();
			}
		}.runTaskLater(InfiBuckets.plugin, 5L);
	}

	public boolean hasMoney(PlayerBucketEmptyEvent e, Player player, int cost) {
		if (EconomyHook.economy.has(player, cost))
			return true;
		else {
			e.setCancelled(true);
			IBUtils.sendPlayerMessage(player, "&cYou do not have enough money to use this!");
			return false;
		}
	}

	public ItemStack getBucket(String whatBucket) {
		ItemStack bucket = new ItemStack(Material.valueOf(whatBucket + " Bucket"));
		if (whatBucket.contains("lava")) {
			ItemMeta lmeta = bucket.getItemMeta();
			lmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Lava Bucket");
			lmeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
			bucket.setItemMeta(lmeta);
		} else if (whatBucket.contains("water")) {
			ItemMeta wmeta = bucket.getItemMeta();
			wmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Water Bucket");
			wmeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
			bucket.setItemMeta(wmeta);
		}
		return bucket;
	}

	public boolean isLava(ItemStack item) {
		if (item.getType().toString().contains("Lava"))
			return true;
		else
			return false;
	}
}