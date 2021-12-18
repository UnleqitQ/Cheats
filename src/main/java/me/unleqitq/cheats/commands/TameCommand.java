package me.unleqitq.cheats.commands;

import me.unleqitq.cheats.Cheats;
import me.unleqitq.cheats.listeners.ReverseListener;
import me.unleqitq.cheats.listeners.TameListener;
import me.unleqitq.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


class TameCommand extends Command {
	
	
	public TameCommand() {
		super("tame");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			if (args.length == 0) {
				boolean prev = TameListener.enabled.getOrDefault(((Player) sender).getUniqueId(), false);
				TameListener.enabled.put(((Player) sender).getUniqueId(), !prev);
			}
		}
	}
	
}
