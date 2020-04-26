package com.fireflyest.rule.event;

import java.util.Map.Entry;

import com.fireflyest.rule.data.Config;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;

import com.fireflyest.rule.data.Ephemeral;

public class ServerEventListener  implements Listener{
	
//	private static Plugin plugin;
	
	public ServerEventListener(){
//		plugin = Regulation.getInstance();
	}
	
	@EventHandler
	 public void onServerLoadEvent(ServerLoadEvent event) {
		new WorldCreator("world_main").createWorld();
		for(Entry<String, Integer> value : Config.BORDER.entrySet()) {
			Bukkit.getWorld(value.getKey()).getWorldBorder().setSize(value.getValue());
		}
	}
	
}
