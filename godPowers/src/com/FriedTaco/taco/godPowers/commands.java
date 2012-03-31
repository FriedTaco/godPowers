package com.FriedTaco.taco.godPowers;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commands implements CommandExecutor
{
	private Player player;
	private final godPowers plugin;
    public commands(godPowers instance) 
    {
        plugin = instance;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
    	if(sender instanceof Player)
    	{
    		player = (Player) sender;
    		if(player.hasPermission("godpowers.commands"))
    		{	
    			
				if(args.length == 0)
				{
					player.sendMessage(ChatColor.BLUE + "You can use the following commands: (< > = Optional [ ] = Required)");
					for(String s : plugin.list.keySet())
						if(player.hasPermission("godpowers."+plugin.list.get(s)))
								player.sendMessage(ChatColor.AQUA + "/" + s + " " + plugin.list.get(s));							
				}
				else
				{
					player.sendMessage("Incorrect syntax. Use '/godpowers'");
				}
				return true;
    		}
    		else
    		{
    			player.sendMessage("The gods prevent you from using this command.");
    			return true;
    		}
    	}        
        return false;
    }
}
