package com.fireflyest.rule.command;

import com.fireflyest.rule.data.Language;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fireflyest.rule.data.Ephemeral;

public class MessageCommand  implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("m")) return true;
		if(args.length >= 2) {
			Player target = Bukkit.getPlayer(args[0]);
			if(target == null) {sender.sendMessage(Language.OFFLINE_PLAYER.replace("%player%", args[0])); return true; }
			String message = "";
			for(int i = 1 ; i < args.length ; i++) {
				message.concat(" "+args[i]);
			}
			sender.sendMessage(Language.TITLE+"悄悄对§3"+args[0] + "§f说 §7▶§f" + message);
			target.sendMessage(Language.TITLE+"§3"+sender.getName()+"§f悄悄对你说 §7▶§f" + message);
			return true;
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
