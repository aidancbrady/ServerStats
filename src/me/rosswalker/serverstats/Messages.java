package me.rosswalker.serverstats;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/* @name Messages
 * @author DicaxDorcas
 * @description Class to handle getters for player messages.
 */


public class Messages {

	
	ServerStats plugin;
	
	
	/* @name getHeader()
	 * @description Returns a string containing the plugin header.
	 */
	String getHeader() {
	    return ChatColor.AQUA +"[" + plugin.getName() + "] " + ChatColor.WHITE;
	}
	
	
	/* @name getHeader()
	 * @param1 integer
	 * @param2 Player
	 * @description Returns a string containing the plugin header.
	 */
	void getError(int number, Player player) {
	    switch(number) {
	        case 0: // Lack of permissions.
	    	    player.sendMessage(getHeader() + "You don't have permission to use this command!");
	    	    break;
	    	case 1: // Throws Exception.
	    	    player.sendMessage(getHeader() + "An error occured, please try again!");
	    		break;
	    	default:
	    		break;
	    }
	}
	
	
}
