package me.NinetyNine.ib.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class IBBucketUtils {

	public static void createBucket(PlayerInventory inventory, String whatBucket) {
		if (whatBucket.equals("lava")) {
			ItemStack lava = new ItemStack(Material.LAVA_BUCKET);
			ItemMeta lmeta = lava.getItemMeta();
			lmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Lava Bucket");
			lmeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			lava.setItemMeta(lmeta);

			inventory.addItem(lava);
		}

		if (whatBucket.equals("water")) {
			ItemStack water = new ItemStack(Material.WATER_BUCKET);
			ItemMeta wmeta = water.getItemMeta();
			wmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Water Bucket");
			wmeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			water.setItemMeta(wmeta);

			inventory.addItem(water);
		}
	}

	public static void createAndPut(Inventory inventory, String whatBucket, int slot) {
		if (whatBucket.equals("lava")) {
			ItemStack lava = new ItemStack(Material.LAVA_BUCKET);
			ItemMeta lmeta = lava.getItemMeta();
			lmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Lava Bucket");
			lmeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			lava.setItemMeta(lmeta);

			inventory.setItem(slot, lava);
		}
		
		if (whatBucket.equals("water")) {
			ItemStack water = new ItemStack(Material.WATER_BUCKET);
			ItemMeta wmeta = water.getItemMeta();
			wmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Water Bucket");
			wmeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			water.setItemMeta(wmeta);

			inventory.setItem(slot, water);
		}
	}
}