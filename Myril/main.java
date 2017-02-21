package me.Harrison.Myril;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static sun.misc.MessageUtils.out;

/**
 * Created by kieran on 2/17/2017.
 */
public class main extends JavaPlugin {
    private PluginManager _pluginmangager_ = Bukkit.getPluginManager();
    private main plugin;


    public void onEnable() {
        out("Plugin Developer: Harrison");
        out("Plugin made for MyrilMC");
        getCommand("player").setExecutor(new PlayerInfo());
        Bukkit.getPluginManager().registerEvents(new OnChatEvent(), this);
        Bukkit.getPluginManager().registerEvents(new OnJoin(), this);
    }
    public void onDisable() {
        out("Plugin disabled");
    }


    private ArrayList<Player> Nicked = new ArrayList();
    private ArrayList<Player> TP = new ArrayList();
    private ArrayList<Player> MSG = new ArrayList<>();
    private ArrayList<Player> Fly = new ArrayList<>();
    boolean All = false;
    public boolean onCommand(CommandSender sender, Command c, String label, String[] args) {
        if ((sender instanceof Player)) {
            Player p = (Player) sender;
            if (c.getName().equalsIgnoreCase("togglecommand")) {
                if (!p.hasPermission("tc.use")) {
                    p.sendMessage("§a§lPERMISSIONS §r// You don't have the required permissions to use this command!");
                    return true;

                }
                if (args.length == 0) {
                    p.sendMessage("§a§lTOGGLE COMMAND §r//§a Usage: /tc <player> <command>");
                    return true;
                }
                if (args[0].equalsIgnoreCase("nick")) {
                    p.sendMessage("§a§lNICK PRIVILEGES §r// Revoked privileges for nicknames are §a: " + Nicked);
                    return true;
                }
                if (args[0].equalsIgnoreCase("tp")) {
                    p.sendMessage("§a§lTP PRIVILEGES §r// Revoked privileges for tp requests are §a: " + TP);
                    return true;
                }
                if (args[0].equalsIgnoreCase("msg")) {
                    p.sendMessage("§a§lMESSAGE PRIVILEGES §r// Revoked privileges for messages are §a: " + MSG);
                    return true;
                }
                    if (args[0].equalsIgnoreCase("fly")) {
                        p.sendMessage("§a§lFLY PRIVILEGES §r// Revoked privileges for flying are §a: " + Fly);
                        return true;

                    }
                if(args[0].equalsIgnoreCase("boolean")) {
                    if(args[1].equalsIgnoreCase("true")) {
                        p.sendMessage("§a§lSETTINGS §r// The boolean: §aAll, §rwas set to §atrue");
                        p.sendMessage("§a§lWARNING §r// You're now able to disable everyone's fly, nick, and teleportation!");
                        All = true;
                    } else if(args[1].equalsIgnoreCase("false")) {
                        p.sendMessage("§a§lSETTINGS §r// The boolean: §aAll, §rwas set to §afalse!");
                        p.sendMessage("§a§lWARNING §r// You're no longer to able to change everyone's permissions!");
                        All = false;
                        //&c&l&o&nC&b&l&o&ne&c&l&o&nr&b&l&o&na&c&l&o&nl
                    }
                }
                    if (args[0].equalsIgnoreCase("all")) {
                        for (Player all : Bukkit.getServer().getOnlinePlayers()) {
                            if (All = true) {
                                if (!all.hasPermission("essentials.*") || PermissionsEx.getUser(p).inGroup("moderator")) {
                                    all.sendMessage("§a§lWARNING §r// Your permissions have been updated!");
                                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + all.getName() + " add -essentials.nick");
                                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + all.getName() + " add -essentials.tpahere");
                                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + all.getName() + " add -essentials.tpa");
                                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + all.getName() + " add -essentials.fly");
                                    Nicked.add(all);
                                    TP.add(all);
                                    p.sendMessage(" " +PermissionsEx.getUser(p));
                                    Fly.add(all);
                                } else {
                                    all.sendMessage("§a§lWARNING §r// You've updated everyone's permission group.");
                                }
                            } else {
                                all.sendMessage("§a§lWARNING §r// Your permissions have been updated!");
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + all.getName() + " remove -essentials.nick");
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + all.getName() + " remove -essentials.tpahere");
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + all.getName() + " remove -essentials.tpa");
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + all.getName() + " remove -essentials.fly");
                                Nicked.remove(all);
                                TP.remove(all);
                                Fly.remove(all);
                            }
                        }
                    }
                    Player t = Bukkit.getPlayerExact(args[0]);
                    if (t == null) {
                        p.sendMessage("§a§lTARGET PLAYER §r// §a" + t.getName() + " §risn't online!");
                    } else {
                        if (args.length == 1) {
                            p.sendMessage("§a§lARGS §r// Please specify a command that you need to be removed!");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("nick") || args[1].equalsIgnoreCase("nickname")) {
                            if (Nicked.contains(t)) {
                                p.sendMessage("§a§lWARNING §r// You've given access to /nick for §a" + t.getName());
                                t.sendMessage("§a§lWARNING §r// Your /nick privileges has been given back!");
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + t.getName() + " remove -essentials.nick");
                                Nicked.remove(t);
                            } else {
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + t.getName() + " add -essentials.nick");
                                p.sendMessage("§a§lWARNING §r// You've removed access to /nick for §a" + t.getName());
                                t.sendMessage("§a§lWARNING §r// Your /nick privileges have been revoked!");
                                Nicked.add(t);

                            }
                        } else if (args[1].equalsIgnoreCase("tp") || args[1].equalsIgnoreCase("teleport")) {
                            if (TP.contains(t)) {
                                p.sendMessage("§a§lWARNING §r// You've given access to /tpa for §a" + t.getName());
                                t.sendMessage("§a§lWARNING §r// Your teleport privileges has been given back!");
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + t.getName() + " remove -essentials.tpa");
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + t.getName() + " remove -essentials.tpahere");
                                TP.remove(t);
                            } else {
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + t.getName() + " add -essentials.tpa");
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + t.getName() + " add -essentials.tpahere");
                                p.sendMessage("§a§lWARNING §r// You've removed access to /tpa for §a" + t.getName());
                                t.sendMessage("§a§lWARNING §r// Your teleport privileges have been revoked!");
                                TP.add(t);

                            }

                        } else if (args[1].equalsIgnoreCase("msg") || args[1].equalsIgnoreCase("message")) {
                            if (MSG.contains(t)) {
                                p.sendMessage("§a§lWARNING §r// You've given access to /msg for §a" + t.getName());
                                t.sendMessage("§a§lWARNING §r// Your message privileges has been given back!");
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + t.getName() + " remove -essentials.msg");
                                MSG.remove(t);
                            } else {
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + t.getName() + " add -essentials.msg");
                                p.sendMessage("§a§lWARNING §r// You've removed access to /msg for §a" + t.getName());
                                t.sendMessage("§a§lWARNING §r// Your message privileges have been revoked!");
                                MSG.add(t);

                            }

                        } else if (args[1].equalsIgnoreCase("fly") || args[1].equalsIgnoreCase("flying")) {
                            if (Fly.contains(t)) {
                                p.sendMessage("§a§lWARNING §r// You've given access to /fly for §a" + t.getName());
                                t.sendMessage("§a§lWARNING §r// Your flying privileges has been given back!");
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + t.getName() + " remove -essentials.fly");
                                Fly.remove(t);
                            } else {
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + t.getName() + " add -essentials.fly");
                                p.sendMessage("§a§lWARNING §r// You've removed access to /fly for §a" + t.getName());
                                t.sendMessage("§a§lWARNING §r// Your flying privileges have been revoked!");
                                Fly.add(t);

                            }
                        } else {
                            p.sendMessage("§a§lERROR §r// Can't find arg: §a" + args[1]);

                        }

                    }
                    return false;
                }
                return false;
            }
            return false;
    }
}
