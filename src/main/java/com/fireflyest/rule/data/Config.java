package com.fireflyest.rule.data;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class Config {

    public static FileConfiguration getConfig() {
        return config;
    }

    private static FileConfiguration config;

    public static String VERSION;
    public static String MAIN_WORLD;
    public static Set<String> ITEMS = new HashSet<>();
    public static Map<String, Integer> BORDER = new HashMap<>();
    public static Map<String, List<String>>PERMISSION = new HashMap<>();
    public static Map<String, String>PREFIX = new HashMap<>();
    public static Map<String, String>WARPS = new HashMap<>();
    public static Map<String, List<String>>KITS = new HashMap<>();
    public static Map<String, Integer>KITTIME = new HashMap<>();

    public Config(FileConfiguration config){
        this.config = config;
        this.setUp();
    }

    private void setUp(){
        VERSION = config.getString("Version");
        MAIN_WORLD = config.getString("MainWorld");
        for(String world : config.getConfigurationSection("WorldBorder").getKeys(false)) {
            BORDER.put(world, config.getInt("WorldBorder."+world));
        }
        for(String group : config.getConfigurationSection("Group").getKeys(false)) {
            PERMISSION.put(group, config.getStringList("Group."+group+".permission"));
            PREFIX.put(group, config.getString("Group."+group+".prefix"));
        }
        for(String warp : config.getConfigurationSection("Warps").getKeys(false)) {
            WARPS.put(warp, config.getString("Warps."+warp));
        }
        for(String kit : config.getConfigurationSection("Kits").getKeys(false)) {
            KITS.put(kit, config.getStringList("Kits."+kit+".items"));
            KITTIME.put(kit, config.getInt("Kits."+kit+".time"));
        }
        ITEMS = config.getConfigurationSection("Items").getKeys(false);
    }

    public static void setKey(String key, Object value) {
        config.set(key, value);
    }

}
