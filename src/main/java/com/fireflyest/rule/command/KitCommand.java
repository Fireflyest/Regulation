package com.fireflyest.rule.command;

import com.fireflyest.rule.convert.ScriptConvert;
import com.fireflyest.rule.data.Config;
import com.fireflyest.rule.data.Language;
import com.fireflyest.rule.data.YamlManager;
import com.fireflyest.rule.item.ScriptItem;
import com.fireflyest.rule.time.ScriptTime;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCommand  implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("kit")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 0) {
			sender.sendMessage(Language.TITLE+ Config.KITS.keySet());
		}else
		if(args.length == 1) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			if(!player.hasPermission("rule.kit."+args[0]))  { sender.sendMessage(Language.NOT_PERMISSION.replace("%permission%", "rule.kit."+args[0])); return true; }
			if(!Config.KITS.keySet().contains(args[0])) { sender.sendMessage(Language.HAVENOT_SETKIT.replace("%kit%", args[0])); return true; }
			if (YamlManager.getPlayerDataKeys(player.getName(), "kit").contains(args[0])) {
				if (YamlManager.getPlayerData(player.getName()).getLong("kit." + args[0], 0) == 0) {
					sender.sendMessage(Language.DISPOSABLE_KIT.replace("%kit%", args[0]));
					return true;
				}
				long time = YamlManager.getPlayerData(player.getName()).getLong("kit." + args[0]) - ScriptTime.getDate();
				if (time > 0) {
					sender.sendMessage(Language.COOLDOWN_TO_USE.replace("%time%", ScriptConvert.convertTime(time)));
					return true;
				}
			}
			giveKit(player, args[0]);
			return true;
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
	private void giveKit(Player player, String kit) {
		if(!Config.KITS.containsKey(kit)){
			player.sendMessage(Language.TITLE + "工具包不存在");
			return;
		}
		for(String k : Config.KITS.get(kit)) { player.getInventory().addItem(ScriptItem.getItem(k)); }
		if(Config.KITTIME.get(kit) == -1) {
			YamlManager.setPlayerData(player.getName(), "kit."+kit, 0);
		}else {
			YamlManager.setPlayerData(player.getName(), "kit."+kit, Config.KITTIME.get(kit)+ScriptTime.getDate());
		}
		player.sendMessage(Language.TITLE + "已领取礼包§3"+kit);
	}
	
}
