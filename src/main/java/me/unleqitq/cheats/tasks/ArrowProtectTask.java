package me.unleqitq.cheats.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import me.unleqitq.cheats.Cheats;


public class ArrowProtectTask implements Runnable {
	
	public static ConcurrentHashMap<UUID, Double> values;
	public static ConcurrentHashMap<UUID, Double> forces;
	BukkitTask task;
	
	public ArrowProtectTask() {
		values = new ConcurrentHashMap<>();
		forces = new ConcurrentHashMap<>();
		task = Bukkit.getScheduler().runTaskTimer(Cheats.plugin, this, 20, 2);
	}
	
	@Override
	public void run() {
		for (Entry<UUID, Double> entry : values.entrySet()) {
			if (Bukkit.getOfflinePlayer(entry.getKey()).isOnline()) {
				Player p = Objects.requireNonNull(Bukkit.getPlayer(entry.getKey()));
				double dist = entry.getValue();
				List<Entity> entities = p.getNearbyEntities(dist, dist, dist);
				for (Entity entity : entities) {
					if (entity != null) {
						if (entity.getLocation().distance(p.getLocation()) <= dist) {
							if (entity instanceof Projectile) {
								Projectile proj = (Projectile) entity;
								if (proj.getShooter() != p || proj.getTicksLived() > 20) {
									Vector vel = proj.getVelocity();
									Vector d = proj.getLocation().toVector().subtract(p.getLocation().toVector());
									double distance = d.length();
									distance = Math.max(distance, 0.05);
									if (forces.getOrDefault(p.getUniqueId(), 1.0) < 0) {
										distance = 1 / distance;
									}
									d.normalize();
									d.multiply(1 / distance).multiply(0.1).multiply(
											forces.getOrDefault(p.getUniqueId(), 1.0));
									if (d.length() > 100) {
										d.multiply(100 / d.length());
									}
									vel.add(d);
									// if (vel.length() > 60) {
									// vel.multiply(60 / vel.length());
									// }
									proj.setVelocity(vel);
								}
							}
						}
					}
				}
			}
		}
	}
	
}
