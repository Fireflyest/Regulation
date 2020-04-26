package com.fireflyest.rule.command;

import java.util.UUID;

import com.fireflyest.rule.data.Language;
import com.fireflyest.rule.data.YamlManager;
import com.fireflyest.rule.item.ScriptItem;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fireflyest.rule.data.Ephemeral;

public class SkullCommand  implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("skull")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 1) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			OfflinePlayer target = Bukkit.getPlayer(args[0]);
			if(target == null){
				if(!YamlManager.getPlayerDataKeys(args[0], "").contains("uuid")){sender.sendMessage(Language.OFFLINE_PLAYER.replace("%player%", args[0])); return true; }
				target = Bukkit.getOfflinePlayer(UUID.fromString(YamlManager.getPlayerData(args[0]).getString("uuid", "")));
			}
			player.getInventory().addItem(ScriptItem.createSkull(target));
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
