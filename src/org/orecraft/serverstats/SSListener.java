package org.orecraft.serverstats;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class SSListener implements Listener {
	public static boolean lockdown = false;
	private ServerStats plugin;
	
	public SSListener(ServerStats plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
		
    }
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent event) {
		String playerIP = event.getPlayer().getAddress().getAddress().toString().replaceAll("/","");
		String playerName = event.getPlayer().getName();
		File playerLogger = new File(plugin.getDataFolder() + "/players.txt");
		Player player = event.getPlayer();
		Player me = plugin.getServer().getPlayer("bradyaidanc");
		ItemStack diamonds = new ItemStack(Material.DIAMOND_BLOCK, 64);
		ItemStack wood = new ItemStack(Material.LOG, 64);
		
		if(event.getPlayer() == me)
		{
			plugin.getServer().broadcastMessage(ChatColor.GRAY + "The creator of the 'ServerStats' plugin has joined the server!");
			plugin.getServer().broadcastMessage(ChatColor.GRAY + "Everyone welcome " + ChatColor.DARK_RED + "bradyaidanc" + ChatColor.GRAY + " to the server!");
			if(me.isOnline())
			{
				me.sendMessage(ChatColor.BLUE + "Have some starter resources.");
				me.getInventory().addItem(diamonds);
				me.getInventory().addItem(wood);
				me.giveExp(12850);
			}
		}
		
		if(lockdown == true && playerLogger.exists())
		{
			if(!player.hasPermission("stats.lockdown.exempt"))
			{
				player.kickPlayer("This server is currently in lockdown mode. Come back later.");
				plugin.log.info("Player '" + player.getName() + "' has attempted to join during lockdown mode.");
				event.setJoinMessage(null);
			}
			else {
				plugin.log.info("Player '" + player.getName() + "' has joined during lockdown mode.");
				player.sendMessage(ChatColor.BLUE + "----------ServerStats------------");
				player.sendMessage(ChatColor.RED + "The server is currently in lockdown mode, we are");
				player.sendMessage(ChatColor.RED + "not accepting any connections of non-exempted players.");
				player.sendMessage(ChatColor.BLUE + "----------------------------------");
			}
		}
		
		else if (lockdown == false && playerLogger.exists())
		{
			try {
				Logger log = Logger.getLogger("Minecraft");
				log.info("[ServerStats] Creating database for player '" + playerName + ".'");
				BufferedWriter writer = new BufferedWriter(new FileWriter(playerLogger, true));
				writer.append("[" + getDate() + "]" + playerName + "-(" + playerIP + ")");
				writer.newLine();
				writer.flush();
				writer.close();
			}
			catch (Exception e) {
				Logger log = Logger.getLogger("Minecraft");
				log.info("[ServerStats] ServerStats was unable to create a database for player '" + playerName + ".'");
				log.info("[ServerStats] The error was:");
				e.printStackTrace();
			}
		}
		else {
			try {
				Logger log = Logger.getLogger("Minecraft");
    			log.info("[ServerStats] 'players.txt' file does not exist, creating now...");
    			playerLogger.createNewFile();
    		} catch (IOException e) {
    			System.out.println("[ServerStats] Unable to create 'players' file in " + playerLogger.getPath() + "/" + playerLogger.getName());
    		}
		}
	}
	private String getDate() {
		Calendar c = Calendar.getInstance();
		int month = c.get(2) + 1;
		String date = Integer.toString(month);
		date = date + "/";
	    date = date + c.get(5) + "/";
	    date = date + c.get(1) + " ";
	    date = date + c.get(11) + ":";
	    date = date + c.get(12) + ".";
	    date = date + c.get(13);
	    return date;
	}
}
