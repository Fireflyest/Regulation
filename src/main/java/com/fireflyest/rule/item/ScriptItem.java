package com.fireflyest.rule.item;

import com.fireflyest.rule.data.Config;
import com.fireflyest.rule.data.YamlManager;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Fireflyest
 */
public class ScriptItem {

    private static Map<String, ItemStack> list = new HashMap<>();

    public static Inventory MY = Bukkit.createInventory(null, 27, "§f[§6My§f]§a⚖");
    public static Inventory PLUGIN = Bukkit.createInventory(null, 54, "§f[§6Plugin§f]§a⚖");
    public static Inventory PREFIX = Bukkit.createInventory(null, 54, "§f[§6Prefix§f]§a⚖");

    private ScriptItem (){

    }

    /**
     * 初始化物品跟箱子
     */
    public static void iniScriptItem(){
        for(String name : Config.ITEMS) {
            if(name.contains("."))continue;
            Material material;
            try{
                material = Material.valueOf(Config.getConfig().getString("Items."+name+".material").toUpperCase());
            }catch (IllegalArgumentException e){
                e.printStackTrace();
                material = Material.STONE;
            }
            addDefaultItem(name, material);
        }
        for(int i = 0 ; i < 27 ; i++) { MY.setItem(i, list.get("Blank")); }
        setItemValue(list.get("Home"), 1, "a");
        MY.setItem(10, list.get("Home"));
        setItemValue(list.get("Home"), 1, "b");
        MY.setItem(11, list.get("Home"));
        setItemValue(list.get("Home"), 1, "c");
        MY.setItem(12, list.get("Home"));
        MY.setItem(14, list.get("Einv"));
        MY.setItem(15, list.get("Table"));
        MY.setItem(16, list.get("Fly"));
    }

    /**
     * 添加自定义物品
     * @param name 名称
     * @param material 材料类型
     */
    private static void addDefaultItem(String name, Material material) {
        ItemStack stack = new ItemStack(material);
        List<String> lores = new ArrayList<>();
        ItemMeta meta = stack.getItemMeta();
        for(String lore : Config.getConfig().getStringList("Items."+name+".lore")) lores.add(lore.replace("&", "§"));
        meta.setLore(lores);
        meta.setDisplayName(Config.getConfig().getString("Items."+name+".display").replace("&", "§"));
        stack.setItemMeta(meta);
        list.put(name, stack);
    }

    public static void saveItem(String name, ItemStack item) {
        list.put(name, item);
        ItemMeta im = item.getItemMeta();
        YamlManager.setConfigData("Items." + name + ".display", im.getDisplayName());
        YamlManager.setConfigData("Items." + name + ".material", item.getType().name());
        YamlManager.setConfigData("Items." + name + ".lore", im.getLore());
    }

    /**
     * 设置物品名称
     * @param item 物品
     * @param name 名称
     */
    public static void setDisplayName(ItemStack item, String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name.replace("&", "§"));
        item.setItemMeta(meta);
    }

    /**
     * 获取自定义物品
     * @param name 物品名称
     * @return ItemStack
     */
    public static ItemStack getItem(String name) {
        if(!list.containsKey(name))return new ItemStack(Material.STONE);
        return list.get(name);
    }

    /**
     * 设置皮革甲颜色
     * @param item 物品
     * @param color 颜色
     */
    public static void setColor(ItemStack item, Color color) {
        LeatherArmorMeta meta = (LeatherArmorMeta)item.getItemMeta();
        meta.setColor(color);
        item.setItemMeta(meta);
    }

    /**
     * 设置物品注释值
     * @param item 物品
     * @param line 行
     * @param value 值
     */
    public static void setItemValue(ItemStack item, int line, Object value) {
        ItemMeta im = item.getItemMeta();
        List<String> lores = im.getLore();
        String text[] = lores.get(line).split(" ");
        lores.set(line, text[0]+" "+value);
        im.setLore(lores);
        item.setItemMeta(im);
    }

    /**
     * 获取值
     * @param lore 注释
     * @return String
     */
    public static String getItemValue(String lore) {
        String value[] = lore.split(" ");
        return value[1];
    }

    /**
     * 设置物品注释
     * @param item 物品
     * @param line 行
     * @param lore 注释
     */
    public static void setLore(ItemStack item, int line, String lore) {
        List<String> lores;
        ItemMeta meta = item.getItemMeta();
        lores = meta.hasLore() ? meta.getLore() : new ArrayList<>();
        if(line <= lores.size()-1){
            lores.set(line, lore);
        }else{
            int j = line-lores.size();
            for(int i = 0; i < j; i++){
                lores.add("");
            }
            lores.add(lore);
        }
        meta.setLore(lores);
        item.setItemMeta(meta);
    }

    /**
     * 自定义物品
     * @param material 材料类型
     * @return ItemStack
     * HIDE_ENCHANTS, 隐藏附魔
     * HIDE_ATTRIBUTES, 隐藏攻击属性
     * HIDE_UNBREAKABLE, 隐藏是否可损坏
     * HIDE_DESTROYS, 隐藏损坏值
     * HIDE_PLACED_ON, 隐藏是否可放置
     * HIDE_POTION_EFFECTS; 隐藏药水属性
     */
    public static ItemStack createCustomItem(Material material, ItemFlag... flags){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        meta.addItemFlags(flags);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * 自定义药水
     * @param hide 是否隐藏药水属性
     * @param color 药水颜色
     * @return ItemStack
     */
    public static ItemStack createPotion(boolean hide, Color color){
        ItemStack item = new ItemStack(Material.POTION);
        ItemMeta meta = item.getItemMeta();
        if(hide)meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        PotionMeta potion = (PotionMeta)meta;
        potion.setColor(color);
        item.setItemMeta(potion);
        return item;
    }

    /**
     * 获取玩家头颅
     * @param player 玩家
     * @return ItemStack
     */
    public static ItemStack createSkull(OfflinePlayer player){
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwningPlayer(player);
        item.setItemMeta(meta);
        return item;
    }

}
