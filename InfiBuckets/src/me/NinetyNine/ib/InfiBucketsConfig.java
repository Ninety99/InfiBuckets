package me.NinetyNine.ib;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

public class InfiBucketsConfig implements Listener {

	private static FileConfiguration config = InfiBuckets.plugin.getConfig();
	
	public static FileConfiguration getConfig() {
		return config;
	}
	
	public static void saveConfig() {
		int old = config.getInt("ibprice");
		InfiBuckets.plugin.getConfig().set("ibprice", old);
		InfiBuckets.plugin.saveConfig();
		InfiBuckets.plugin.reloadConfig();
	}
	
	public static void loadConfig() {
		config.options().copyDefaults(true);
		saveConfig();
	}
}