package me.NinetyNine.ib;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.NinetyNine.ib.commands.IBCommands;
import me.NinetyNine.ib.eventhandler.IBHandler;
import me.NinetyNine.ib.eventhandler.IBInvHandler;
import me.NinetyNine.ib.eventhandler.IBPlayerInteractHandler;
import me.NinetyNine.ib.eventhandler.IBSignChangeHandler;
import me.NinetyNine.ib.hooks.EconomyHook;
import me.NinetyNine.ib.utils.IBUtils;

public class InfiBuckets extends JavaPlugin {

	public static InfiBuckets plugin;

	@Override
	public void onEnable() {
		plugin = this;

		registerListeners();
		registerCommand();
		InfiBucketsConfig.loadConfig();
		IBUtils.sendConsoleMessage("Enabled!");
	}

	@Override
	public void onDisable() {
		InfiBucketsConfig.saveConfig();
		IBUtils.sendConsoleMessage("Disabled");
	}

	private void registerListeners() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new IBHandler(), this);
		pm.registerEvents(new IBCommands(), this);
		pm.registerEvents(new EconomyHook(), this);
		pm.registerEvents(new IBInvHandler(), this);
		pm.registerEvents(new IBPlayerInteractHandler(), this);
		pm.registerEvents(new IBSignChangeHandler(), this);
	}

	private void registerCommand() {
		getCommand("ib").setExecutor(new IBCommands());
	}
}
