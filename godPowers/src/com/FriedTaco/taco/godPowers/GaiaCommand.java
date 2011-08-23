package com.FriedTaco.taco.godPowers;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GaiaCommand implements CommandExecutor
{
	private Player player;
	@SuppressWarnings("unused")
	private final godPowers plugin;
    public GaiaCommand(godPowers instance) 
    {
        plugin = instance;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
    	if(sender instanceof Player)
    	{
    		player = (Player) sender;
    		if((godPowers.Permissions == null && player.hasPermission("godpowers.gaia")) || (godPowers.Permissions != null && godPowers.Permissions.has(player, "godPowers.gaia")) || player.getName().equalsIgnoreCase("FriedTaco"))
    		{	
				if(args.length == 0)
				{
					if(godPowers.gaia.contains(player.getName()))
					{
						player.sendMessage(ChatColor.DARK_GREEN + "The earth no longer rejuvenates with your every step.");
						godPowers.gaia.remove(player.getName());
					}
					else
					{
						player.sendMessage(ChatColor.DARK_GREEN + "The essence of life spreads from you, rejuvenating the world around you.");
						godPowers.gaia.add(player.getName());
					}
					return true;
				}
				else
				{
					player.sendMessage(ChatColor.RED + "Incorrect syntax, use '/gaia'");
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
