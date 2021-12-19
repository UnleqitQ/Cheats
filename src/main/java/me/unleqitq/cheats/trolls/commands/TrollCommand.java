package me.unleqitq.cheats.trolls.commands;

import org.bukkit.command.CommandSender;

import me.unleqitq.command.Command;


public class TrollCommand extends Command {
	
	public TrollCommand() {
		super("ctroll");
		register(new BadBowAimCommand());
		register(new BadPearlAimCommand());
		register(new HurtCommand());
		register(new TameTrollCommand());
	}
	
	
}
