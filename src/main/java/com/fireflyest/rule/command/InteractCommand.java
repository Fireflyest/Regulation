package com.fireflyest.rule.command;

import com.fireflyest.rule.convert.ScriptConvert;
import com.fireflyest.rule.data.Ephemeral;
import com.fireflyest.rule.data.Language;
import com.fireflyest.rule.data.YamlManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class InteractCommand implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("interact")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 0) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			return true;
		}else
		if(args.length == 1) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
			BookMeta meta = (BookMeta)book.getItemMeta();
			OfflinePlayer target = Bukkit.getPlayer(args[0]);
			String name;
			if(target != null){
				name = target.getName();
				String info = "§6§l名称§7:§0 "+name+
						"\n§6§l等级§7:§0 "+ YamlManager.getPlayerData(name).getString("level")+
						"\n§6§l在线§7:§0 "+ "true"+
						"\n§6§l勿扰§7:§0 "+ Ephemeral.isDnd(name)+
						"\n§6§l禁言§7:§0 "+ Ephemeral.isMute(name);
				meta.spigot().addPage(
						new ComponentBuilder(info)
								.append("\n\n§a[").bold(true)
								.append("§9请求传送")
								.event(new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/tpa "+name))
								.event(new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("点击传送").create()))
								.append("]").bold(false).color(ChatColor.GREEN)
								.append("    ").reset()
								.append("§a[").bold(true)
								.append("§9邀请传送")
								.event(new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/tphere "+name))
								.event(new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("点击邀请").create()))
								.append("]").bold(false).color(ChatColor.GREEN)
								.create()
				);
			}else{
				if(!YamlManager.getPlayerData(args[0]).contains("uuid")){
					sender.sendMessage(Language.TITLE + "玩家不存在");
					return true;
				}
				target = Bukkit.getOfflinePlayer(UUID.fromString(YamlManager.getPlayerData(args[0]).getString("uuid")));
				name = target.getName();
				String info = "§6§l名称§7:§0 "+name+
						"\n§6§l等级§7:§0 "+ YamlManager.getPlayerData(name).getString("level")+
						"\n§6§l在线§7:§0 "+ "true"+
						"\n§6§l勿扰§7:§0 "+ Ephemeral.isDnd(name)+
						"\n§6§l禁言§7:§0 "+ Ephemeral.isMute(name)+
						"\n§6§l下线§7:§0 "+ ScriptConvert.convertTime(target.getLastPlayed());
			}
			book.setItemMeta(meta);
			player.openBook(book);
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
