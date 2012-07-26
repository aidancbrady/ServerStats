package me.rosswalker.serverstats;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerStats extends JavaPlugin {

	public ServerStats()
	{
		super();
	}
	

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player) {
			Player player = (Player)sender;
			if (cmd.getName().equalsIgnoreCase("ss")) {
				
				//Stats Command
				else if (args[0].equalsIgnoreCase("stats")) {
					if (player.hasPermission("stats.stats")) {
						int totalPlayers = (getServer().getOfflinePlayers().length + getServer().getOnlinePlayers().length);
						int onlinePlayers = getServer().getOnlinePlayers().length;
						int maxSlots = getServer().getMaxPlayers();
						String serverName = getServer().getName();
						String serverIP = getServer().getIp();
						int serverPort = getServer().getPort();
						int serverOPs = getServer().getOperators().size();
						String bukkitVersion = getServer().getBukkitVersion();
						
						player.sendMessage(ChatColor.BLUE + "----------ServerStats----------");
						player.sendMessage(ChatColor.GRAY + "  There are " + ChatColor.DARK_GRAY + onlinePlayers + ChatColor.GRAY + " out of " + ChatColor.DARK_GRAY + maxSlots + ChatColor.GRAY + " players online.");
						player.sendMessage(ChatColor.DARK_GRAY + "  " + serverName + ChatColor.GRAY + " is running on IP " + ChatColor.DARK_GRAY + serverIP + ChatColor.GRAY + " with port " + ChatColor.DARK_GRAY + serverPort + ChatColor.GRAY + ".");
						player.sendMessage(ChatColor.DARK_GRAY + "  " + serverName + ChatColor.GRAY + " has Bukkit version " + ChatColor.DARK_GRAY + bukkitVersion + ChatColor.GRAY + ".");
						player.sendMessage(ChatColor.GRAY + "  Lockdown status: " + ChatColor.DARK_GRAY + lockdownStatus());
						player.sendMessage(ChatColor.GRAY + "  There are currently " + ChatColor.DARK_GRAY + serverOPs + ChatColor.GRAY + " players with 'OP' status.");
						player.sendMessage(ChatColor.GRAY + "  A total of " + ChatColor.DARK_GRAY + totalPlayers + ChatColor.GRAY + " players have joined " + ChatColor.DARK_GRAY + serverName);
						player.sendMessage(ChatColor.GRAY + "  Maximum Memory: " + ChatColor.DARK_GRAY + (Runtime.getRuntime().maxMemory() / 1024 / 1024) + ChatColor.GRAY + " MB");
						player.sendMessage(ChatColor.GRAY + "  Total Memory: " + ChatColor.DARK_GRAY + (Runtime.getRuntime().totalMemory() / 1024 / 1024) + ChatColor.GRAY + " MB");
						player.sendMessage(ChatColor.GRAY + "  Free Memory: " + ChatColor.DARK_GRAY + (Runtime.getRuntime().freeMemory() / 1024 / 1024) + ChatColor.GRAY + " MB");
						player.sendMessage(ChatColor.BLUE + "--------------------------------");
						return true;
					}
					else {
						player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
					}
				}
				
				//Player Command
				
				else if (args[0].equalsIgnoreCase("player")) {
					try {
						if (args.length < 2) {
							if (player.hasPermission("stats.player")) {
								player.sendMessage(ChatColor.BLUE + "----------ServerStats----------");
								player.sendMessage(ChatColor.GRAY + "  Hello, " + ChatColor.DARK_BLUE + player.getName() + "!");
								player.sendMessage(ChatColor.GRAY + "  You are playing in gamemode '" + ChatColor.DARK_GRAY + player.getGameMode() + ChatColor.GRAY + ".'");
								player.sendMessage(ChatColor.GRAY + "  You are playing on IP " + ChatColor.DARK_GRAY + player.getAddress() + ChatColor.GRAY + ".");
								player.sendMessage(ChatColor.GRAY + "  You have " + ChatColor.DARK_GRAY + player.getHealth() + ChatColor.GRAY + " out of " + ChatColor.DARK_GRAY + player.getMaxHealth() + ChatColor.GRAY + " health.");
								player.sendMessage(ChatColor.BLUE + "--------------------------------");
								return true;
							}
							else {
								player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
							}
						}
						Player otherPlayer = player.getServer().getPlayer(args[1]);
						if (player.hasPermission("stats.player.lookup")) {
							player.sendMessage(ChatColor.BLUE + "----------ServerStats----------");
							player.sendMessage(ChatColor.GRAY + "  " + ChatColor.DARK_BLUE + otherPlayer.getName() + "'s stats:");
							player.sendMessage(ChatColor.GRAY + "  OP: " + ChatColor.DARK_GRAY + otherPlayer.isOp());
							player.sendMessage(ChatColor.GRAY + "  Online: " + ChatColor.DARK_GRAY + otherPlayer.isOnline());
							player.sendMessage(ChatColor.GRAY + "  Gamemode: " + ChatColor.DARK_GRAY + otherPlayer.getGameMode() + ChatColor.GRAY);
							player.sendMessage(ChatColor.GRAY + "  IP: " + ChatColor.DARK_GRAY + otherPlayer.getAddress());
							player.sendMessage(ChatColor.GRAY + "  Health: " + ChatColor.DARK_GRAY + otherPlayer.getHealth());
							player.sendMessage(ChatColor.GRAY + "  Location: " + ChatColor.DARK_GRAY + "(" + otherPlayer.getWorld().getName() + ", " + Math.round(otherPlayer.getLocation().getX()) + ", " + Math.round(otherPlayer.getLocation().getY()) + ", " + Math.round(otherPlayer.getLocation().getZ()) + ")");
							player.sendMessage(ChatColor.BLUE + "--------------------------------");
							return true;
						}
						else {
							player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
						}
					}
					catch (Exception e) {
						player.sendMessage(ChatColor.RED + "An error occurred, please try again.");
					}
				}
		} else {
			System.out.println("[ServerStats] The console does not have access to this plugin.");
		}
		return false;
		}

		
    public void onEnable() {
    	new SSListener(this);
    	this.getLogger().info("Plugin enabled.");
	}
	 
	public void onDisable(){ 
		this.getLogger().info("Plugin disabled.");
	}	

}

