package me.unleqitq.cheats.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.unleqitq.cheats.Cheats;
import me.unleqitq.cheats.listeners.ReachListener;
import me.unleqitq.command.Command;


class ReachCommand extends Command {
	
	
	public ReachCommand() {
		super("reach");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			if (args.length >= 1) {
				try {
					double range = Double.valueOf(args[0]);
					Cheats.reach.put(((Player) sender).getUniqueId(), range);
					if (args.length >= 2) {
						try {
							double size = Double.valueOf(args[1]);
							ReachListener.size.put(((Player) sender).getUniqueId(), size);
						}
						catch (NumberFormatException ex) {
							boolean ignoreBlocks = Boolean.valueOf(args[1]);
							ReachListener.ignoreBlocks.put(((Player) sender).getUniqueId(), ignoreBlocks);
						}
						if (args.length >= 3) {
							try {
								double size = Double.valueOf(args[1]);
								ReachListener.size.put(((Player) sender).getUniqueId(), size);
							}
							catch (NumberFormatException ex) {
								boolean ignoreBlocks = Boolean.valueOf(args[1]);
								ReachListener.ignoreBlocks.put(((Player) sender).getUniqueId(), ignoreBlocks);
							}
						}
					}
				}
				catch (Exception ex) {}
			}
			else if (args.length == 0) {
				Cheats.reach.remove(((Player) sender).getUniqueId());
				ReachListener.ignoreBlocks.remove(((Player) sender).getUniqueId());
				ReachListener.size.remove(((Player) sender).getUniqueId());
			}
		}
	}
	
}
