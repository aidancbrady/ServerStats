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
	public static String versionNumber = "1.0.3";
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
			if (command.equals("ss") || command.equals("server") || command.equals("serverstats")) {
				String param1 = args.length > 0 ? args[0] : "";
				String param2 = args.length > 1 ? args[1] : "";
				String param3 = args.length > 2 ? args[2] : "";

				//Info Command
				
				if (param1.equals("info")) {
					if (player.hasPermission("stats.info")) {
						player.sendMessage(ChatColor.BLUE + "----------ServerStats------------");
						player.sendMessage(ChatColor.GRAY + "  You are running" + ChatColor.DARK_GRAY + " ServerStats" + ChatColor.GRAY + " version " + ChatColor.GOLD + versionNumber);
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
						player.sendMessage(ChatColor.GRAY + "  Referenced with " + ChatColor.DARK_GRAY + getServer().getBukkitVersion());
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
							player.sendMessage(ChatColor.DARK_GRAY + "  /ss help [cmd] " + ChatColor.GRAY + "- displays help for ServerStats.");
							player.sendMessage(ChatColor.DARK_GRAY + "  /ss credits " + ChatColor.GRAY + "- displays credits for ServerStats.");
							player.sendMessage(ChatColor.DARK_GRAY + "  /ss player [player] " + ChatColor.GRAY + "- displays info of an online player.");
							player.sendMessage(ChatColor.DARK_GRAY + "  /ss rage [player] [damage] " + ChatColor.GRAY + "- enraged? Kill someone.");
							player.sendMessage(ChatColor.DARK_GRAY + "  /ss stats " + ChatColor.GRAY + "- displays stats for ServerStats.");
							player.sendMessage(ChatColor.DARK_GRAY + "  /ss reset " + ChatColor.GRAY + "- resets ServerStats data.");
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
						else if (param2.equals("reset")) {
							player.sendMessage(ChatColor.DARK_GRAY + "/ss reset " + ChatColor.GRAY + "- resets ServerStats data.");
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
							player.sendMessage(ChatColor.RED + "Command not recognized. Try another.");
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
					try {
						if (args.length < 2) {
							if (player.hasPermission("stats.player")) {
								player.sendMessage(ChatColor.BLUE + "----------ServerStats----------");
								player.sendMessage(ChatColor.GRAY + "  Hello, " + ChatColor.DARK_BLUE + player.getDisplayName() + "!");
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
							player.sendMessage(ChatColor.GRAY + "  " + ChatColor.DARK_BLUE + otherPlayer.getDisplayName() + "'s stats:");
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
				
				//Rage Command
				
				else if (param1.equals("rage")) {
					try {
						if (player.hasPermission("stats.rage")) {
							int damage;
							Player target = getServer().getPlayer(param2);
							if (param2.equals("") && param3.equals("")) {
								player.sendMessage(ChatColor.GRAY + "May Notch's wrath rain upon thou, " + ChatColor.DARK_BLUE + player.getDisplayName() + ChatColor.GRAY + "!");
								rage(player);
							}
							else if (!param2.equals("") && param3.equals("")) {
								player.sendMessage(ChatColor.GRAY + "Raging player '" + ChatColor.DARK_BLUE + target.getDisplayName() + ChatColor.GRAY + ".'");
								target.sendMessage(ChatColor.GRAY + "May Notch's wrath rain upon thou, " + ChatColor.DARK_BLUE + target.getDisplayName() + ChatColor.GRAY + "!");
								rage(target);
							}
							else if (!param2.equals("") && !param3.equals("")) {
								player.sendMessage(ChatColor.GRAY + "Raging player '" + ChatColor.DARK_BLUE + target.getDisplayName() + ChatColor.GRAY + ".'");
								target.sendMessage(ChatColor.GRAY + "May Notch's wrath rain upon thou, " + ChatColor.DARK_BLUE + target.getDisplayName() + ChatColor.GRAY + "!");
								damage = Integer.parseInt(param3);
								rage(target, damage);
							}
							else {
								player.sendMessage(ChatColor.RED + "Invalid parameters. Try again.");
							}
						}
						else {
							player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
						}
					}
					catch (Exception e)
					{
						player.sendMessage(ChatColor.RED + "Invalid parameters. Try again.");
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
				
				//Reset Command
				
				else if (param1.equals("reset")) {
					if (player.hasPermission("stats.reset")) {
						File playerLogger = new File(getDataFolder() + "/players.txt");
						if (playerLogger.exists()) {
							try {
								player.sendMessage(ChatColor.BLUE + "----------ServerStats----------");
								playerLogger.delete();
								System.out.println("'players.txt' file deleted. Creating new file...");
								player.sendMessage(ChatColor.GRAY + "ServerStats data emptied. Generating new file...");
								playerLogger.createNewFile();
								player.sendMessage(ChatColor.GRAY + "File created.");
								System.out.println("'players.txt' created.");
								player.sendMessage(ChatColor.BLUE + "--------------------------------");
								
							}
							catch (IOException e)
							{
								player.sendMessage(ChatColor.RED + "An error occured, see console for more info.");
								player.sendMessage(ChatColor.BLUE + "--------------------------------");
								System.out.println("[ServerStats] Unable to create 'players.txt' file in " + playerLogger.getPath() + "/" + playerLogger.getName());
								e.printStackTrace();
							}
						}
						else {
							try {
								player.sendMessage(ChatColor.BLUE + "----------ServerStats----------");
								player.sendMessage(ChatColor.GRAY + "ServerStats has not yet created data files. Creating now...");
								System.out.println("[ServerStats] Creating 'players.txt' file...");
								playerLogger.createNewFile();
								player.sendMessage(ChatColor.GRAY + "File created.");
								System.out.println("File created.");
								player.sendMessage(ChatColor.BLUE + "--------------------------------");
							}
							catch (IOException e)
							{
								player.sendMessage(ChatColor.RED + "An error occured, see console for more info.");
								player.sendMessage(ChatColor.BLUE + "--------------------------------");
								System.out.println("[ServerStats] Unable to create 'players.txt' file in " + playerLogger.getPath() + "/" + playerLogger.getName());
								e.printStackTrace();
							}
						}
					}
				}
				
				//Unknown Command
				
				else {
					if (player.hasPermission("stats.unknown")) {
						player.sendMessage(ChatColor.BLUE + "----------ServerStats----------");
						player.sendMessage(ChatColor.RED + "  Command not recognized.");
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
	
	/**
	 * Rage command with second parameter.
	 * @param player
	 * @param damage
	 * @return
	 */
	
	public boolean rage(Player player, int damage)
	{
		float explosionPower = damage;
		int lightningStrikes = damage;
		World world = player.getWorld();
		Location location = player.getLocation();
		for (int i=0;i<lightningStrikes;i++) {
			world.strikeLightning(location);
			try {
				Thread.currentThread();
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		world.createExplosion(location, explosionPower);
		return true;
	}
	
	/**
	 * Rage command without 2nd parameter.
	 * @param player
	 * @return
	 */
	
	public boolean rage(Player player)
	{
		return rage(player, 5);
	}
	
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

