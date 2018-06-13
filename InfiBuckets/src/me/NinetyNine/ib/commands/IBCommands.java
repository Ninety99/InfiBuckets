package me.NinetyNine.ib.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.NinetyNine.ib.InfiBucketsConfig;
import me.NinetyNine.ib.utils.IBBucketUtils;
import me.NinetyNine.ib.utils.IBUtils;

public class IBCommands implements Listener, CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (cmd.getName().equalsIgnoreCase("ib")) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED + "You can't use this!");
					return true;
				}

				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("water") || args[0].equalsIgnoreCase("lava")) {
						sender.sendMessage(ChatColor.RED + "You can't use this!");
						return true;
					}
					
					if (args[0].equalsIgnoreCase("reload")) {
						InfiBucketsConfig.saveConfig();
						sender.sendMessage(ChatColor.GREEN + "Reloaded.");
						return true;
					}
				}
			}
			return true;
		} else {
			Player player = (Player) sender;

			if (cmd.getName().equalsIgnoreCase("ib")) {
				if (player.hasPermission("ib.get")) {
					if (args.length == 0) {
						IBUtils.sendPlayerMessage(player, "&cUsage: /ib <water/lava>");
						return true;
					}

					if (args.length == 1) {
						if (args[0].equalsIgnoreCase("water")) {
							IBBucketUtils.createBucket(player.getInventory(), "water");
							IBUtils.sendPlayerMessage(player, "&aYou have recieved the infinite water bucket!");
							return true;
						}

						if (args[0].equalsIgnoreCase("lava")) {
							IBBucketUtils.createBucket(player.getInventory(), "lava");
							IBUtils.sendPlayerMessage(player, "&aYou have recieved the infinite lava bucket!");
							return true;
						}
						
						if (args[0].equalsIgnoreCase("reload")) {
							InfiBucketsConfig.saveConfig();
							IBUtils.sendPlayerMessage(player, "&aReloaded.");
							return true;
						}
					}
				} else {
					IBUtils.sendPlayerMessage(player, "&cYou do not have permissions to execute this command!");
					return true;
				}
			}
		}
		return true;
	}
}