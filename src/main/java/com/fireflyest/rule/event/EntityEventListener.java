package com.fireflyest.rule.event;

import com.fireflyest.rule.data.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPortalEnterEvent;

public class EntityEventListener   implements Listener{
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		if(event.getEntity().getLocation().getChunk().getEntities().length > 30)event.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityPortalEnter(EntityPortalEnterEvent event) {
		if(!event.getEntityType().isAlive())return;
		if(event.getEntityType().name().equals("TRADER_LLAMA") || event.getEntityType().name().equals("WANDERING_TRADER"))event.getEntity().remove();
	}
	
	@EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if(!event.getEntity().getWorld().getName().equals(Config.MAIN_WORLD)) { return; }
		if(event.getDamager().getType().name().equals("PLAYER")
		&& event.getEntity().getType().name().equals("PLAYER"))event.setCancelled(true);
	}
	
	@EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
		if(!event.getEntity().getWorld().getName().equals(Config.MAIN_WORLD)) { return; }
		event.blockList().clear();
	}
	
}
