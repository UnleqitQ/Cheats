package me.unleqitq.cheats.commands;

import org.bukkit.command.CommandSender;

import me.unleqitq.command.Command;


public class CheatCommand extends Command {
	
	public CheatCommand() {
		super("cheats");
		register(new ReachCommand());
		register(new ReverseCommand());
		register(new AttractionCommand());
		register(new ArrowProtectCommand());
		register(new ForceFieldCommand());
		register(new ItemForceFieldCommand());
		register(new TameCommand());
	}
	
	
}
