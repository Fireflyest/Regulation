package com.fireflyest.rule.manager;

import com.fireflyest.rule.data.Config;
import com.fireflyest.rule.data.YamlManager;
import com.fireflyest.rule.time.ScriptTime;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.fireflyest.rule.Regulation;

public class PermissionManager {
	
	public static PermissionManager getInstance() { return manager; }
	
	private static PermissionManager manager = new PermissionManager();
	
	private static Plugin plugin;
	private static PermissibleBase base;
	
	private PermissionManager() {
		
	}
	
	public static void iniPermissionManager() {
		plugin = Regulation.getInstance();
		base = new PermissibleBase(null);
		base.clearPermissions();
	}
	
	public static void addAttachments(Player player, int level) {
		if(player.isOp())return;
		new BukkitRunnable() {
			@Override
			public void run() {
				if(level > 3){
					long time = YamlManager.getPlayerData(player.getName()).getLong("vip");
					if(time < ScriptTime.getDate()){
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "group "+player.getName()+"1");
						YamlManager.setPlayerData(player.getName(), "vip", null);
						return;
					}
				}
				if(YamlManager.getPlayerDataKeys(player.getName(), "").contains("permission")) {
					for(String permission : YamlManager.getPlayerDataKeys(player.getName(), "permission")) {
						boolean value = YamlManager.getPlayerData(player.getName()).getBoolean("permission."+permission, false);
						player.addAttachment(plugin, permission.replace("_", "."), value);
					}
				}
				for(int i = 0 ; i <= level ; i++) {
					if(!Config.PERMISSION.containsKey("G"+i))continue;
					for(String permission : Config.PERMISSION.get("G"+i)) {
						String[] p = permission.split(",");
						player.addAttachment(plugin, p[0], Boolean.parseBoolean(p[1]));
					}
				}
				cancel();
				return;
			}
		}.runTask(plugin);
	}
	
}
