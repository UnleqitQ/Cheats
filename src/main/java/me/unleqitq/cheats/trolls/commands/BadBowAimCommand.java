package me.unleqitq.cheats.trolls.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.unleqitq.cheats.Cheats;
import me.unleqitq.cheats.trolls.listeners.BadBowAimListener;
import me.unleqitq.command.Command;


class BadBowAimCommand extends Command {
	
	
	public BadBowAimCommand() {
		super("badbowaim");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length == 1) {
			try {
				if (Bukkit.getPlayer(args[0]) != null) {
					CraftPlayer p = (CraftPlayer) Bukkit.getPlayer(args[0]);
					BadBowAimListener.values.remove(p.getUniqueId());
				}
			}
			catch (Exception ex) {}
		}
		else if (args.length == 2) {
			try {
				if (Bukkit.getPlayer(args[0]) != null) {
					CraftPlayer p = (CraftPlayer) Bukkit.getPlayer(args[0]);
					double ratio = Double.valueOf(args[1]);
					ratio = Math.abs(ratio);
					BadBowAimListener.values.put(p.getUniqueId(), ratio);
				}
			}
			catch (Exception ex) {}
		}
	}
	
	@Override
	public List<String> tab(CommandSender sender, String[] args) {
		if (args.length == 1) {
			ArrayList<String> l = new ArrayList<>();
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (player.getName().contains(args[0])) {
					l.add(player.getName());
				}
			}
			return l;
		}
		return Collections.emptyList();
	}
	
}
