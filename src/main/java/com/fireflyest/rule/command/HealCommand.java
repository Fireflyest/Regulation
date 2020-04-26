package com.fireflyest.rule.command;

import com.fireflyest.rule.data.Language;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fireflyest.rule.data.Ephemeral;
import org.bukkit.potion.PotionEffect;

public class HealCommand  implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("heal")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 0) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			healPlayer(player);

		}else
		if(args.length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if(target == null) {sender.sendMessage(Language.OFFLINE_PLAYER.replace("%player%", args[0])); return true; }
			healPlayer(target);
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
	private void healPlayer(Player player) {
		player.setHealth(20);
		player.setFoodLevel(20);
		for(PotionEffect effect: player.getActivePotionEffects()){
			player.removePotionEffect(effect.getType());
		}
		player.setFireTicks(0);
		player.sendMessage(Language.TITLE + "已恢复状态");
	}
	
}
