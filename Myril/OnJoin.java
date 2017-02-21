package me.Harrison.Myril;

/**
 * Created by kieran on 2/21/2017.
 */
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.event.Listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public final class OnJoin implements Listener {
    TextComponent message = new TextComponent("STAFF");
    @EventHandler
    public void Join(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.RED + "" + ChatColor.BOLD + "+" + ChatColor.GRAY + "] " + ChatColor.RESET + p.getName());
        p.setPlayerListName(ChatColor.RED + "" + ChatColor.BOLD + "STAFF " + ChatColor.YELLOW + "" + p.getName());
    }
}
