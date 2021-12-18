package me.unleqitq.cheats.listeners;

import me.unleqitq.cheats.Cheats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.FluidCollisionMode;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.RayTraceResult;
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
	
	
}
