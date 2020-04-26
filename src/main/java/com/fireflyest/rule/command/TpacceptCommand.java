package com.fireflyest.rule.command;

import com.fireflyest.rule.data.Language;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fireflyest.rule.data.Applications;
import com.fireflyest.rule.manager.TeleportManager;

public class TpacceptCommand  implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("tpaccept")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 0) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			Player tper;
			if(Applications.containsTpaReciver(player.getUniqueId())) {
				tper = Bukkit.getPlayer(Applications.getTpaer(player.getUniqueId()));
				tper.sendMessage(Language.TELEPORT_POINT.replace("%point%", player.getName()));
				TeleportManager.teleportTo(tper, player.getLocation(),  tper.hasPermission("rule.vip"));
				Applications.removeTpa(player.getUniqueId());
				return true;
			}
			if(Applications.containsTphereReceiver(player.getUniqueId())) {
				tper = Bukkit.getPlayer(Applications.getTpherer(player.getUniqueId()));
				player.sendMessage(Language.TELEPORT_POINT.replace("%point%", tper.getName()));
				TeleportManager.teleportTo(player, tper.getLocation(),  player.hasPermission("rule.vip"));
				Applications.removeTphere(player.getUniqueId());
				return true;
			}
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
