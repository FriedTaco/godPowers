package com.FriedTaco.taco.godPowers;

import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlutusCommand implements CommandExecutor
{
	private Player player;
	@SuppressWarnings("unused")
	private final godPowers plugin;
    public PlutusCommand(godPowers instance) 
    {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
    	String[] split = args;
    	if(sender instanceof Player)
    	{
    		player = (Player) sender;
    		if(player.hasPermission("godpowers.plutus"))
    		{
    			if(split.length > 0)
    			{
    				player.sendMessage(ChatColor.RED + "Incorrect syntax. Correct usage: '/plutus'");
    				return true;
    			}
    			else
    			{
    				int poss[] = {256,257,258,269,270,271,273,274,275,277,278,279,284,285,286};
    				ArrayList<Integer> possible = new ArrayList<Integer>();
    				for(int i=0;i<poss.length;i++)
    					possible.add(poss[i]);
    				ItemStack i = player.getItemInHand();
    				if(i != null && possible.contains(Integer.valueOf(i.getTypeId())))
    				{
    					player.sendMessage(ChatColor.GOLD + "You suddenly feel as if earthly riches will come easily to you.");
    					i.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 25);
    				}
    				else
    				{
    					player.sendMessage(ChatColor.RED + "You aren't holding the correct type of item.");
    				}
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