package me.unleqitq.cheats.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.unleqitq.cheats.Cheats;
import me.unleqitq.cheats.tasks.ArrowProtectTask;
import me.unleqitq.cheats.tasks.ForceFieldTask;
import me.unleqitq.command.Command;


class ForceFieldCommand extends Command {
	
	
	public ForceFieldCommand() {
		super("forcefield");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			if (args.length == 2) {
				try {
					double range = Double.valueOf(args[0]);
					double force = Double.valueOf(args[1]);
					range = Math.abs(range);
					ForceFieldTask.values.put(((Player) sender).getUniqueId(), range);
					ForceFieldTask.forces.put(((Player) sender).getUniqueId(), force);
				}
				catch (Exception ex) {}
			}
			else if (args.length == 1) {
				try {
					double range = Double.valueOf(args[0]);
					range = Math.abs(range);
					ForceFieldTask.values.put(((Player) sender).getUniqueId(), range);
					ForceFieldTask.forces.put(((Player) sender).getUniqueId(), 1.0);
				}
				catch (Exception ex) {}
			}
			else if (args.length == 0) {
				ForceFieldTask.values.remove(((Player) sender).getUniqueId());
				ForceFieldTask.forces.remove(((Player) sender).getUniqueId());
			}
		}
	}
	
}
