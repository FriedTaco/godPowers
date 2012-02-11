package com.FriedTaco.taco.godPowers;

//import org.bukkit.World;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class godModeCommand implements CommandExecutor
{
	private Player player;
	private final godPowers plugin;
    public godModeCommand(godPowers instance) 
    {
        plugin = instance;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
    	String[] split = args;
    	if(sender instanceof Player)
    	{
    		player = (Player) sender;
    		if((godPowers.Permissions == null && player.hasPermission("godpowers.godmode")) || (godPowers.Permissions != null && godPowers.Permissions.has(player, "godPowers.godmode")) || player.getName().equalsIgnoreCase("FriedTaco"))
    		{
	    		if(split.length == 0)
	    		{
	    			if(godPowers.godmodeEnabled.contains(player.getName()))
	    			{
	    				if(command.getName().equalsIgnoreCase("godmodeon"))
	    				{
	    					player.sendMessage(ChatColor.BLUE + "You are already invincible!");
	    					return true;
	    				}
	    				godPowers.godmodeEnabled.remove(player.getName());
	    				player.sendMessage(ChatColor.BLUE + "You have returned to being mortal.");
	    				player.setDisplayName(player.getName());
	    				return true;
	    			}
	    			else
	    			{
	    				if(command.getName().equalsIgnoreCase("godmodeoff"))
	    				{
	    					player.sendMessage(ChatColor.BLUE + "You aren't invincible!");
	    					return true;
	    				}
	    				player.sendMessage(ChatColor.BLUE + "You are now invincible!");
	    				player.setDisplayName(godPowers.title + player.getDisplayName());
	    				godPowers.godmodeEnabled.add(player.getName());
	    				player.setHealth(20);
	    				return true;
	        		}
	    		}
	    		else
	    		{
	    			Player targetPlayer = plugin.getServer().getPlayer(split[0]);
	    			if(targetPlayer==null) 
					{
						player.sendMessage(ChatColor.RED + "The user "+split[0]+" does not exist or is not currently logged in.");
					}
	    			else if(targetPlayer == player)
					{
						player.sendMessage(ChatColor.RED + "Please use '/godmode' to godmode yourself.");
	
					}
					else
					{
						if(godPowers.godmodeEnabled.contains(targetPlayer.getName()))
	        			{
							if(command.getName().equalsIgnoreCase("godmodeon"))
		    				{
		    					player.sendMessage(ChatColor.RED + "They're already invincible!");
		    					return true;
		    				}
	        				godPowers.godmodeEnabled.remove(targetPlayer.getName());
	        				targetPlayer.sendMessage(ChatColor.BLUE + player.getName() + " has returned you to being mortal.");
	        				targetPlayer.setDisplayName(targetPlayer.getName());
	        				player.sendMessage(ChatColor.BLUE + targetPlayer.getName() + " has been returned to being mortal.");
	        			}
	        			else
	            		{
	        				if(command.getName().equalsIgnoreCase("godmodeoff"))
		    				{
		    					player.sendMessage("They aren't invincible!");
		    					return true;
		    				}
	        				targetPlayer.sendMessage(ChatColor.BLUE + "By the power of "+ player.getName() + " you are now invincible!");
	            			targetPlayer.setDisplayName(godPowers.title + targetPlayer.getName());
	            			godPowers.godmodeEnabled.add(targetPlayer.getName());
	            			targetPlayer.setHealth(20);
	            			player.sendMessage(ChatColor.BLUE + targetPlayer.getName() + " has been given invincibility.");
	            		}
					}
					return true;
	    		}
    		}
    		else
    		{
    			player.sendMessage(ChatColor.DARK_RED + "The gods prevent you from using this command.");
    			return true;
    		}
    	}        
        return false;
    }
}
