package com.fireflyest.rule.command;

import com.fireflyest.rule.data.Language;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fireflyest.rule.data.Ephemeral;
import org.jetbrains.annotations.NotNull;

public class DndCommand  implements CommandExecutor{
	
	@Override
	public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, @NotNull String[] args) {
		if(!cmd.getName().equalsIgnoreCase("dnd")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 0) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			if(Ephemeral.isDnd(sender.getName())) {
				Ephemeral.removeDnd(sender.getName());
			}else {
				Ephemeral.addDnd(sender.getName());
			}
			player.sendMessage(Language.SUCCEED_SWITCH);
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
