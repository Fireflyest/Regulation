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

import com.fireflyest.rule.data.Ephemeral;
import com.fireflyest.rule.manager.EconomyManager;

import net.milkbowl.vault.economy.Economy;

public class PayCommand  implements CommandExecutor{
	
	private static Economy eco;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("pay")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(eco == null) eco = EconomyManager.getEconomy();
		if(args.length == 2) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			double amount = Double.parseDouble(args[1]);
			if(!eco.has(player, amount)) { player.sendMessage(Language.TITLE + "你的麦块币不足"); return true; }
			eco.withdrawPlayer(player, amount);
			OfflinePlayer target = Bukkit.getPlayer(args[0]);
			if(target == null){
				if(!YamlManager.getPlayerDataKeys(args[0], "").contains("uuid")){sender.sendMessage(Language.OFFLINE_PLAYER.replace("%player%", args[0])); return true; }
				target = Bukkit.getOfflinePlayer(UUID.fromString(YamlManager.getPlayerData(args[0]).getString("uuid", "")));
			}
			eco.depositPlayer(target, amount);
			double money = eco.getBalance(target);
			player.sendMessage(Language.TITLE + "玩家§3" + args[0] + "§f目前拥有麦块币§3" + money);
			if(target.isOnline())target.getPlayer().sendMessage(Language.TITLE + "您目前拥有麦块币§3" + money);
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
