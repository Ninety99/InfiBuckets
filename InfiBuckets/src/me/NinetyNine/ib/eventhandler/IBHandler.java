package me.NinetyNine.ib.eventhandler;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.NinetyNine.ib.InfiBuckets;
import me.NinetyNine.ib.hooks.EconomyHook;
import me.NinetyNine.ib.utils.IBUtils;

public class IBHandler implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK))
			return;

		if (!(e.getClickedBlock().getType() == Material.AIR || e.getClickedBlock() == null))
			return;

		if (!(e.getItem().getType() == Material.LAVA_BUCKET || e.getItem().getType() == Material.WATER_BUCKET))
			return;

		if (!(e.getItem().getType() == null || e.getItem().getType() == Material.AIR))
			return;

		Player player = e.getPlayer();
		ItemStack bucket = e.getItem();
		World world = player.getWorld();
		Block block = e.getClickedBlock();

		Location blockLoc = block.getLocation();

		int cost = InfiBuckets.plugin.getConfig().getInt("ibprice");

		if (bucket.getItemMeta().hasEnchant(Enchantment.ARROW_INFINITE)) {
			e.setCancelled(true);
			if (EconomyHook.economy.has(player, cost)) {
				if (bucket.getItemMeta().getDisplayName() == ChatColor.LIGHT_PURPLE + "Lava Bucket") {
					EconomyHook.economy.withdrawPlayer(player, cost);
					world.spawnFallingBlock(blockLoc.clone().add(0, 1, 0), Material.LAVA, (byte) 0);
				} else
					return;

				if (bucket.getItemMeta().getDisplayName() == ChatColor.LIGHT_PURPLE + "Water Bucket") {
					EconomyHook.economy.withdrawPlayer(player, cost);
					world.spawnFallingBlock(blockLoc.clone().add(0, 1, 0), Material.WATER, (byte) 0);
				} else
					return;
			} else {
				e.setCancelled(true);
				IBUtils.sendPlayerMessage(player, "&cYou do not have enough money to use this!");
				return;
			}
		} else
			return;
	}

	/*
	 * public void spawnBack(Player player, ItemStack bucket) { PlayerInventory
	 * playerinv = player.getInventory(); Material b = bucket.getType(); int slot =
	 * playerinv.getSize();
	 * 
	 * new BukkitRunnable() { public void run() { if (playerinv.contains(b)) { for
	 * (int i = 0; i < slot; i++) { if (playerinv.getItem(i).getType() == b) {
	 * playerinv.getItem(i).setType(b); break; } } } }
	 * }.runTaskLater(InfiBuckets.plugin, 2L); }
	 */
}