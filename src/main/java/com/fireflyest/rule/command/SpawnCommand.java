package com.fireflyest.rule.command;

import com.fireflyest.rule.data.Config;
import com.fireflyest.rule.data.Language;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fireflyest.rule.manager.TeleportManager;

public class SpawnCommand  implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("spawn")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 0) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			TeleportManager.teleportTo(player, Bukkit.getWorld(Config.MAIN_WORLD).getSpawnLocation(), player.hasPermission("rule.vip"));
			player.sendMessage(Language.TELEPORT_POINT.replace("%point%", "spawn"));
			return true;
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
