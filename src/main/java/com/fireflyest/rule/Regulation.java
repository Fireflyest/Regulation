package com.fireflyest.rule;

import com.fireflyest.rule.command.*;
import com.fireflyest.rule.data.Applications;
import com.fireflyest.rule.data.YamlManager;
import com.fireflyest.rule.event.*;
import com.fireflyest.rule.item.ScriptItem;
import com.fireflyest.rule.manager.EconomyManager;
import com.fireflyest.rule.manager.PermissionManager;
import com.fireflyest.rule.manager.TeleportManager;
import com.fireflyest.rule.time.ScriptTime;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.command.Command;
import org.bukkit.plugin.java.annotation.permission.Permission;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.author.Author;

@Plugin(name="Regulation", version="1.0.0-SNAPSHOT")
@Author(value = "Fireflyest")
@Command(name = "rule",desc = "基础指令", usage = "/script <reload|help|default>")
@Command(name = "my",desc = "快捷菜单", usage = "/my")
@Command(name = "home",desc = "传送到家", usage = "/home [name]")
@Command(name = "sethome",desc = "设置个家", usage = "/sethome [name]")
@Command(name = "spawn",desc = "复活点", usage = "/spawn")
@Command(name = "warp",desc = "传送点", usage = "/warp [name]")
@Command(name = "kit",desc = "快捷菜单", usage = "/kit [name]")
@Command(name = "tpa",desc = "工具包", usage = "/tpa [name]")
@Command(name = "tpaccept",desc = "快捷菜单", usage = "/tpaccept")
@Command(name = "tprefuse",desc = "快捷菜单", usage = "/tprefuse")
@Command(name = "money",desc = "快捷菜单", usage = "/money <name>")
@Command(name = "m",desc = "快捷菜单", usage = "/m [name] [text]")
@Command(name = "interact",desc = "玩家交互", usage = "/interact [name]")
@Command(name = "prefix",desc = "快捷菜单", usage = "/prefix <[prefix] [player] [color,time]>")
@Command(name = "delay",desc = "查看延迟", usage = "/delay")
@Command(name = "tphere",desc = "快捷菜单", usage = "/tphere [name]",
        permission = "rule.tphere", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.tphere")
@Command(name = "setwarp",desc = "设置传送点", usage = "/setwarp [name]",
        permission = "rule.setwarp", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.setwarp")
@Command(name = "fly",desc = "玩家飞行控制", usage = "/fly <name>",
        permission = "rule.fly", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.fly")
@Command(name = "inv",desc = "玩家背包查看", usage = "/inv [name]",
        permission = "rule.inv", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.inv")
@Command(name = "heal",desc = "玩家治疗", usage = "/heal <name>",
        permission = "rule.heal", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.heal")
@Command(name = "mode",desc = "切换模式", usage = "/mode",
        permission = "rule.mode", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.mode")
@Command(name = "einv",desc = "移动末影箱", usage = "/einv <name>",
        permission = "rule.einv", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.einv")
@Command(name = "table",desc = "移动工作台", usage = "/table",
        permission = "rule.table", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.table")
@Command(name = "skull",desc = "获取头颅", usage = "/skull [name]",
        permission = "rule.skull", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.skull")
@Command(name = "world",desc = "获取世界信息", usage = "/world <name>",
        permission = "rule.world", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.world")
@Command(name = "back",desc = "回到前一个传送点", usage = "/back",
        permission = "rule.back", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.back")
@Command(name = "plugin",desc = "服务器插件管理", usage = "/plugin",
        permission = "rule.plugin", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.plugin")
@Command(name = "sudo",desc = "让玩家执行", usage = "/sudo [player] [command]",
        permission = "rule.sudo", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.sudo")
@Command(name = "top",desc = "到达顶端", usage = "/top",
        permission = "rule.top", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.top")
@Command(name = "hat",desc = "戴帽子指令", usage = "/hat",
        permission = "rule.hat", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.hat")
@Command(name = "mute",desc = "禁言指令", usage = "/mute [player] <time>",
        permission = "rule.mute", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.mute")
@Command(name = "up",desc = "上", usage = "/up <amount>",
        permission = "rule.up", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.up")
@Command(name = "name",desc = "命名手上物品", usage = "/name [text]",
        permission = "rule.name", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.name")
@Command(name = "lore",desc = "修改手上物品的注释", usage = "/lore [line] [text]",
        permission = "rule.lore", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.lore")
@Command(name = "additem",desc = "添加物品", usage = "/additem [name]",
        permission = "rule.additem", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.additem")
@Command(name = "runtime",desc = "内存使用状况", usage = "/runtime",
        permission = "rule.runtime", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.runtime")
@Command(name = "dnd",desc = "请勿打扰", usage = "/dnd",
        permission = "rule.dnd", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.dnd")
@Command(name = "group",desc = "权限组", usage = "/group [name] <level>",
        permission = "rule.group", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.group")
@Command(name = "permission",desc = "权限", usage = "/permission <[name] [permission,boolean]>",
        permission = "rule.permission", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.permission")
@Command(name = "eco",desc = "权限", usage = "/eco <[name] [addmoney]>",
        permission = "rule.eco", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.eco")
@Command(name = "pay",desc = "支付", usage = "/pay [name] [money]",
        permission = "rule.pay", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.pay")
@Command(name = "sun",desc = "晴天", usage = "/sun",
        permission = "rule.sun", permissionMessage = "§6▶§f你没有使用该指令的权限§3 rule.sun")
@Permission(name = "rule.money.other", desc = "Allows foo command", defaultValue = PermissionDefault.OP)
@Permission(name = "rule.vip", desc = "Allows foo command", defaultValue = PermissionDefault.OP)
@Permission(name = "rule.einv.see", desc = "Allows foo command", defaultValue = PermissionDefault.OP)
@Permission(name = "rule.fly.give", desc = "Allows foo command", defaultValue = PermissionDefault.OP)
@Permission(name = "rule.chat.color", desc = "Allows foo command", defaultValue = PermissionDefault.OP)
@Permission(name = "rule.world.load", desc = "Allows foo command", defaultValue = PermissionDefault.OP)
@Permission(name = "rule.world.tp", desc = "Allows foo command", defaultValue = PermissionDefault.OP)
@Permission(name = "rule.spawn.build", desc = "Allows foo command", defaultValue = PermissionDefault.OP)
@Permission(name = "rule.prefix.add", desc = "Allows foo command", defaultValue = PermissionDefault.OP)
@ApiVersion(value = ApiVersion.Target.v1_14)
public class Regulation extends JavaPlugin {

    public static JavaPlugin getInstance() { return plugin; }

    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        YamlManager.iniYamlManager();
        PermissionManager.iniPermissionManager();
        EconomyManager.iniEconomyManager();
        TeleportManager.iniTeleportManager();
        ScriptItem.iniScriptItem();
        Applications.iniApplications();
        this.setExecutor();//指令监听
        this.setEventListiener();//事件监听
    }

    @Override
    public void onDisable() {

    }

    private void setEventListiener() {
        this.getServer().getPluginManager().registerEvents( new BlockEventListener(), this );
        this.getServer().getPluginManager().registerEvents( new EntityEventListener(), this );
        this.getServer().getPluginManager().registerEvents( new OtherEventListener(), this );
        this.getServer().getPluginManager().registerEvents( new PlayerEventListener(), this );
        this.getServer().getPluginManager().registerEvents( new ServerEventListener(), this );
        this.getServer().getPluginManager().registerEvents( new InventoryEventListener(), this );
    }

    private void setExecutor() {
        this.getCommand("rule").setExecutor( new RuleCommands());
        this.getCommand("fly").setExecutor( new FlyCommand());
        this.getCommand("inv").setExecutor( new InvCommand());
        this.getCommand("heal").setExecutor( new HealCommand());
        this.getCommand("mode").setExecutor( new ModeCommand());
        this.getCommand("einv").setExecutor( new EinvCommand());
        this.getCommand("table").setExecutor( new TableCommand());
        this.getCommand("my").setExecutor( new MyCommand());
        this.getCommand("home").setExecutor( new HomeCommand());
        this.getCommand("sethome").setExecutor( new SethomeCommand());
        this.getCommand("spawn").setExecutor( new SpawnCommand());
        this.getCommand("skull").setExecutor( new SkullCommand());
        this.getCommand("world").setExecutor( new WorldCommand());
        this.getCommand("back").setExecutor( new BackCommand());
        this.getCommand("warp").setExecutor( new WarpCommand());
        this.getCommand("setwarp").setExecutor( new SetwarpCommand());
        this.getCommand("kit").setExecutor( new KitCommand());
        this.getCommand("tpa").setExecutor( new TpaCommand());
        this.getCommand("tphere").setExecutor( new TphereCommand());
        this.getCommand("tpaccept").setExecutor( new TpacceptCommand());
        this.getCommand("tprefuse").setExecutor( new TprefuseCommand());
        this.getCommand("money").setExecutor( new MoneyCommand());
        this.getCommand("plugin").setExecutor( new PluginCommand());
        this.getCommand("delay").setExecutor( new DelayCommand());
        this.getCommand("sudo").setExecutor( new SudoCommand());
        this.getCommand("hat").setExecutor( new HatCommand());
        this.getCommand("top").setExecutor( new TopCommand());
        this.getCommand("up").setExecutor( new UpCommand());
        this.getCommand("name").setExecutor( new NameCommand());
        this.getCommand("lore").setExecutor( new LoreCommand());
        this.getCommand("additem").setExecutor( new AdditemCommand());
        this.getCommand("runtime").setExecutor( new RuntimeCommand());
        this.getCommand("mute").setExecutor( new MuteCommand());
        this.getCommand("dnd").setExecutor( new DndCommand());
        this.getCommand("group").setExecutor( new GroupCommand());
        this.getCommand("prefix").setExecutor( new PrefixCommand());
        this.getCommand("permission").setExecutor( new PermissionCommand());
        this.getCommand("eco").setExecutor( new EcoCommand());
        this.getCommand("pay").setExecutor( new PayCommand());
        this.getCommand("m").setExecutor( new MessageCommand());
        this.getCommand("sun").setExecutor( new SunCommand());
        this.getCommand("interact").setExecutor( new InteractCommand());
    }

}
