package com.fireflyest.rule.command;

import com.fireflyest.rule.data.Language;
import com.fireflyest.rule.data.YamlManager;
import com.fireflyest.rule.time.ScriptTime;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fireflyest.rule.manager.PermissionManager;

public class GroupCommand  implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("group")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 1) {
			player.sendMessage(Language.TITLE + "玩家§3"+args[0]+"§f权限等级§3G" + YamlManager.getPlayerData(args[0]).getInt("level", 0));
		}else
		if(args.length == 2) {
			int level = YamlManager.getPlayerData(args[0]).getInt("level", 0);
			YamlManager.setPlayerData(args[0], "prefix.Lv"+level, null);
			level = Integer.parseInt(args[1]);
			Bukkit.broadcastMessage(Language.TITLE + "恭喜玩家§3" + args[0] + "§f成功晋升等级" + level);
			YamlManager.setPlayerData(args[0], "level", level);
			if(level > 3){
				long time = ScriptTime.getDate()+2592000000L;
				YamlManager.setPlayerData(args[0], "vip", time);
				YamlManager.setPlayerData(args[0], "prefix.Lv"+level, "7,"+time);
			}else{
				YamlManager.setPlayerData(args[0], "prefix.Lv"+level, "7,"+-1);
			}
			PermissionManager.addAttachments(player, level);
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
