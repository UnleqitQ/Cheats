package me.unleqitq.cheats.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.unleqitq.cheats.Cheats;
import me.unleqitq.cheats.tasks.ArrowProtectTask;
import me.unleqitq.cheats.tasks.ForceFieldTask;
import me.unleqitq.command.Command;


class SaturationCommand extends Command {
	
	
	public SaturationCommand() {
		super("saturation");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			if (args.length == 2) {
				try {
					double range = Double.valueOf(args[0]);
					double force = Double.valueOf(args[1]);
					range = Math.abs(range);
					ArrowProtectTask.values.put(((Player) sender).getUniqueId(), range);
					ArrowProtectTask.forces.put(((Player) sender).getUniqueId(), force);
				}
				catch (Exception ex) {}
			}
			else if (args.length == 1) {
				try {
					double range = Double.valueOf(args[0]);
					ArrowProtectTask.values.put(((Player) sender).getUniqueId(), range);
					ArrowProtectTask.forces.put(((Player) sender).getUniqueId(), 1.0);
				}
				catch (Exception ex) {}
			}
			else if (args.length == 0) {
				ArrowProtectTask.values.remove(((Player) sender).getUniqueId());
				ArrowProtectTask.forces.remove(((Player) sender).getUniqueId());
			}
		}
	}
	
}
