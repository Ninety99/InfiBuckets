package me.NinetyNine.ib.eventhandler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.NinetyNine.ib.utils.IBBucketUtils;
import me.NinetyNine.ib.utils.IBUtils;

public class IBPlayerInteractHandler implements Listener {
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		Block block = e.getClickedBlock();

		if (block.getState() instanceof Sign) {
			Sign sign = (Sign) block.getState();

			if (sign.getLine(0).equalsIgnoreCase(ChatColor.AQUA + "[InfiBuckets]")) {
				openInv(player);
			} else
				return;
		}
	}

	private void openInv(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.GRAY + "Get bucket");

		IBBucketUtils.createAndPut(inventory, "lava", 12);
		
		ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE, (byte) 15);
		ItemMeta pmeta = pane.getItemMeta();
		pmeta.setDisplayName(" ");
		pane.setItemMeta(pmeta);

		for (int i = 0; i < inventory.getSize() - 2; i++)
			inventory.setItem(i, pane);
		
		IBBucketUtils.createAndPut(inventory, "water", 14);
		
		player.openInventory(inventory);
		IBUtils.sendPlayerMessage(player, "Opening...");
	}
}
