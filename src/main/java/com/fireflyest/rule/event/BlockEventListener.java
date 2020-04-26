package com.fireflyest.rule.event;

import com.fireflyest.rule.data.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockDispenseArmorEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;

public class BlockEventListener   implements Listener{
	
	@EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
		if(event.getCause().name().equals("SPREAD") || event.getCause().name().equals("ARROW"))event.setCancelled(true);
	}
	
	@EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event) {
		if(!event.getBlock().getWorld().getName().equals(Config.MAIN_WORLD)) { return; }
		if(event.getBlock().getType().name().contains("RAIL")) { event.setCancelled(true); return; }
		if(event.getBlock().getType().name().contains("LADDER")) { event.setCancelled(true); return; }
		if(event.getBlock().getType().name().contains("TROCH")) { event.setCancelled(true); return; }
	}
	
	@EventHandler
    public void onBlockRedstone(BlockRedstoneEvent event) {
		
	}
	
	@EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
		if(!event.getBlock().getWorld().getName().equals(Config.MAIN_WORLD)) { return; }
		if(!event.getPlayer().hasPermission("rule.spawn.build")) event.setCancelled(true);
	}
	
	@EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
		if(!event.getBlock().getWorld().getName().equals(Config.MAIN_WORLD)) { return; }
		event.setCancelled(true);
	}
	
	@EventHandler
    public void onBlockDispenseArmor(BlockDispenseArmorEvent event) {
		if(!event.getBlock().getWorld().getName().equals(Config.MAIN_WORLD)) { return; }
		if(!event.getTargetEntity().hasPermission("rule.spawn.build")) event.setCancelled(true);
	}
	
	@EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
		if(!event.getBlock().getWorld().getName().equals(Config.MAIN_WORLD)) { return; }
		if(!event.getPlayer().hasPermission("rule.spawn.build")) event.setCancelled(true);
	}
	
}
