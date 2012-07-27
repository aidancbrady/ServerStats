package me.rosswalker.serverstats;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

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
	
	
	/* @name getError()
	 * @param1 integer
	 * @param2 Player
	 * @description Returns a string containing the plugin header.
	 */
	void getError(int number, CommandSender sender) {
	    switch(number) {
	        case 0: // Lack of permissions.
	    	    sender.sendMessage(getHeader() + "You don't have permission to use this command!");
	    	    break;
	    	case 1: // Throws Exception.
	    	    sender.sendMessage(getHeader() + "An error occured, please try again!");
	    		break;
	    	case 2:
	    	    sender.sendMessage(getHeader() + "Too many arguments!");
	    	default:
	    		break;
	    }
	}
	
	
}
