package com.fireflyest.rule.event;

import com.fireflyest.rule.data.Language;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class OtherEventListener   implements Listener{
	
	@EventHandler
    public void onServerListPing(ServerListPingEvent event) {
		event.setMaxPlayers(100);
		event.setMotd(Language.MOTD);
	}
	
}
