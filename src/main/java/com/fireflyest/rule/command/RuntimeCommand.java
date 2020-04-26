package com.fireflyest.rule.command;

import com.fireflyest.rule.data.Language;
import com.fireflyest.rule.time.ScriptTime;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import com.fireflyest.rule.Regulation;

public class RuntimeCommand  implements CommandExecutor{
	
	private static final long u = 1024*1024;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("runtime")) return true;
		Runtime r = Runtime.getRuntime();
		if(args.length == 0) {
			sender.sendMessage(Language.TITLE + "内存回收中...");
			new BukkitRunnable() {
				@Override
				public void run() {
					r.gc();
					String used = ""+(r.totalMemory()-r.freeMemory())/(double)r.totalMemory();
					sender.sendMessage(Language.TITLE+"§f总共 §3: " + r.totalMemory()/u+"M");
					sender.sendMessage(Language.TITLE+"§f可用 §3: " + r.freeMemory()/u+"M");
					sender.sendMessage(Language.TITLE+"§f已用 §3: " + used.substring(2, 4)+"."+used.substring(4, 6)+"%");
					cancel();
				}
			}.runTask(Regulation.getInstance());
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
