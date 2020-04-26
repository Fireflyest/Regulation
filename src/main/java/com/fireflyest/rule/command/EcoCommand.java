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
import org.jetbrains.annotations.NotNull;

public class EcoCommand  implements CommandExecutor{
	
	private static Economy eco;
	
	@Override
	public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, @NotNull String[] args) {
		if(!cmd.getName().equalsIgnoreCase("eco")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(eco == null) eco = EconomyManager.getEconomy();
		if(args.length == 2) {
			//[0]玩家 [1]数量
			//检测是否存在该玩家
			OfflinePlayer target;
			target = Bukkit.getPlayer(args[0]);
			//如果不在线，获取下线对象
			if(target == null){
				if(!YamlManager.getPlayerDataKeys(args[0], "").contains("uuid")){sender.sendMessage(Language.OFFLINE_PLAYER.replace("%player%", args[0])); return true; }
				target = Bukkit.getOfflinePlayer(UUID.fromString(YamlManager.getPlayerData(args[0]).getString("uuid", "")));
			}
			//给钱
			eco.depositPlayer(target, Double.parseDouble(args[1]));
			double money = eco.getBalance(target);
			//提示信息
			sender.sendMessage(Language.TITLE + "玩家§3" + args[0] + "§f目前拥有麦块币§3" + money);
			if(target.isOnline())target.getPlayer().sendMessage(Language.TITLE + "您目前拥有麦块币§3" + money);
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
