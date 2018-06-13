package me.NinetyNine.ib.eventhandler;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class IBSignChangeHandler implements Listener {

	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		String firstLine = e.getLine(0);

		if (firstLine.equalsIgnoreCase("[IB]")) {
			e.setLine(0, ChatColor.AQUA + "[InfiBuckets]");
			e.setLine(1, ChatColor.LIGHT_PURPLE + "OPEN");
		}
	}
}