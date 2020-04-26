package com.fireflyest.rule.command;

import com.fireflyest.rule.convert.ScriptConvert;
import com.fireflyest.rule.data.Language;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fireflyest.rule.data.Ephemeral;
import com.fireflyest.rule.manager.TeleportManager;
import org.jetbrains.annotations.NotNull;

public class BackCommand  implements CommandExecutor{
	
	@Override
	public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, @NotNull String[] args) {
		if(!cmd.getName().equalsIgnoreCase("back")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 0) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			TeleportManager.teleportTo(player, ScriptConvert.convertLocation(Ephemeral.getBack(player.getName())), player.hasPermission("rule.vip"));
			player.sendMessage(Language.TELEPORT_POINT.replace("%point%", "back"));
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
