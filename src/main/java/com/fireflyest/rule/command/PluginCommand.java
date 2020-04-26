package com.fireflyest.rule.command;

import java.util.Set;
import java.util.TreeSet;

import com.fireflyest.rule.chat.ScriptChat;
import com.fireflyest.rule.data.Language;
import com.fireflyest.rule.item.ScriptItem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;

import com.fireflyest.rule.Regulation;

public class PluginCommand  implements CommandExecutor{
	
	private static PluginManager manager;
	private static Set<String> plugins = new TreeSet<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("plugin")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(manager == null)manager = Bukkit.getServer().getPluginManager();
		if(args.length == 0) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			new BukkitRunnable() {
				@Override
				public void run() {
					for(Plugin plugin : manager.getPlugins()) {
						if(!plugins.contains(plugin.getName())) { plugins.add(plugin.getName()); }
					}
					ScriptItem.PLUGIN.clear();
					for(String plugin : plugins) {
						ItemStack item = ScriptItem.getItem("Plugin").clone();
						ScriptItem.setDisplayName(item, "§f[§e"+plugin+"§f]");
						ScriptItem.PLUGIN.addItem(item);
					}
					player.closeInventory();
					player.openInventory(ScriptItem.PLUGIN);
					cancel();
					return;
				}
			}.runTask(Regulation.getInstance());
			return true;
		}else
		if(args.length == 1) {
			if(plugins.isEmpty()) for(Plugin plugin : manager.getPlugins()) { plugins.add(plugin.getName()); }
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			Plugin plugin = manager.getPlugin(args[0]);
			if(plugin == null) {
				sender.sendMessage(Language.TITLE + "插件不存在");
				return true;
			}
			sender.sendMessage("§e§m =                                                                          = ");
			sender.sendMessage(Language.TITLE+"§f名称 §3: " + plugin.getName());
			sender.sendMessage(Language.TITLE+"§f核心 §3: " + plugin.getDescription().getAPIVersion());
			sender.sendMessage(Language.TITLE+"§f版本 §3: " + plugin.getDescription().getVersion());
			sender.sendMessage(Language.TITLE+"§f作者 §3: " + plugin.getDescription().getAuthors());
			sender.sendMessage(Language.TITLE+"§f指令 §3: " + plugin.getDescription().getCommands().keySet());
			if(plugin.isEnabled()) {
				ScriptChat.sendCommandButton(player, "关闭插件", "RED", "点击关闭", "/plugin "+args[0]+" disable");
			}else {
				ScriptChat.sendCommandButton(player, "开启插件", "GREEN", "点击开启", "/plugin "+args[0]+" enable");
			}
			sender.sendMessage("§e§m =                                                                          = ");
			return true;
		}else if(args.length == 2) {
			if("disable".equals(args[1])) {
				manager.disablePlugin(manager.getPlugin(args[0]));
				return true;
			}
			if("enable".equals(args[1])) {
				manager.enablePlugin(manager.getPlugin(args[0]));
				return true;
			}
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
