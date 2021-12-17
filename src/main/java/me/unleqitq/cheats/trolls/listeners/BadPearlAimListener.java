package me.unleqitq.cheats.trolls.listeners;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftArrow;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEnderPearl;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.util.Vector;

import me.unleqitq.cheats.Cheats;


public class BadPearlAimListener implements Listener {
	
	public static ConcurrentHashMap<UUID, Double> values;
	
	
	public BadPearlAimListener() {
		Bukkit.getPluginManager().registerEvents(this, Cheats.plugin);
		values = new ConcurrentHashMap<>();
	}
	
	@EventHandler
	public void onThrow(ProjectileLaunchEvent event) {
		if (event.getEntity() != null) {
			if (event.getEntityType() == EntityType.ENDER_PEARL) {
				CraftEnderPearl pearl = (CraftEnderPearl) event.getEntity();
				if (pearl.getShooter() instanceof Player) {
					CraftPlayer p = (CraftPlayer) pearl.getShooter();
					if (values.containsKey(p.getUniqueId())) {
						double ratio = values.get(p.getUniqueId());
						if (pearl.getVelocity().length() > 0) {
							Vector vel = pearl.getVelocity();
							Vector d1 = new Vector().add(vel);
							if (vel.getX() != vel.getY() || vel.getY() != vel.getZ()) {
								d1.add(new Vector(vel.getY(), vel.getZ(), vel.getX()));
							}
							else {
								d1.add(new Vector(-2 * vel.getY(), -vel.getZ(), vel.getX()));
							}
							Vector d2 = vel.getCrossProduct(d1);
							d2.normalize();
							d1 = vel.getCrossProduct(d2);
							d1.normalize();
							d1.multiply((Math.random() * 2 - 1) * ratio * 0.01 * vel.length());
							d2.multiply((Math.random() * 2 - 1) * ratio * 0.01 * vel.length());
							vel.add(d1).add(d2);
							pearl.setVelocity(vel);
						}
					}
				}
			}
		}
	}
	
}
