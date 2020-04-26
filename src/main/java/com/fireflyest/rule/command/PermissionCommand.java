package com.fireflyest.rule.command;

import com.fireflyest.rule.data.Language;
import com.fireflyest.rule.data.YamlManager;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.fireflyest.rule.Regulation;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class PermissionCommand  implements CommandExecutor{
	
	@Override
	public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, @NotNull String[] args) {
		if(!cmd.getName().equalsIgnoreCase("permission")) return true;
		if(args.length == 1) {
			Player player = (sender instanceof Player)? (Player)sender : null;
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			Player target = Bukkit.getPlayer(args[0]);
			if(target == null) {sender.sendMessage(Language.OFFLINE_PLAYER.replace("%player%", args[0])); return true; }
			for(PermissionAttachmentInfo p : target.getEffectivePermissions()){
				String value = p.getValue()?"§a":"§c";
				player.sendMessage(value + p.getPermission());
			}
		}else
		if(args.length == 2) {
			Player target = Bukkit.getPlayer(args[0]);
			if(target == null) {sender.sendMessage(Language.OFFLINE_PLAYER.replace("%player%", args[0])); return true; }
			String[] p = args[1].split(",");
			YamlManager.setPlayerData(args[0], "permission."+p[0].replace(".", "_"), Boolean.valueOf(p[1]));
			target.addAttachment(Regulation.getInstance(), p[0], Boolean.parseBoolean(p[1]));
			sender.sendMessage(Language.TITLE + "已为玩家设置权限§3" + p[0] + "§f:§3" +p [1]);
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
