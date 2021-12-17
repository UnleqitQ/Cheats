package me.unleqitq.cheats.listeners;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_17_R1.util.CraftRayTraceResult;
import org.bukkit.craftbukkit.v1_17_R1.util.CraftVector;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExhaustionEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import me.unleqitq.cheats.Cheats;


public class AttractionListener implements Listener {
	
	// public static ConcurrentHashMap<UUID, Double> values;
	
	public AttractionListener() {
		// values = new ConcurrentHashMap<>();
		Bukkit.getPluginManager().registerEvents(this, Cheats.plugin);
	}
	
	@EventHandler
	public void onUse(PlayerInteractEvent event) {
		if (Cheats.attraction.containsKey(event.getPlayer().getUniqueId())) {
			CraftPlayer p = (CraftPlayer) event.getPlayer();
			if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
				if (event.getItem() == null || event.getItem().getType() == Material.AIR) {
					double range = Cheats.attraction.get(p.getUniqueId());
					RayTraceResult result = ((CraftWorld) p.getWorld()).rayTrace(p.getEyeLocation(),
						p.getEyeLocation().getDirection(), range, FluidCollisionMode.NEVER, true, 0.0, (entity) -> {
							return entity != p;
						});
					if (result != null) {
						if (result.getHitEntity() != null) {
							Entity entity = result.getHitEntity();
							CraftEntity e = (CraftEntity) entity;
							e.teleport(p);
							Cheats.writePlayer(p, String.format("Used attraction on %s %s %s(%01.2f m)",
								e.getType(), e.getName(), ChatColor.DARK_PURPLE,
								result.getHitPosition().distance(p.getLocation().toVector())));
							event.setCancelled(true);
						}
					}
				}
			}
		}
	}
	
	
	
}
