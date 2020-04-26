package com.fireflyest.rule.command;

import com.fireflyest.rule.data.Language;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fireflyest.rule.data.Applications;

public class TprefuseCommand  implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("tprefuse")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 0) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			Player tper;
			if(Applications.containsTpaReciver(player.getUniqueId())) {
				tper = Bukkit.getPlayer(Applications.getTpaer(player.getUniqueId()));
				tper.sendMessage(Language.TP_APPLY_REFUSE);
				Applications.removeTpa(player.getUniqueId());
			}
			if(Applications.containsTphereReceiver(player.getUniqueId())) {
				tper = Bukkit.getPlayer(Applications.getTpherer(player.getUniqueId()));
				tper.sendMessage(Language.TP_APPLY_REFUSE);
				Applications.removeTphere(player.getUniqueId());
			}
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
