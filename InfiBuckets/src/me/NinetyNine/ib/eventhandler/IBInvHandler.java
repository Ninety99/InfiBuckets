package me.NinetyNine.ib.eventhandler;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.NinetyNine.ib.utils.IBBucketUtils;
import me.NinetyNine.ib.utils.IBUtils;

public class IBInvHandler implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inventory = e.getInventory();
		ItemStack current = e.getCurrentItem();

		if (inventory.getName() == ChatColor.GRAY + "Get bucket") {
			if (current.getType() != Material.AIR || current.getType() != null) {
				if (current.getType() == Material.WATER_BUCKET) {
					e.setCancelled(true);
					IBBucketUtils.createBucket(player.getInventory(), "water");
					IBUtils.sendPlayerMessage(player, "&aYou have recieved the infinite water bucket!");
				} 
				if (current.getType() == Material.LAVA_BUCKET) {
					e.setCancelled(true);
					IBBucketUtils.createBucket(player.getInventory(), "lava");
					IBUtils.sendPlayerMessage(player, "&aYou have recieved the infinite lava bucket!");
				}
				switch (current.getData().getData()) {
				default:
					break;
				case 15:
					e.setCancelled(true);
					break;
				}
			} else
				e.setCancelled(true);
		}
	}
}
