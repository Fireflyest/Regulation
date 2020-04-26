package com.fireflyest.rule.command;

import com.fireflyest.rule.convert.ScriptConvert;
import com.fireflyest.rule.data.Config;
import com.fireflyest.rule.data.Language;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fireflyest.rule.manager.TeleportManager;

public class WarpCommand  implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("warp")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 0) {
			sender.sendMessage(Language.TITLE+ Config.WARPS.keySet());
		}else
		if(args.length == 1) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			if(!player.hasPermission("rule.warp."+args[0]))  { sender.sendMessage(Language.NOT_PERMISSION.replace("%permission%", "rule.warp."+args[0])); return true; }
			if(!Config.WARPS.keySet().contains(args[0])) { sender.sendMessage(Language.HAVENOT_SETPOI.replace("%point%", args[0])); return true; }
			String loc = Config.WARPS.get(args[0]);
			TeleportManager.teleportTo(player, ScriptConvert.convertLocation(loc), player.hasPermission("rule.vip"));
			player.sendMessage(Language.TELEPORT_POINT.replace("%point%", args[0]));
			return true;
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
