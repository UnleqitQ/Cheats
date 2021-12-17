package me.unleqitq.cheats.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.unleqitq.cheats.Cheats;
import me.unleqitq.cheats.tasks.ArrowProtectTask;
import me.unleqitq.cheats.tasks.ForceFieldTask;
import me.unleqitq.cheats.tasks.ItemForceFieldTask;
import me.unleqitq.command.Command;


class ItemForceFieldCommand extends Command {
	
	
	public ItemForceFieldCommand() {
		super("itemforcefield");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			if (args.length == 2) {
				try {
					double range = Double.valueOf(args[0]);
					double force = Double.valueOf(args[1]);
					range = Math.abs(range);
					ItemForceFieldTask.values.put(((Player) sender).getUniqueId(), range);
					ItemForceFieldTask.forces.put(((Player) sender).getUniqueId(), force);
				}
				catch (Exception ex) {}
			}
			else if (args.length == 1) {
				try {
					double range = Double.valueOf(args[0]);
					range = Math.abs(range);
					ItemForceFieldTask.values.put(((Player) sender).getUniqueId(), range);
					ItemForceFieldTask.forces.put(((Player) sender).getUniqueId(), 1.0);
				}
				catch (Exception ex) {}
			}
			else if (args.length == 0) {
				ItemForceFieldTask.values.remove(((Player) sender).getUniqueId());
				ItemForceFieldTask.forces.remove(((Player) sender).getUniqueId());
			}
		}
	}
	
}
