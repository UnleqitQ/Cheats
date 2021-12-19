package me.unleqitq.cheats.trolls.listeners;

import me.unleqitq.cheats.Cheats;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


public class TameTrollListener implements Listener {
	
	public static ConcurrentHashMap<UUID, Boolean> enabled;
	
	public TameTrollListener() {
		Bukkit.getPluginManager().registerEvents(this, Cheats.plugin);
		enabled = new ConcurrentHashMap<>();
	}
	
	@EventHandler
	public void onTame(EntityTameEvent event) {
		if (enabled.getOrDefault(event.getOwner().getUniqueId(), false)) {
			event.setCancelled(true);
		}
	}
	
	
}
