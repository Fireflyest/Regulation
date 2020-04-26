package com.fireflyest.rule.command;

import java.util.ArrayList;
import java.util.List;

import com.fireflyest.rule.chat.ScriptChat;
import com.fireflyest.rule.data.Language;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.fireflyest.rule.Regulation;
import com.fireflyest.rule.manager.TeleportManager;

public class WorldCommand  implements CommandExecutor{
	
	private static List<String> worlds = new ArrayList<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("world")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 0) {
			sender.sendMessage("§e§m =                                                                          = ");
			for(World world : Bukkit.getWorlds()) {
				if(!worlds.contains(world.getName()))worlds.add(world.getName());
				String color = world.getEnvironment().name().replace("NORMAL", "§a").replace("NETHER", "§c").replace("THE_END", "§5");
				ScriptChat.sendCommandButton(player, color+world.getName(), "YELLOW", "点击查看详情", "/world "+world.getName());
			}
		}else
		if(args.length == 1) {
			if(worlds.isEmpty())for(World world : Bukkit.getWorlds()) worlds.add(world.getName());
			if(!worlds.contains(args[0])) { player.sendMessage(Language.TITLE + worlds); return true; }
			World world = Bukkit.getWorld(args[0]);
			new BukkitRunnable() {
				List<String> slow = new ArrayList<>();
				@Override
				public void run() {
					sender.sendMessage("§e§m =                                                                          = ");
					player.sendMessage(Language.TITLE+"§f边界宽度 §3: " + world.getWorldBorder().getSize());
					player.sendMessage(Language.TITLE+"§f区块数量 §3: " + world.getLoadedChunks().length);
					player.sendMessage(Language.TITLE+"§f实体数量 §3: " + world.getEntities().size());
					player.sendMessage(Language.TITLE+"§f生物数量 §3: " + world.getLivingEntities().size());
					player.sendMessage(Language.TITLE+"§f动物限制 §3: " + world.getAnimalSpawnLimit());
					player.sendMessage(Language.TITLE+"§f怪物限制 §3: " + world.getMonsterSpawnLimit());
					player.sendMessage(Language.TITLE+"§f玩家数量 §3: " + world.getPlayers().size());
					player.sendMessage(Language.TITLE+"§f动物刷新 §3: " + world.getTicksPerAnimalSpawns());
					player.sendMessage(Language.TITLE+"§f怪物刷新 §3: " + world.getTicksPerMonsterSpawns());
					for(Chunk chunk : world.getLoadedChunks()) { 
						if(chunk.getEntities().length > 25) {
							for(Entity entity : chunk.getEntities()) { if(entity.getType().name().equals("PLAYER"))entity.sendMessage(Language.TITLE+"区块卡顿，请及时清理附近实体"); }
							slow.add("("+chunk.getX()+","+chunk.getZ()+")"); 
						}
					}
					player.sendMessage(Language.TITLE+"§f卡顿区块 §3: " + slow);
					sender.sendMessage("§e§m =                                                                          = ");
					cancel();
					return;
				}
			}.runTask(Regulation.getInstance());
		}else 
		if(args.length == 2) {
			if(worlds.isEmpty())for(World world : Bukkit.getWorlds()) worlds.add(world.getName());
			if("load".equals(args[0])) {
				if(worlds.contains(args[1]))return true;
				WorldCreator main = new WorldCreator(args[1]);
				main.createWorld();
			}
			if("tp".equals(args[0])) {
				if(!player.hasPermission("rule.world.tp"))  { sender.sendMessage(Language.NOT_PERMISSION.replace("%permission%", "rule.world.tp")); return true; }
				if(!worlds.contains(args[1]))return true;
				TeleportManager.teleportTo(player, Bukkit.getWorld(args[1]).getSpawnLocation(), player.hasPermission("rule.vip"));
			}
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
