package com.fireflyest.rule.command;

import com.fireflyest.rule.convert.ScriptConvert;
import com.fireflyest.rule.data.Language;
import com.fireflyest.rule.data.YamlManager;
import com.fireflyest.rule.item.ScriptItem;
import com.fireflyest.rule.time.ScriptTime;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.fireflyest.rule.Regulation;

public class PrefixCommand  implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("prefix")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 0) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			new BukkitRunnable() {
				@Override
				public void run() {
					ScriptItem.PREFIX.clear();
					//[0]称号 [1]玩家 [2]参数(颜色,期限)
					//加载称号菜单
					for(String prefix : YamlManager.getPlayerDataKeys(player.getName(), "prefix")) {
						String[] p = (YamlManager.getPlayerData(player.getName()).getString("prefix."+prefix)).split(",");
						long time = Long.parseLong(p[1]) - ScriptTime.getDate();
						if(time < 0 && Long.parseLong(p[1]) != -1) {
							YamlManager.setPlayerData(player.getName(), "prefix."+prefix, null);
							YamlManager.setPlayerData(player.getName(), "using", null);
							continue; 
						}
						ItemStack item = ScriptItem.getItem("Prefix").clone();
						ScriptItem.setDisplayName(item, "§f[§"+p[0]+prefix+"§f]");
						String value = Long.parseLong(p[1]) == -1 ? "无限":ScriptConvert.convertTime(time);
						ScriptItem.setItemValue(item, 1, value);
						ScriptItem.PREFIX.addItem(item);
					}
					player.closeInventory();
					player.openInventory(ScriptItem.PREFIX);
					cancel();
				}
			}.runTask(Regulation.getInstance());
			return true;
		}else
		if(args.length == 1) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			if(!YamlManager.getPlayerDataKeys(player.getName(), "prefix").contains(args[0].substring(2))) { player.sendMessage(Language.NOT_PERMISSION.replace("%permission%", args[0])); return true; }
			YamlManager.setPlayerData(player.getName(), "using", args[0]);
			player.setDisplayName("§f["+args[0]+"§f]§b" + player.getName() + "§f");
			player.setPlayerListName(player.getDisplayName());
			player.sendMessage(Language.TITLE + "成功切换头衔" + "§f["+args[0]+"§f]§b");
		}else if(args.length == 3) {
			if(!sender.hasPermission("rule.prefix.add"))  { sender.sendMessage(Language.NOT_PERMISSION.replace("%permission%", "rule.prefix.add")); return true; }
			//颜色，期限
			String[] value = args[2].split(",");
			//已有的时间
			long has;
			if(YamlManager.getPlayerDataKeys(args[1] ,"prefix").contains(args[0])){
				String[] p = (YamlManager.getPlayerData(player.getName()).getString("prefix."+args[0])).split(",");
				has = Long.parseLong(p[1]);
			}else{
				has = ScriptTime.getDate();
			}
			YamlManager.setPlayerData(args[1], "prefix."+args[0], value[0]+","+(has+Long.parseLong(value[1])));
			sender.sendMessage(Language.TITLE + "已给予头衔" + "§f[§"+value[0]+args[0]+"§f]");
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
