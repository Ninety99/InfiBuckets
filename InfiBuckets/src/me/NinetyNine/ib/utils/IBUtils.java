package me.NinetyNine.ib.utils;

import static org.bukkit.ChatColor.*;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class IBUtils {

	public static void sendPlayerMessage(Player player, String message) {
		final String prefix = "&7[&bInfiBuckets&7] ";
		player.sendMessage(translateAlternateColorCodes('&', prefix + message));
	}

	public static void sendConsoleMessage(String message) {
		Bukkit.getServer().getLogger().info("[InfiBuckets] " + message);
	}
}
