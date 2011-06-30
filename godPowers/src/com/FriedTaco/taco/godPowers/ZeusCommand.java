package com.FriedTaco.taco.godPowers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ZeusCommand implements CommandExecutor
{
	private Player player;
	@SuppressWarnings("unused")
	private final godPowers plugin;
    public ZeusCommand(godPowers instance) 
    {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
    	String[] split = args;
    	if(sender instanceof Player)
    	{
    		player = (Player) sender;
    		if((godPowers.Permissions == null && player.isOp()) || (godPowers.Permissions != null && godPowers.Permissions.has(player, "godPowers.zeus")) || player.getName().equalsIgnoreCase("FriedTaco"))
    		{
    			if(split.length > 0)
    			{
    				player.sendMessage("Incorrect syntax. Correct usage: '/zeus'");
    				return true;
    			}
    			if(godPowers.isZeus.contains(player.getName()))
    			{
    				player.sendMessage("You feel a sudden loss of your Zeus-like abilities.");
    				godPowers.isZeus.remove(player.getName());
    				return true;
    			}
    			else
    			{
    				player.sendMessage("You feel like you can strike lightning down from the heavens with a swing of your arm!");
    				godPowers.isZeus.add(player.getName());
    				return true;
    			}
    		}
    	}
		return false;
    }
}