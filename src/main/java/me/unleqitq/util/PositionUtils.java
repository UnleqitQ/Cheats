package me.unleqitq.util;

import org.bukkit.Location;
import org.bukkit.util.Vector;


public class PositionUtils {
	
	
	private PositionUtils() {}
	
	public static Vector convert(Location loc) {
		return new Vector(loc.getX(), loc.getY(), loc.getZ());
	}
	
}
