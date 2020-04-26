package com.fireflyest.rule.manager;

import java.util.List;

import com.fireflyest.rule.data.YamlManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;

import com.fireflyest.rule.Regulation;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

public class EconomyManager implements Economy {
	
	public static EconomyManager getInstance() { return econ; }
	
	private static EconomyManager econ = new EconomyManager();

	private static Plugin plugin;
	private static boolean enable = false;
	private static RegisteredServiceProvider<Economy> rsp;
	
	private EconomyManager() {
		
	}
	
	public static Economy getEconomy() {
		return rsp.getProvider();
	}
	
	public static void iniEconomyManager() {
		plugin = Regulation.getInstance();
		rsp = new RegisteredServiceProvider<>(Economy.class, econ, ServicePriority.Normal, plugin);
		if (rsp != null) enable = true;
	}

	@Override
	public EconomyResponse bankBalance(String arg0) {
		return null;
	}

	@Override
	public EconomyResponse bankDeposit(String arg0, double arg1) {
		return null;
	}

	@Override
	public EconomyResponse bankHas(String arg0, double arg1) {
		return null;
	}

	@Override
	public EconomyResponse bankWithdraw(String arg0, double arg1) {
		return null;
	}

	@Override
	public EconomyResponse createBank(String arg0, String arg1) {
		return null;
	}

	@Override
	public EconomyResponse createBank(String arg0, OfflinePlayer arg1) {
		return null;
	}

	@Override
	public boolean createPlayerAccount(String arg0) {
		return false;
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer arg0) {
		return false;
	}

	@Override
	public boolean createPlayerAccount(String arg0, String arg1) {
		return false;
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer arg0, String arg1) {
		return false;
	}

	@Override
	public String currencyNamePlural() {
		return null;
	}

	@Override
	public String currencyNameSingular() {
		return null;
	}

	@Override
	public EconomyResponse deleteBank(String arg0) {
		return null;
	}

	@Override
	public EconomyResponse depositPlayer(String arg0, double arg1) {
		double money = YamlManager.getPlayerData(arg0).getDouble("money")+arg1;
		YamlManager.setPlayerData(arg0, "money", money);
		return new EconomyResponse(arg1, money, ResponseType.SUCCESS,"操作失败");
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer arg0, double arg1) {
		double money = YamlManager.getPlayerData(arg0.getName()).getDouble("money")+arg1;
		YamlManager.setPlayerData(arg0.getName(), "money", money);
		return new EconomyResponse(arg1, money, ResponseType.SUCCESS,"操作失败");
	}

	@Override
	public EconomyResponse depositPlayer(String arg0, String arg1, double arg2) {
		return null;
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer arg0, String arg1, double arg2) {
		return null;
	}

	@Override
	public String format(double arg0) {
		return null;
	}

	@Override
	public int fractionalDigits() {
		return 0;
	}

	@Override
	public double getBalance(String arg0) {
		return YamlManager.getPlayerData(arg0).getDouble("money");
	}

	@Override
	public double getBalance(OfflinePlayer arg0) {
		return YamlManager.getPlayerData(arg0.getName()).getDouble("money");
	}

	@Override
	public double getBalance(String arg0, String arg1) {
		return 0;
	}

	@Override
	public double getBalance(OfflinePlayer arg0, String arg1) {
		return 0;
	}

	@Override
	public List<String> getBanks() {
		return null;
	}

	@Override
	public String getName() {
		return "Regulation";
	}

	@Override
	public boolean has(String arg0, double arg1) {
		return arg1 <= YamlManager.getPlayerData(arg0).getDouble("money");
	}

	@Override
	public boolean has(OfflinePlayer arg0, double arg1) {
		return arg1 <= YamlManager.getPlayerData(arg0.getName()).getDouble("money");
	}

	@Override
	public boolean has(String arg0, String arg1, double arg2) {
		return false;
	}

	@Override
	public boolean has(OfflinePlayer arg0, String arg1, double arg2) {
		return false;
	}

	@Override
	public boolean hasAccount(String arg0) {
		return YamlManager.getPlayerData(arg0).getDouble("money") >= 0;
	}

	@Override
	public boolean hasAccount(OfflinePlayer arg0) {
		return YamlManager.getPlayerData(arg0.getName()).getDouble("money") >= 0;
	}

	@Override
	public boolean hasAccount(String arg0, String arg1) {
		return false;
	}

	@Override
	public boolean hasAccount(OfflinePlayer arg0, String arg1) {
		return false;
	}

	@Override
	public boolean hasBankSupport() {
		return false;
	}

	@Override
	public EconomyResponse isBankMember(String arg0, String arg1) {
		return null;
	}

	@Override
	public EconomyResponse isBankMember(String arg0, OfflinePlayer arg1) {
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, String arg1) {
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, OfflinePlayer arg1) {
		return null;
	}

	@Override
	public boolean isEnabled() {
		return enable;
	}

	@Override
	public EconomyResponse withdrawPlayer(String arg0, double arg1) {
		double money = YamlManager.getPlayerData(arg0).getDouble("money")-arg1;
		YamlManager.setPlayerData(arg0, "money", money);
		return new EconomyResponse(arg1, money, ResponseType.SUCCESS,"操作失败");
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer arg0, double arg1) {
		double money = YamlManager.getPlayerData(arg0.getName()).getDouble("money")-arg1;
		YamlManager.setPlayerData(arg0.getName(), "money", money);
		return new EconomyResponse(arg1, money, ResponseType.SUCCESS,"操作失败");
	}

	@Override
	public EconomyResponse withdrawPlayer(String arg0, String arg1, double arg2) {
		return null;
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer arg0, String arg1, double arg2) {
		return null;
	}

}
