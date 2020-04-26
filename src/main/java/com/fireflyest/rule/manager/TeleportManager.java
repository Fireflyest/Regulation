package com.fireflyest.rule.manager;

import java.util.HashSet;
import java.util.Set;

import com.fireflyest.rule.Regulation;
import com.fireflyest.rule.data.Language;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TeleportManager {

	private static Plugin plugin;
	
	private static Set<String> waiting = new HashSet<>();
	
	private TeleportManager() {
		
	}
	
	public static void iniTeleportManager() {
		plugin = Regulation.getInstance();
	}
	
	public static void teleportTo(Player player, Location loc, boolean vip) {
		if(waiting.contains(player.getName())) { player.sendMessage(Language.TITLE+"waiting..."); return; }
		if(!loc.getChunk().isLoaded())loc.getChunk().load();
		double t , max; t = max = vip ? 1 : 3;
		player.playSound(player.getLocation(), Sound.BLOCK_PORTAL_AMBIENT, 1, 1);
		new BukkitRunnable() {
			Location now = player.getLocation();
			BossBar coolbar = Bukkit.getServer().createBossBar("", BarColor.WHITE, BarStyle.SOLID, BarFlag.PLAY_BOSS_MUSIC);
			double time = t;
			@Override
			public void run() {
				if(player.getLocation().distance(now) > 1.5) {
					player.sendMessage(Language.CANCEL_WAITING);
					waiting.remove(player.getName());
		    		coolbar.setVisible(false);
		    		coolbar.removeAll();
					cancel();
					return;
				}
				if(!coolbar.isVisible()) coolbar.setVisible(true);
		    	if(!coolbar.getPlayers().contains(player)) coolbar.addPlayer(player);
				if(time <= 0) {
					player.teleport(loc);
					waiting.remove(player.getName());
		    		coolbar.setVisible(false);
		    		coolbar.removeAll();
					cancel();
					return;
				}else {
			    	coolbar.setProgress(time/max);
				}
				time-=0.05;
			}
		}.runTaskTimer(plugin, 0L, 1L);
	}
	
}
