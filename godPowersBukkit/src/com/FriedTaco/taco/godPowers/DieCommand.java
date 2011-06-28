package com.FriedTaco.taco.godPowers;

//import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DieCommand implements CommandExecutor
{
	private Player player;
	@SuppressWarnings("unused")
	private final godPowers plugin;
    public DieCommand(godPowers instance) 
    {
        plugin = instance;
    }

	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
    	if(sender instanceof Player)
    	{
    		player = (Player) sender;
    		if((godPowers.Permissions == null && player.isOp()) || (godPowers.Permissions != null && godPowers.Permissions.has(player, "godPowers.die")))
    		{
    			if(godPowers.godmodeEnabled.contains(player.getName()))
    			{
    				player.sendMessage("Your godly powers prevent you from death.");
    				return true;
    			}
    			else
    			{			
    				player.setHealth(0);
    				godPowers.dropDeadItems(player);
    				player.sendMessage("You have died.");
    				return true;
    			}
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
