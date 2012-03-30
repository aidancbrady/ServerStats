package org.orecraft.serverstats;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class IPListener implements Listener {
	private ServerStats plugin;
	
	public IPListener(ServerStats plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
		
    }
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent event) {
		String playerIP = event.getPlayer().getAddress().getAddress().toString().replaceAll("/","");
		String playerName = event.getPlayer().getName();
		
		File playerLogger = new File(plugin.getDataFolder() + "/players.txt");
		if (playerLogger.exists()) {
			try {
				Logger log = Logger.getLogger("Minecraft");
				log.info("[ServerStats] Creating database for player '" + playerName + ".'");
				BufferedWriter writer = new BufferedWriter(new FileWriter(playerLogger, true));
				writer.append(getDate() + "," + playerName + "," + playerIP);
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
