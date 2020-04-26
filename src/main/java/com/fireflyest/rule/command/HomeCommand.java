package com.fireflyest.rule.command;

import com.fireflyest.rule.convert.ScriptConvert;
import com.fireflyest.rule.data.Language;
import com.fireflyest.rule.data.YamlManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fireflyest.rule.manager.TeleportManager;

public class HomeCommand  implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("home")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 0) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			player.sendMessage(YamlManager.getPlayerDataKeys(player.getName(), "").contains("home") ? Language.TITLE+YamlManager.getPlayerDataKeys(player.getName(), "home").toString() : Language.HAVENOT_SETTLE.replace("%home%", ""));
			return true;
		}else
		if(args.length == 1) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			if(!YamlManager.getPlayerDataKeys(player.getName(), "home").contains(args[0])) { player.sendMessage(Language.HAVENOT_SETTLE.replace("%home%", args[0])); return true; }
			TeleportManager.teleportTo(player, ScriptConvert.convertLocation(YamlManager.getPlayerData(player.getName()).getString("home."+args[0])), player.hasPermission("rule.vip"));
			player.sendMessage(Language.TELEPORT_POINT.replace("%point%", "home("+args[0]+")"));
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
