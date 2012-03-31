package com.FriedTaco.taco.godPowers;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BlessCommand implements CommandExecutor
{
	private Player player;
	private final godPowers plugin;
    public BlessCommand(godPowers instance) 
    {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
    	String[] split = args;
    	if(sender instanceof Player)
    	{
    		player = (Player) sender;
    		if(player.hasPermission("godpowers.bless"))
    		{
    			if(split.length > 0)
    			{
    				player.sendMessage(ChatColor.RED + "Incorrect syntax. Correct usage: '/bless'");
    				return true;
    			}
    			else
    			{
    				player.sendMessage(ChatColor.BLUE + "The gods have blessed your equipment with their holy might!");
    				plugin.bless(player);
    				return true;
    			}
    		}
    		else
    		{
    			player.sendMessage(ChatColor.DARK_RED + "The gods prevent you from using this command.");
    		}
    	}
		return false;
    }
}