package me.Harrison.Myril;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public final class OnChatEvent implements Listener {
    TextComponent message = new TextComponent("STAFF");
    TextComponent messsagetwo = new TextComponent("");

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        message.setBold(true);
        message.setColor(net.md_5.bungee.api.ChatColor.RED);
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("This is a staff rank").create()));
        if(p.hasPermission("op.me")){
            for(Player t : Bukkit.getOnlinePlayers()) {
                event.setCancelled(true);
                event.setFormat("");
                TextComponent Testing = new TextComponent(" " + p.getName() + " " + event.getMessage().replace("&","§"));
                t.spigot().sendMessage(message, Testing);
            }
        } else {
            event.setFormat(ChatColor.YELLOW + "" + p.getDisplayName() + ChatColor.RESET + " " + event.getMessage().replace("&","§"));
        }
    }

}
