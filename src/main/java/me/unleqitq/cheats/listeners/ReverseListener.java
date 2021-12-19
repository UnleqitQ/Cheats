package me.unleqitq.cheats.listeners;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

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
				if (event.getDamager() instanceof Damageable) {
					Damageable entity = (Damageable) event.getDamager();
					if (entity instanceof Player && values.containsKey(entity.getUniqueId()))
						return;
					entity.damage(event.getDamage() * ratio / 100.0, event.getEntity());
					Cheats.writePlayer(event.getEntity(),
							String.format("Returned %1.2f %s of in Total %1.2f Damage", ratio, "%", event.getDamage()));
				}
				else if (event.getDamager() instanceof Projectile) {
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
								String.format("Returned %1.2f %s of in Total %1.2f Damage", ratio, "%", damage));
					}
				}
			}
		}
	}
	
	
}
