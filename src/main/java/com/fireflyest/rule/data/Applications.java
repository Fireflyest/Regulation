package com.fireflyest.rule.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.fireflyest.rule.Regulation;

public class Applications {
	
	public static Applications getInstance() { return app; }
	
	private static Applications app = new Applications();
	
	private static Plugin plugin;
	private static Map<UUID, UUID> tpa = new HashMap<>();
	private static Map<UUID, UUID> tphere = new HashMap<>();
	
	private Applications() {
	}
	
	public static void iniApplications() {
		plugin = Regulation.getInstance();
	}
	
	public static void addTpa(UUID tper, UUID receiver) {
		tpa.put(receiver, tper);
		timeOut(receiver);
	}
	
	public static void removeTpa(UUID receiver) {
		tpa.remove(receiver);
	}
	
	public static boolean containsTpaReciver(UUID receiver) {
		return tpa.containsKey(receiver);
	}
	
	public static UUID getTpaer(UUID receiver) {
		return tpa.get(receiver);
	}
	
	public static void addTphere(UUID tper, UUID reciver) {
		tphere.put(reciver, tper);
		timeOut(reciver);
	}
	
	public static void removeTphere(UUID receiver) {
		tphere.remove(receiver);
	}
	
	public static boolean containsTphereReceiver(UUID receiver) {
		return tphere.containsKey(receiver);
	}
	
	public static UUID getTpherer(UUID receiver) {
		return tphere.get(receiver);
	}

	private static void timeOut(UUID receiver) {
		new BukkitRunnable() {
			int time = 0;
			@Override
			public void run() {
				time++;
				if(time == 30) {
					if(tpa.containsKey(receiver))tpa.remove(receiver);
					if(tphere.containsKey(receiver))tphere.remove(receiver);
					cancel();
					return;
				}
			}
		}.runTaskTimer(plugin, 0L, 20L);
	}
	
}
