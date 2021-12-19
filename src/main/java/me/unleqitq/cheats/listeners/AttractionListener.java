package me.unleqitq.cheats.listeners;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.RayTraceResult;

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
			Player p = event.getPlayer();
			if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
				if (event.getItem() == null || event.getItem().getType() == Material.AIR) {
					double range = Cheats.attraction.get(p.getUniqueId());
					RayTraceResult result = p.getWorld().rayTrace(p.getEyeLocation(), p.getEyeLocation().getDirection(),
							range, FluidCollisionMode.NEVER, true, 0.0, (entity) -> entity != p);
					if (result != null) {
						if (result.getHitEntity() != null) {
							Entity entity = result.getHitEntity();
							entity.teleport(p);
							Cheats.writePlayer(p,
									String.format("Used attraction on %s %s %s(%01.2f m)", entity.getType(),
											entity.getName(), ChatColor.DARK_PURPLE,
											result.getHitPosition().distance(p.getLocation().toVector())));
							event.setCancelled(true);
						}
					}
				}
			}
		}
	}
	
	
}
