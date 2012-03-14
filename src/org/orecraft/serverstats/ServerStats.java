package org.orecraft.serverstats;

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
	public static String versionNumber = "1.0.1";
	public ServerStats()
	{
		super();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player) {
			String command = cmd.getName().toLowerCase();
			Player player = (Player)sender;
			if (command.equals("ss") || command.equals("server") || command.equals("serverstats") || command.equals("s")) {
				String param1 = args.length > 0 ? args[0] : "";
				String param2 = args.length > 1 ? args[1] : "";

				//Info Command
				
				if (param1.equals("info")) {
					if (player.hasPermission("stats.info")) {
						player.sendMessage(ChatColor.BLUE + "----------ServerStats------------");
						player.sendMessage(ChatColor.GRAY + "  You are running" + ChatColor.DARK_GRAY + " ServerStats" + ChatColor.GRAY + " version" + ChatColor.GOLD + versionNumber);
						player.sendMessage(ChatColor.GRAY + "  Plugin is currently " + ChatColor.GREEN + "enabled.");
						player.sendMessage(ChatColor.GRAY + "  Type " + ChatColor.DARK_GRAY + "/ss help " + ChatColor.GRAY + "for a list of commands.");
						player.sendMessage(ChatColor.BLUE + "----------------------------------");
						return true;
					}
					else {
						player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
					}
				}
				
				//Credits Command
				
				else if (param1.equals("credits")) {
					if (player.hasPermission("stats.credits")) {
						player.sendMessage(ChatColor.BLUE + "----------ServerStats----------");
						player.sendMessage(ChatColor.GRAY + "  Programmed and compiled by " + ChatColor.DARK_GRAY + "Aidan Brady.");
						player.sendMessage(ChatColor.GRAY + "  Made using " + ChatColor.DARK_GRAY + "Eclipse build 20110916-0149");
						player.sendMessage(ChatColor.GRAY + "  Referenced with " + ChatColor.DARK_GRAY + "Bukkit 1.2.3-R0.2 API Developer Snapshot");
						player.sendMessage(ChatColor.BLUE + "--------------------------------");
						return true;
					}
					else {
						player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
					}
				}
				
				//Help Command
				
				else if (param1.equals("help")) {
					if (player.hasPermission("stats.help")) {
						if (param2.equals("")) {
							player.sendMessage(ChatColor.BLUE + "----------ServerStats----------");
							player.sendMessage(ChatColor.DARK_GRAY + "  /ss help " + ChatColor.GRAY + "- displays help for ServerStats.");
							player.sendMessage(ChatColor.DARK_GRAY + "  /ss help <cmd> " + ChatColor.GRAY + "- displays help for a command.");
							player.sendMessage(ChatColor.DARK_GRAY + "  /ss credits " + ChatColor.GRAY + "- displays credits for ServerStats.");
							player.sendMessage(ChatColor.DARK_GRAY + "  /ss player " + ChatColor.GRAY + "- displays your player info.");
							player.sendMessage(ChatColor.DARK_GRAY + "  /ss player <player> " + ChatColor.GRAY + "- displays info of an online player.");
							player.sendMessage(ChatColor.DARK_GRAY + "  /ss rage " + ChatColor.GRAY + "- let's not go there.");
							player.sendMessage(ChatColor.DARK_GRAY + "  /ss rage <player> " + ChatColor.GRAY + "- enraged? Kill someone.");
							player.sendMessage(ChatColor.DARK_GRAY + "  /ss stats " + ChatColor.GRAY + "- displays stats for ServerStats.");
							player.sendMessage(ChatColor.DARK_GRAY + "  /ss info " + ChatColor.GRAY + "- displays info for ServerStats.");
							player.sendMessage(ChatColor.BLUE + "--------------------------------");
							return true;
						}
						
						else if (param2.equals("help")) {
							player.sendMessage(ChatColor.DARK_GRAY + "/ss help " + ChatColor.GRAY + "- displays help for ServerStats.");
							return true;
						}
						else if (param2.equals("stats")) {
							player.sendMessage(ChatColor.DARK_GRAY + "/ss stats " + ChatColor.GRAY + "- displays stats for ServerStats.");
							return true;
						}
						else if (param2.equals("info")) {
							player.sendMessage(ChatColor.DARK_GRAY + "/ss info " + ChatColor.GRAY + "- displays info for ServerStats.");
							return true;	
						}
						else if (param2.equals("credits")) {
							player.sendMessage(ChatColor.DARK_GRAY + "/ss credits " + ChatColor.GRAY + "- displays credits for ServerStats.");
							return true;
						}
						else if (param2.equals("player")) {
							player.sendMessage(ChatColor.DARK_GRAY + "/ss player " + ChatColor.GRAY + "- displays player info for ServerStats.");
							return true;
						}
						else if (param2.equals("rage")) {
							player.sendMessage(ChatColor.DARK_GRAY + "/ss rage " + ChatColor.GRAY + "- let's not go there.");
							return true;
						}
						else {
							player.sendMessage(ChatColor.GRAY + "Command not recognized. Try another.");
							return true;
						}
					}
					else {
						player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
					}
				}
				
				//Stats Command
				
				else if (param1.equals("stats")) {
					if (player.hasPermission("stats.stats")) {
						int totalPlayers = (getServer().getOfflinePlayers().length + getServer().getOnlinePlayers().length);
						int onlinePlayers = getServer().getOnlinePlayers().length;
						int maxSlots = getServer().getMaxPlayers();
						String serverName = getServer().getName();
						String serverIP = getServer().getIp();
						int serverPort = getServer().getPort();
						int serverOPs = getServer().getOperators().size();
						String bukkitVersion = getServer().getBukkitVersion();
						String minecraftVersion = getServer().getVersion();
						player.sendMessage(ChatColor.BLUE + "----------ServerStats----------");
						player.sendMessage(ChatColor.GRAY + "  There are " + ChatColor.DARK_GRAY + onlinePlayers + ChatColor.GRAY + " out of " + ChatColor.DARK_GRAY + maxSlots + ChatColor.GRAY + " players online.");
						player.sendMessage(ChatColor.DARK_GRAY + "  " + serverName + ChatColor.GRAY + " is running on IP " + ChatColor.DARK_GRAY + serverIP + ChatColor.GRAY + " with port " + ChatColor.DARK_GRAY + serverPort + ChatColor.GRAY + ".");
						player.sendMessage(ChatColor.DARK_GRAY + "  " + serverName + ChatColor.GRAY + " has Bukkit version " + ChatColor.DARK_GRAY + bukkitVersion + ChatColor.GRAY + " and is running on Minecraft " + ChatColor.DARK_GRAY + minecraftVersion + ChatColor.GRAY + ".");
						player.sendMessage(ChatColor.GRAY + "  There are currently " + ChatColor.DARK_GRAY + serverOPs + ChatColor.GRAY + " players with 'OP' status.");
						player.sendMessage(ChatColor.GRAY + "  A total of " + ChatColor.DARK_GRAY + totalPlayers + ChatColor.GRAY + " have joined " + ChatColor.DARK_GRAY + serverName);
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
				
				else if (param1.equals("player")) {
					//If the command sender didn't type the "x" in (/ss player x) then...
					if (args.length < 2) {
						//If the command sender has this permission then...
						if (player.hasPermission("stats.player")) {
							player.sendMessage(ChatColor.BLUE + "----------ServerStats----------");
							player.sendMessage(ChatColor.GRAY + "  Hello, " + ChatColor.DARK_BLUE + player.getDisplayName() + "!");
							player.sendMessage(ChatColor.GRAY + "  You are playing in gamemode '" + ChatColor.DARK_GRAY + player.getGameMode() + ChatColor.GRAY + ".'");
							player.sendMessage(ChatColor.GRAY + "  You are playing on IP " + ChatColor.DARK_GRAY + player.getAddress() + ChatColor.GRAY + ".");
							player.sendMessage(ChatColor.GRAY + "  You have " + ChatColor.DARK_GRAY + player.getHealth() + ChatColor.GRAY + " out of " + ChatColor.DARK_GRAY + player.getMaxHealth() + ChatColor.GRAY + " health.");
							player.sendMessage(ChatColor.BLUE + "--------------------------------");
							return true;
						//If he doesn't have permission then...
						}
						else {
							player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
						}
					}
					Player otherPlayer = player.getServer().getPlayer(args[1]);
					//If the command sender has this permission then...
					if (player.hasPermission("stats.player.lookup")) {
						//If the other player is online then...
						if (getServer().getPlayer(args[1]).isOnline()) {
							player.sendMessage(ChatColor.BLUE + "----------ServerStats----------");
							player.sendMessage(ChatColor.GRAY + "  " + ChatColor.DARK_BLUE + otherPlayer.getDisplayName() + "'s stats:");
							player.sendMessage(ChatColor.GRAY + "  OP: " + ChatColor.DARK_GRAY + otherPlayer.isOp());
							player.sendMessage(ChatColor.GRAY + "  Online: " + ChatColor.DARK_GRAY + otherPlayer.isOnline());
							player.sendMessage(ChatColor.GRAY + "  Gamemode: " + ChatColor.DARK_GRAY + otherPlayer.getGameMode() + ChatColor.GRAY);
							player.sendMessage(ChatColor.GRAY + "  IP: " + ChatColor.DARK_GRAY + otherPlayer.getAddress());
							player.sendMessage(ChatColor.GRAY + "  Health: " + ChatColor.DARK_GRAY + otherPlayer.getHealth());
							player.sendMessage(ChatColor.GRAY + "  Location: " + ChatColor.DARK_GRAY + "(" + otherPlayer.getWorld().getName() + ", " + Math.round(otherPlayer.getLocation().getX()) + ", " + Math.round(otherPlayer.getLocation().getY()) + ", " + Math.round(otherPlayer.getLocation().getZ()) + ")");
							player.sendMessage(ChatColor.BLUE + "--------------------------------");
							return true;
						//If the other player is NOT online then...
						}
						else  if (!getServer().getPlayer(args[1]).isOnline()) {
							player.sendMessage(ChatColor.DARK_GRAY + otherPlayer.getName() + ChatColor.GRAY + " is not online.");
							return true;
						}
					//If the command sender doesn't have permission then...
					}
					else {
						player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
					}
					
				}
				
				//Rage Command
				
				else if (param1.equals("rage")) {
					if (player.hasPermission("stats.rage")) {
						Player otherPlayer = player.getServer().getPlayer(param2);
						if (getServer().matchPlayer(param2).isEmpty() || getServer().matchPlayer(param2).equals("")) {
							player.sendMessage(ChatColor.GRAY + "Invalid parameters. Try again.");
						}
						else {
							int i;
							World world = otherPlayer.getWorld();
							float explosionPower = 3.0F;
							Location location = otherPlayer.getLocation();
							player.sendMessage(ChatColor.GRAY + "Killing player '" + ChatColor.BLUE + otherPlayer.getName() + ChatColor.GRAY + "...'");
							otherPlayer.sendMessage(ChatColor.GRAY + "Prepare to be destroyed!");
							for (i=0;i<5;i++) {
								world.strikeLightning(location);
							}
							otherPlayer.getWorld().createExplosion(location, explosionPower);
							world.createExplosion(location, explosionPower);
							player.sendMessage(ChatColor.GRAY + "Player '" + ChatColor.BLUE + otherPlayer.getName() + ChatColor.GRAY + "' has been destroyed.");
						}
					}
					else {
						player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
					}
				}
				//Null Command
				
				else if (param1.equals("")) {
					if (player.hasPermission("stats.null")) {
						player.sendMessage(ChatColor.BLUE + "----------ServerStats----------");
						player.sendMessage(ChatColor.GRAY + "  ServerStats version " + versionNumber + " loaded.");
						player.sendMessage(ChatColor.GRAY + "  Type " + ChatColor.DARK_GRAY + "/ss help " + ChatColor.GRAY + "for a list of commands.");
						player.sendMessage(ChatColor.BLUE + "--------------------------------");
						return true;
					}
					else {
						player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
					}
				}
				
				//Unknown Command
				
				else {
					if (player.hasPermission("stats.unknown")) {
						player.sendMessage(ChatColor.BLUE + "----------ServerStats----------");
						player.sendMessage(ChatColor.GRAY + "  Command not recognized.");
						player.sendMessage(ChatColor.GRAY + "  Type " + ChatColor.DARK_GRAY + "/ss help " + ChatColor.GRAY + "for a list of commands.");
						player.sendMessage(ChatColor.BLUE + "--------------------------------");
						return true;
					}
					else {
						player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
					}
				}
			}
		}
		else {
			System.out.println("[ServerStats] The console does not have access to this plugin.");
		}
		return false;
	}

	Logger log;
	
    public void onEnable() {
    	new IPListener(this);
        if (!new File(getDataFolder().toString()).exists()) {
            new File(getDataFolder().toString()).mkdir();
          }
    	
    	File file = new File(getDataFolder() + "/players.txt"); 
    	
    	if (!file.exists()) {
    		try {
    			file.createNewFile();
    		} catch (IOException e) {
    			System.out.println("[ServerStats] Unable to create 'players.txt' file in " + file.getPath() + "/" + file.getName());
    		}
    	}
    	
		 log = this.getLogger();
		 log.info("Plugin enabled.");
	}
	 
	public void onDisable(){ 
		 log.info("Plugin disabled.");
	}	

}

