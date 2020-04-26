package com.fireflyest.rule.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.fireflyest.rule.data.Ephemeral;
import com.fireflyest.rule.data.Language;
import com.fireflyest.rule.time.ScriptTime;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.fireflyest.rule.Regulation;
import org.jetbrains.annotations.NotNull;

public class DelayCommand  implements CommandExecutor{

	@Override
	public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, @NotNull String[] args) {
		if(!cmd.getName().equalsIgnoreCase("delay")) return true;
		Player player = (sender instanceof Player)? (Player)sender : null;
		if(args.length == 0) {
			if(player == null) { sender.sendMessage(Language.ONLYPLAYERUSE); return true; }
			new BukkitRunnable() {
				@Override
				public void run() {
					Runtime runtime = Runtime.getRuntime();
					Process process;
					try {
						process = runtime.exec("ping -n 1 " + player.getAddress().getAddress().toString().substring(1));
						InputStream is = process.getInputStream();
						InputStreamReader isr = new InputStreamReader(is);
						BufferedReader br = new BufferedReader(isr);
						String line, last = null;
						while ((line = br.readLine()) != null) {
							last = line;
						}
						String[] delay = last.split(" ");
						String value = delay[delay.length-1];
						player.setPlayerListFooter(Language.LIST_FOOTER.replace("%n%", ""+ Bukkit.getOnlinePlayers().size()).replace("%m%", value));
						is.close();
						isr.close();
						br.close();
					}catch (IOException e) {
						e.printStackTrace();
					}
					cancel();
				}
			}.runTask(Regulation.getInstance());
		}else sender.sendMessage(Language.TITLE + "正确用法§3" + cmd.getUsage());
		return true;
	}
	
}
