package me.unleqitq.cheats.listeners;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_17_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftProjectile;
import org.bukkit.craftbukkit.v1_17_R1.util.CraftRayTraceResult;
import org.bukkit.craftbukkit.v1_17_R1.util.CraftVector;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExhaustionEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import me.unleqitq.cheats.Cheats;


public class ReverseListener implements Listener {
	
	public static ConcurrentHashMap<UUID, Double> values;
	
	
	public ReverseListener() {
		Bukkit.getPluginManager().registerEvents(this, Cheats.plugin);
		values = new ConcurrentHashMap<>();
	}
	
	@EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onDamage(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
			if (values.containsKey(event.getEntity().getUniqueId())) {
				double ratio = values.get(event.getEntity().getUniqueId());
				if (event.getDamager() != null && event.getDamager() instanceof Damageable) {
					Damageable entity = (Damageable) event.getDamager();
					if (entity instanceof Player && values.containsKey(entity.getUniqueId()))
						return;
					entity.damage(event.getDamage() * ratio / 100.0, event.getEntity());
					Cheats.writePlayer(event.getEntity(),
						String.format("Returned %1.2f %s of in Total %1.2f Damage", ratio, "%",
							(double) event.getDamage()));
				}
				else if (event.getDamager() != null && event.getDamager() instanceof Projectile) {
					Projectile projectile = (Projectile) event.getDamager();
					if (projectile.getShooter() != null && projectile.getShooter() instanceof Damageable) {
						Damageable entity = (Damageable) projectile.getShooter();
						if (entity instanceof Player && values.containsKey(entity.getUniqueId()))
							return;
						double damage = event.getDamage();
						Cheats.writePlayer(event.getEntity(), "" + damage);
						entity.damage(event.getDamage() * ratio / 100.0, event.getEntity());
						// System.out.println(event.getDamage());
						Cheats.writePlayer(event.getEntity(),
							String.format("Returned %1.2f %s of in Total %1.2f Damage", ratio, "%",
								damage));
					}
				}
			}
		}
	}
	
	
	
}
