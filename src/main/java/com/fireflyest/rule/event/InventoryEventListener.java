package com.fireflyest.rule.event;

import java.util.List;

import com.fireflyest.rule.item.ScriptItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.fireflyest.rule.Regulation;

public class InventoryEventListener   implements Listener{
	
	@EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
    	if(event.getCurrentItem() == null || event.getView().getTitle() == null) return;
		if(!event.getView().getTitle().contains("⚖")) return;
		event.setCancelled(true);
		new BukkitRunnable() {
			@Override
			public void run() {
				HumanEntity player = event.getWhoClicked();
		    	if(!event.getCurrentItem().hasItemMeta()) { cancel(); return; }
				if(!event.getCurrentItem().getItemMeta().hasLore()) { cancel(); return; }
		    	if(!event.getCurrentItem().getItemMeta().getLore().get(0).contains("⚖")) { cancel(); return; }
		    	List<String> lores = event.getCurrentItem().getItemMeta().getLore();
				String type = lores.get(0).substring(5);
				event.getWhoClicked().closeInventory();
				if("home".equals(type)) {
					Bukkit.dispatchCommand(player, (event.isShiftClick() ? "set"+type : type) +" "+ ScriptItem.getItemValue(lores.get(1)));
				}else if("plugin".equals(type)){
					Bukkit.dispatchCommand(player, type+" "+event.getCurrentItem().getItemMeta().getDisplayName().replace("[§e", "").replace("§f]", ""));
				}else if("prefix".equals(type)){
					Bukkit.dispatchCommand(player, type+" "+event.getCurrentItem().getItemMeta().getDisplayName().substring(1).replace("§f]", ""));
				}else {
					Bukkit.dispatchCommand(player, type);
				}
		    	cancel(); return; 
			}
		}.runTask(Regulation.getInstance());
	}
	
}
