package com.fireflyest.rule.command;

import com.fireflyest.rule.data.Language;
import com.fireflyest.rule.item.ScriptItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LoreCommand  implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("lore")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 2) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			if(player.getInventory().getItemInMainHand().getType().name().equals("AIR"))return true;
			ScriptItem.setLore(player.getInventory().getItemInMainHand(), Integer.valueOf(args[0]), args[1].replace("&", "§"));
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
