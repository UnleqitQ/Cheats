package me.unleqitq.cheats.trolls.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.unleqitq.cheats.Cheats;
import me.unleqitq.command.Command;


class HurtCommand extends Command {
	
	
	public HurtCommand() {
		super("hurt");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length == 2) {
			try {
				if (Bukkit.getPlayer(args[0]) != null) {
					CraftPlayer p = (CraftPlayer) Bukkit.getPlayer(args[0]);
					double damage = Double.valueOf(args[1]);
					p.damage(damage);
					
					Cheats.writePlayer(sender, String.format("Spanked player with %1.2f Damage", damage));
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
