package com.fireflyest.rule.data;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Language {

    public FileConfiguration getLang() {
        return lang;
    }

    private FileConfiguration lang;

    public static List<String>HELP = new ArrayList<>();
    public static String VERSION;
    public static String TITLE;
    public static String RELOADING;
    public static String RELOADED;
    public static String MOTD;
    public static String ONLYPLAYERUSE;
    public static String NOT_PERMISSION;
    public static String OFFLINE_PLAYER;
    public static String SUCCEED_SWITCH;
    public static String MAXIMUN_AMOUNT;
    public static String SUCCEED_SETTLE;
    public static String HAVENOT_SETTLE;
    public static String HAVENOT_SETPOI;
    public static String HAVENOT_SETKIT;
    public static String TELEPORT_POINT;
    public static String CANCEL_WAITING;
    public static String LIST_HEADER;
    public static String LIST_FOOTER;
    public static String DISPOSABLE_KIT;
    public static String COOLDOWN_TO_USE;
    public static String AREADY_GAS_TPER;
    public static String SUCCEED_SEND_TP;
    public static String TELEPORT_APPLY;
    public static String TELEPORT_IVTTP;
    public static String TP_APPLY_REFUSE;
    public static String ECONOMY_PLAYER;

    public Language(FileConfiguration lang){
        this.lang = lang;
        this.setUp();
    }

    private void setUp(){
        VERSION = lang.getString("Version");
        HELP = lang.getStringList("Help");
        TITLE = lang.getString("Title").replace("&", "§");
        RELOADING = TITLE + lang.getString("Reloading").replace("&", "§");
        RELOADED = TITLE + lang.getString("Reloaded").replace("&", "§");
        LIST_HEADER = lang.getString("ListHeader").replace("&", "§").replace(",", "\n");
        LIST_FOOTER = lang.getString("ListFooter").replace("&", "§").replace(",", "\n");
        MOTD = lang.getString("Motd").replace("&", "§").replace(",", "\n");
        ONLYPLAYERUSE = TITLE + lang.getString("OnlyPlayerUse").replace("&", "§");
        NOT_PERMISSION = TITLE + lang.getString("NotPermission").replace("&", "§");
        OFFLINE_PLAYER =  TITLE + lang.getString("OfflinePlayer").replace("&", "§");
        SUCCEED_SWITCH =  TITLE + lang.getString("SucceedSwitch").replace("&", "§");
        MAXIMUN_AMOUNT =  TITLE + lang.getString("MaximumAmount").replace("&", "§");
        SUCCEED_SETTLE =  TITLE + lang.getString("SucceedSettle").replace("&", "§");
        HAVENOT_SETTLE =  TITLE + lang.getString("HavenotSettle").replace("&", "§");
        HAVENOT_SETPOI =  TITLE + lang.getString("HavenotSetpoi").replace("&", "§");
        HAVENOT_SETKIT = TITLE + lang.getString("HavenotSetkit").replace("&", "§");
        TELEPORT_POINT = TITLE + lang.getString("TeleportPoint").replace("&", "§");
        CANCEL_WAITING = TITLE + lang.getString("CancelWaiting").replace("&", "§");
        DISPOSABLE_KIT = TITLE + lang.getString("DisposableKit").replace("&", "§");
        COOLDOWN_TO_USE = TITLE + lang.getString("CooldownToUse").replace("&", "§");
        AREADY_GAS_TPER = TITLE + lang.getString("AreadyHasTper").replace("&", "§");
        SUCCEED_SEND_TP = TITLE + lang.getString("SucceedSendTp").replace("&", "§");
        TELEPORT_APPLY = TITLE + lang.getString("TeleportApply").replace("&", "§");
        TELEPORT_IVTTP = TITLE + lang.getString("TeleportIvttp").replace("&", "§");
        TP_APPLY_REFUSE = TITLE + lang.getString("TpApplyRefuse").replace("&", "§");
        ECONOMY_PLAYER = TITLE + lang.getString("EconomyPlayer").replace("&", "§");
    }

}
