package me.unleqitq.cheats.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.unleqitq.cheats.Cheats;
import me.unleqitq.cheats.listeners.ReachListener;
import me.unleqitq.cheats.listeners.ReverseListener;
import me.unleqitq.command.Command;


class ReverseCommand extends Command {
	
	
	public ReverseCommand() {
		super("reverse");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			if (args.length == 1) {
				try {
					double ratio = Double.parseDouble(args[0]);
					if (ratio < 0) {
						Cheats.writePlayer(sender, "ratio has to be at least 0");
					}
					ReverseListener.values.put(((Player) sender).getUniqueId(), ratio);
				} catch (NumberFormatException ex) {
					Cheats.writePlayer(sender, "Please type in a number");
				}
			}
			else if (args.length == 0) {
				ReverseListener.values.remove(((Player) sender).getUniqueId());
			}
		}
	}
	
}
