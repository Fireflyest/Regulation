package com.fireflyest.rule.command;

import com.fireflyest.rule.data.Language;
import com.fireflyest.rule.item.ScriptItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NameCommand  implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("name")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 1) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			if(player.getInventory().getItemInMainHand().getType().name().equals("AIR"))return true;
			ScriptItem.setDisplayName(player.getInventory().getItemInMainHand(), args[0].replace("&", "§"));
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
