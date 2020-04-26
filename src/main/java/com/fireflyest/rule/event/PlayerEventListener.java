package com.fireflyest.rule.event;

import com.fireflyest.rule.chat.ScriptChat;
import com.fireflyest.rule.convert.DeathMsgConvert;
import com.fireflyest.rule.convert.ScriptConvert;
import com.fireflyest.rule.data.Config;
import com.fireflyest.rule.data.Language;
import com.fireflyest.rule.data.YamlManager;
import com.fireflyest.rule.time.ScriptTime;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.fireflyest.rule.Regulation;
import com.fireflyest.rule.command.RuleCommands;
import com.fireflyest.rule.data.Ephemeral;
import com.fireflyest.rule.manager.PermissionManager;


public class PlayerEventListener  implements Listener{
	
	private static Plugin plugin;
	
	public PlayerEventListener() {
		plugin = Regulation.getInstance();
	}
	
	@EventHandler
    public void onLogin(PlayerJoinEvent event) {
		String name = event.getPlayer().getName();
		Player player = event.getPlayer();
		YamlManager.setup("PlayerData", name);
		new BukkitRunnable() {
			@Override
			public void run() {	
				if(!YamlManager.getPlayerDataKeys(name, "").contains("uuid")) {
					YamlManager.setPlayerData(name, "uuid", player.getUniqueId().toString());
					YamlManager.setPlayerData(name, "level", 0);
					if(!YamlManager.getPlayerDataKeys(name, "").contains("money"))YamlManager.setPlayerData(name, "money", 50.0);
					YamlManager.setPlayerData(name, "prefix.NEW", "6,"+(ScriptTime.getDate()+86400000*7));
					YamlManager.setPlayerData(name, "prefix.Lv0", "7,"+-1);
				}
				Ephemeral.putLevel(name, YamlManager.getPlayerData(name).getInt("level"));
				String prefix = YamlManager.getPlayerDataKeys(name, "").contains("using") ?
						YamlManager.getPlayerData(name).getString("using", "NULL") : Config.PREFIX.get("G"+Ephemeral.getLevel(player.getName())).replace("&", "§");
				player.setDisplayName("§f[" +prefix+ "§f]§b" + player.getName() + "§f");
				player.setPlayerListName(player.getDisplayName());
				PermissionManager.addAttachments(player, Ephemeral.getLevel(name));
				cancel();
			}
		}.runTask(plugin);
		new BukkitRunnable() {
			@Override
			public void run() {
				player.setPlayerListHeader(Language.LIST_HEADER);
//				player.performCommand("delay");
				if(!player.isOnline())cancel();
			}
		}.runTaskTimer(plugin, 0L, 160L);
	}

	@EventHandler
	public void onTabComplete(TabCompleteEvent event) {
		String[] command = event.getBuffer().split(" ");
		if(!command[0].equals("/rule"))return;
		if(command.length == 1)event.setCompletions(RuleCommands.getCommands());
	}
	
	private static final CharSequence c = "\\";
	@EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
		event.setCancelled(true);
		if(Ephemeral.isMute(event.getPlayer().getName()))return;
		if(event.getMessage().contains(c))return;
		new BukkitRunnable() {
			@Override
			public void run() {
				if(event.getPlayer().hasPermission("rule.chat.color"))event.setMessage(event.getMessage().replace("&", "§"));
				if(event.getMessage().contains("@")){
					String[] msg = event.getMessage().split(" ");
					for(String demo : msg){
						if(!demo.contains("@"))continue;
						Player target = Bukkit.getPlayer(demo.substring(1));
						if(target == null)break;
						event.setMessage(event.getMessage().replace(demo, "§6@§b"+target.getName()+"§r"));
						target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
					}
				}
				for(Player player : event.getRecipients()) {
					String prefix = event.getPlayer().getDisplayName().replace(event.getPlayer().getName(), "");
					ScriptChat.sendMessage(prefix, event.getPlayer().getName(), player, ScriptTime.getTime(), event.getMessage());
				}
				cancel();
			}
		}.runTask(plugin);
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Location loc = event.getEntity().getLocation();
		Ephemeral.putBack(event.getEntity().getName(), ScriptConvert.convertLocation(loc));
		String name = event.getEntity().getName();
		String msg = event.getDeathMessage()+"";
		msg = DeathMsgConvert.convertDeathMsg(name, msg, event.getEntity().getLastDamageCause().getCause());
		event.setDeathMessage("§6☠ §f"+ msg);
		new BukkitRunnable() {
			@Override
			public void run() {
				event.getEntity().spigot().respawn();
				cancel();
			}
		}.runTask(plugin);
	}
	
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		Location loc = event.getFrom();
		Ephemeral.putBack(event.getPlayer().getName(), ScriptConvert.convertLocation(loc));
	}
	
	@EventHandler
	public void onPlayerItemConsume(PlayerItemConsumeEvent event){
		if(event.getItem().getAmount() < 0)event.getItem().setAmount(0);
	}
	
	@EventHandler
	public void onPlayerArmorStandManipulate(PlayerArmorStandManipulateEvent event){
		if(!event.getPlayer().getWorld().getName().equals(Config.MAIN_WORLD)) { return; }
		if(!event.getPlayer().hasPermission("rule.spawn.build")) event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event){
		if(!event.getPlayer().getWorld().getName().equals(Config.MAIN_WORLD)) { return; }
		if(!event.getPlayer().hasPermission("rule.spawn.build")) event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerFillEmpty(PlayerBucketFillEvent event){
		if(!event.getPlayer().getWorld().getName().equals(Config.MAIN_WORLD)) { return; }
		if(!event.getPlayer().hasPermission("rule.spawn.build")) event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
//		if(!event.getPlayer().getWorld().getName().equals(Config.MAIN_WORLD)) { return; }
//		if(!event.getPlayer().hasPermission("rule.spawn.build")) event.setCancelled(true);
	}

	@EventHandler
	public void PlayerInteractEntity(PlayerInteractEntityEvent event){
		if(!event.getRightClicked().getType().name().equals("PLAYER"))return;

	}
	
}
