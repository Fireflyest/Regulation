package com.fireflyest.rule.command;

import java.util.UUID;

import com.fireflyest.rule.data.Language;
import com.fireflyest.rule.data.YamlManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fireflyest.rule.manager.EconomyManager;

import net.milkbowl.vault.economy.Economy;

public class MoneyCommand  implements CommandExecutor{
	
	private static Economy econ;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("money")) return true;
		if(econ == null)econ = EconomyManager.getEconomy();
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 0) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			sender.sendMessage(Language.ECONOMY_PLAYER.replace("%player%", "").replace("%money%", ""+econ.getBalance(player)));
			return true;
		}else
		if(args.length == 1) {
			if(!player.hasPermission("rule.money.other"))  { sender.sendMessage(Language.NOT_PERMISSION.replace("%permission%", "rule.money.other")); return true; }
			OfflinePlayer target;
			if(YamlManager.getPlayerDataKeys(args[0], "").contains("uuid")) {
				target = Bukkit.getOfflinePlayer(UUID.fromString(YamlManager.getPlayerData(args[0]).getString("uuid")));
			}else {
				sender.sendMessage(Language.OFFLINE_PLAYER.replace("%player%", args[0]));
				return true; 
			}
			sender.sendMessage(Language.ECONOMY_PLAYER.replace("%player%", args[0]).replace("%money%", ""+econ.getBalance(target)));
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
