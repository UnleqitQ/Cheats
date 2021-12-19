package me.unleqitq.cheats.listeners;

import me.unleqitq.cheats.Cheats;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.spigotmc.event.entity.EntityMountEvent;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


public class TameListener implements Listener {
	
	public static ConcurrentHashMap<UUID, Boolean> enabled;
	
	public TameListener() {
		Bukkit.getPluginManager().registerEvents(this, Cheats.plugin);
		enabled = new ConcurrentHashMap<>();
	}
	
	@EventHandler
	public void onTame(EntityMountEvent event) {
		if (event.getEntityType() == EntityType.PLAYER) {
			Player player = (Player) event.getEntity();
			if (event.getMount() instanceof Tameable) {
				Tameable entity = (Tameable) event.getMount();
				if (enabled.getOrDefault(player.getUniqueId(), false)) {
					if (!entity.isTamed()) {
						entity.setTamed(true);
						entity.setOwner(player);
						Cheats.writePlayer(player, "Successfully tamed " + entity.getType());
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onTame(PlayerInteractEntityEvent event) {
		if (event.getRightClicked() instanceof Tameable) {
			if (enabled.getOrDefault(event.getPlayer().getUniqueId(), false)) {
				Tameable entity = (Tameable) event.getRightClicked();
				if (!entity.isTamed()) {
					entity.setTamed(true);
					entity.setOwner(event.getPlayer());
					Cheats.writePlayer(event.getPlayer(), "Successfully tamed " + entity.getType());
				}
			}
		}
	}
	
	
}
