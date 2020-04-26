package com.fireflyest.rule.command;

import com.fireflyest.rule.data.Language;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fireflyest.rule.data.Ephemeral;

public class ModeCommand  implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("mode")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 0) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			switchMode(player);
			return true;
		}else
		if(args.length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if(target == null) {sender.sendMessage(Language.OFFLINE_PLAYER.replace("%player%", args[0])); return true; }
			sender.sendMessage(Language.SUCCEED_SWITCH+"§3"+target.getName());
			switchMode(target);
			return true;
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}

	private void switchMode(Player player){
		player.setGameMode(GameMode.SURVIVAL.equals(player.getGameMode()) ? GameMode.CREATIVE : GameMode.SURVIVAL);
		player.sendMessage(Language.TITLE+"模式: §3" + player.getGameMode().name().toLowerCase());
	}
	
}
