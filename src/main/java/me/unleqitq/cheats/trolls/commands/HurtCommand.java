package me.unleqitq.cheats.trolls.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
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
					Player p = Objects.requireNonNull(Bukkit.getPlayer(args[0]));
					double damage = Double.parseDouble(args[1]);
					p.damage(damage);
					
					Cheats.writePlayer(sender, String.format("Spanked player with %1.2f Damage", damage));
				}
			} catch (NumberFormatException ex) {
				Cheats.writePlayer(sender, "Please type in a number");
			}
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
