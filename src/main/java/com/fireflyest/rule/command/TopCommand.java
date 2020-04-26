package com.fireflyest.rule.command;

import com.fireflyest.rule.data.Language;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fireflyest.rule.manager.TeleportManager;

public class TopCommand  implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("top")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 0) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			Location loc = player.getLocation();
			loc.setY(loc.getChunk().getChunkSnapshot().getHighestBlockYAt(Math.abs((int)loc.getX()%16), Math.abs((int)loc.getZ()%16))+2.5);
			TeleportManager.teleportTo(player, loc, player.hasPermission("rule.vip"));
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
