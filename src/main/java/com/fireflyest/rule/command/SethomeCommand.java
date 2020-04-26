package com.fireflyest.rule.command;

import com.fireflyest.rule.convert.ScriptConvert;
import com.fireflyest.rule.data.Language;
import com.fireflyest.rule.data.YamlManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class SethomeCommand  implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("sethome")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 0) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			player.sendMessage(YamlManager.getPlayerDataKeys(player.getName(), "").contains("home") ? Language.TITLE+YamlManager.getPlayerDataKeys(player.getName(), "home").toString() : Language.HAVENOT_SETTLE.replace("%home%", ""));
			return true;
		}else
		if(args.length == 1) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			if(YamlManager.getPlayerDataKeys(player.getName(), "").contains("home")) {
				Set<String> home = YamlManager.getPlayerDataKeys(player.getName(), "home");
				if(!home.contains(args[0])){
					if(home.size() >= 3 && player.hasPermission("rule.vip")) {
						player.sendMessage(Language.MAXIMUN_AMOUNT);
						return true;
					}
					if(home.size() >= 5) {
						player.sendMessage(Language.MAXIMUN_AMOUNT);
						return true;
					}
				}
			}
			YamlManager.setPlayerData(player.getName(), "home."+args[0], ScriptConvert.convertLocation(player.getLocation()));
			player.sendMessage(Language.SUCCEED_SETTLE.replace("%home%", args[0]));
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
