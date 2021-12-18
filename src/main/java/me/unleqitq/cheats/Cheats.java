package me.unleqitq.cheats;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnull;

import me.unleqitq.cheats.listeners.TameListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.scheduler.CraftScheduler;
import org.bukkit.plugin.java.JavaPlugin;

import me.unleqitq.cheats.commands.CheatCommand;
import me.unleqitq.cheats.listeners.AttractionListener;
import me.unleqitq.cheats.listeners.ReachListener;
import me.unleqitq.cheats.listeners.ReverseListener;
import me.unleqitq.cheats.tasks.ArrowProtectTask;
import me.unleqitq.cheats.tasks.ForceFieldTask;
import me.unleqitq.cheats.tasks.ItemForceFieldTask;
import me.unleqitq.cheats.trolls.commands.TrollCommand;
import me.unleqitq.cheats.trolls.listeners.BadBowAimListener;
import me.unleqitq.cheats.trolls.listeners.BadPearlAimListener;


public class Cheats extends JavaPlugin {
	
	public static Cheats plugin;
	public static ConcurrentHashMap<UUID, Double> reach;
	public static ConcurrentHashMap<UUID, Double> attraction;
	
	
	public Cheats() {
		plugin = this;
		reach = new ConcurrentHashMap<>();
		attraction = new ConcurrentHashMap<>();
	}
	
	@Override
	public void onEnable() {
		
		registerCommand("cheats", new CheatCommand());
		registerCommand("ctroll", new TrollCommand());
		
		new ReachListener();
		new ReverseListener();
		new AttractionListener();
		new TameListener();
		
		new BadBowAimListener();
		new BadPearlAimListener();
		
		new ArrowProtectTask();
		new ForceFieldTask();
		new ItemForceFieldTask();
	}
	
	@Override
	public void onDisable() {
		((CraftScheduler) Bukkit.getScheduler()).cancelTasks(plugin);
	}
	
	@Nonnull
	public <T extends CommandExecutor> void registerCommand(String cmd, T handler) {
		getCommand(cmd).setExecutor((CommandExecutor) handler);
	}
	
	public static void writePlayer(CommandSender sender, String... messages) {
		for (String message : messages) {
			sender.sendMessage(ChatColor.DARK_RED + "[Cheats] " + ChatColor.GOLD + message);
		}
	}
	
	public static void log(String... messages) {
		for (String message : messages) {
			Bukkit.getLogger().info(ChatColor.DARK_RED + "[Cheats] " + ChatColor.GOLD + message);
		}
	}
	
}
