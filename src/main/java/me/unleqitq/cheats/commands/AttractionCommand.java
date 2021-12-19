package me.unleqitq.cheats.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.unleqitq.cheats.Cheats;
import me.unleqitq.cheats.listeners.AttractionListener;
import me.unleqitq.command.Command;


class AttractionCommand extends Command {
	
	
	public AttractionCommand() {
		super("attraction");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			if (args.length == 1) {
				try {
					double range = Double.parseDouble(args[0]);
					Cheats.attraction.put(((Player) sender).getUniqueId(), range);
				} catch (Exception ex) {
					Cheats.writePlayer(sender, ex.toString());
				}
			}
			else if (args.length == 0) {
				Cheats.attraction.remove(((Player) sender).getUniqueId());
			}
		}
	}
	
}
