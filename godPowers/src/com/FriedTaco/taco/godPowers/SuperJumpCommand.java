package com.FriedTaco.taco.godPowers;

//import org.bukkit.World;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SuperJumpCommand implements CommandExecutor
{
	private Player player;
	@SuppressWarnings("unused")
	private final godPowers plugin;
    public SuperJumpCommand(godPowers instance) 
    {
        plugin = instance;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
    	if(sender instanceof Player)
    	{
    		player = (Player) sender;
    		if((godPowers.Permissions == null && player.hasPermission("godpowers.superjump")) || (godPowers.Permissions != null && godPowers.Permissions.has(player, "godPowers.superjump")) || player.getName().equalsIgnoreCase("FriedTaco"))
    		{
	    		if(godPowers.superJumper.contains(player.getName()))
	    		{
	    			godPowers.superJumper.remove(player.getName());
	    			player.sendMessage(ChatColor.BLUE + "You can no longer jump great heights.");
	    		}
	    		else
	        	{
	    			player.sendMessage(ChatColor.BLUE + "You feel like you can leap tall building in a single bound!");
	        		godPowers.superJumper.add(player.getName());
	        	}
	    		return true;
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
