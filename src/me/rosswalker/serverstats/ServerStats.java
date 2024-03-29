package me.rosswalker.serverstats;

import org.bukkit.plugin.java.JavaPlugin;

/* @name ServerStats
 * @author uberKat, DicaxDorcas
 * @description Central class for the ServerStats plugin.
 */


public class ServerStats extends JavaPlugin {
    
	private Executor executor;
	
	/* @name onEnable()
	 * @description Ran when the plugin is enabled.
	 * 
	 * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
	 */
    public void onEnable() {
    	this.getLogger().info("Plugin enabled.");
    	
    	executor = new Executor(this);
    	getCommand("ss").setExecutor(executor);
	}
	
    
    /* @name onDisable()
	 * @description Ran when the plugin is disabled.
	 * 
	 * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
	 */
	public void onDisable() { 
		this.getLogger().info("Plugin disabled.");
	}	

}

