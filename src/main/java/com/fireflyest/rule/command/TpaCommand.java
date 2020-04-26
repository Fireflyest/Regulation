package com.fireflyest.rule.command;

import com.fireflyest.rule.chat.ScriptChat;
import com.fireflyest.rule.data.Language;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fireflyest.rule.data.Applications;
import com.fireflyest.rule.data.Ephemeral;

public class TpaCommand  implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("tpa")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 1) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			Player target = Bukkit.getPlayer(args[0]);
			if(target == null) {sender.sendMessage(Language.OFFLINE_PLAYER.replace("%player%", args[0])); return true; }
			//判断是否勿扰
			if(Ephemeral.isDnd(target.getName())){
				player.sendMessage(Language.TP_APPLY_REFUSE);
				return true;
			}
			if(Applications.containsTpaReciver(target.getUniqueId())) {sender.sendMessage(Language.AREADY_GAS_TPER.replace("%player%", args[0])); return true; }
			Applications.addTpa(player.getUniqueId(), target.getUniqueId());
			target.sendMessage(Language.TELEPORT_APPLY.replace("%player%", player.getName()));
			ScriptChat.sendApplyButton(target, "/tp");
			player.sendMessage(Language.SUCCEED_SEND_TP);
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
