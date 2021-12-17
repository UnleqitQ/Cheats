package me.unleqitq.cheats.listeners;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
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


public class ReachListener implements Listener {
	
	public static ConcurrentHashMap<UUID, Boolean> ignoreBlocks;
	public static ConcurrentHashMap<UUID, Double> size;
	
	public ReachListener() {
		Bukkit.getPluginManager().registerEvents(this, Cheats.plugin);
		size = new ConcurrentHashMap<>();
		ignoreBlocks = new ConcurrentHashMap<>();
	}
	
	@EventHandler
	public void onHit(PlayerInteractEvent event) {
		if (Cheats.reach.containsKey(event.getPlayer().getUniqueId())) {
			CraftPlayer p = (CraftPlayer) event.getPlayer();
			if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
				double range = Cheats.reach.get(p.getUniqueId());
				RayTraceResult result;
				if (ignoreBlocks.getOrDefault(p.getUniqueId(), false)) {
					result = ((CraftWorld) p.getWorld()).rayTraceEntities(p.getEyeLocation(),
							p.getEyeLocation().getDirection(), range, size.getOrDefault(p.getUniqueId(), 0.0),
							(entity) -> {
								return entity != p;
							});
				}
				else {
					result = ((CraftWorld) p.getWorld()).rayTrace(p.getEyeLocation(),
							p.getEyeLocation().getDirection(), range, FluidCollisionMode.NEVER, true,
							size.getOrDefault(p.getUniqueId(), 0.0), (entity) -> {
								return entity != p;
							});
				}
				if (result != null) {
					if (result.getHitEntity() != null) {
						Entity entity = result.getHitEntity();
						if (entity instanceof Damageable
								&& entity.getLocation().toVector().distance(p.getEyeLocation().toVector()) > 0) {
							Damageable e = (Damageable) entity;
							p.attack(e);
							Cheats.writePlayer(p, String.format("Used reach on %s %s %s(%01.2f m)",
									e.getType(), e.getName(), ChatColor.DARK_PURPLE,
									result.getHitPosition().distance(p.getLocation().toVector())));
						}
					}
				}
				else {
					Cheats.log("Got null as RayCast result");
				}
			}
		}
	}
	
	
}
