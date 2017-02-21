package me.Harrison.Myril;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.lang.reflect.Array;

/**
 * Created by kieran on 2/20/2017.
 */
public class PlayerInfo implements CommandExecutor {
    public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
        Player p = (Player) s;
        if(c.getName().equalsIgnoreCase("player")) {
            if(!p.hasPermission("test.test")) {
                p.sendMessage("§a§lPERMISSIONS §r// Your permissions don't grant the use of this command!");
                return true;
            }
            if(args.length == 0) {
                p.sendMessage("§a§lUSAGE §r// §a/player <target>");
                return true;
            }
            Player t = Bukkit.getPlayerExact(args[0]);
            if(t == null) {
                p.sendMessage("§a§lPLAYER §r// §a" + t.getName() + "§r isn't online! Please try again later!");
                return true;
            } else {
                if(PermissionsEx.getUser(t).inGroup("admin")) {
                    p.sendMessage("§a§lERROR §r// §a" + t.getName() + "§r is a rank higher than you, can't check their info!");
                    return true;
                } else if(PermissionsEx.getUser(t).inGroup("moderator")) {
                    p.sendMessage("§a§lERROR §r// §a" + t.getName()  + "§r is a rank higher than you, can't check their info!");
                    return true;
                } else {
                    String groups;
                    String testworld;
                    if(PermissionsEx.getUser(t).inGroup("admin") || PermissionsEx.getUser(t).inGroup("owner") || PermissionsEx.getUser(t).inGroup("moderator")) {
                        groups = "§cStaff";
                    } else if(PermissionsEx.getUser(t).inGroup("donor")) {
                        groups = "donor";
                    } else {
                        groups = "Fucking nub";
                    }
                    if(t.getPlayer().getWorld().toString().contains("world_nether")) {
                        testworld = "The Nether";
                    } else if(t.getPlayer().getWorld().toString().contains("world_the_end")) {
                        testworld = "The End";
                    }
                    else if(t.getPlayer().getWorld().toString().contains("world")) {
                        testworld = "Overworld";
                    } else if(t.getPlayer().getWorld().toString().contains("Test")) {
                        testworld = "Testing World";
                    } else {
                        testworld = "Unknown world";
                    }
                    p.sendMessage("§8§l§m==============[§a§l " + t.getName() + " §8§l§m]==============");
                    p.sendMessage("§a§lIP §r// §a" + t.getAddress());
                    p.sendMessage("§a§lWORLD §r// §a" + testworld);
                    p.sendMessage("§a§lGROUPS §r// §a" + PermissionsEx.getUser(t).getName());
                    p.sendMessage("§a§lGROUPS §r// §a" + PermissionsEx.getUser(t).getPermissions("world"));
                    p.sendMessage("§a§lGROUPS §r// §a" + PermissionsEx.getUser(t).getPrefix("world"));
                    p.sendMessage("§a§lGROUPS §r// §a" + groups);
                    p.sendMessage("§8§l§m==============[§a§l " + t.getName() + " §8§l§m]==============");
                    return true;

                }
            }
        }
        return false;
    }
}
