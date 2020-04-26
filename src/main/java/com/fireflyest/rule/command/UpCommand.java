package com.fireflyest.rule.command;

import com.fireflyest.rule.data.Language;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fireflyest.rule.manager.TeleportManager;

public class UpCommand  implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("up")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 1) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			Location loc = player.getLocation().add(0, Double.valueOf(args[0]), 0); 
			Location f = loc.clone().add(0, -1, 0);
			if(f.getBlock().getType().name().equals("AIR"))f.getBlock().setType(Material.GLASS);
			TeleportManager.teleportTo(player, loc, player.hasPermission("rule.vip"));
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
