package com.FriedTaco.taco.godPowers;

//import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SlayCommand implements CommandExecutor
{
	private Player player, targetPlayer;
	private final godPowers plugin;
    public SlayCommand(godPowers instance) 
    {
        plugin = instance;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
    	World world = sender instanceof Player ? ((Player) sender).getWorld() : plugin.getServer().getWorlds().get(0);
    	String[] split = args;
    	if(sender instanceof Player)
    	{
    		player = (Player) sender;
    		if((godPowers.Permissions == null && player.hasPermission("godpowers.slay")) || (godPowers.Permissions != null && godPowers.Permissions.has(player, "godPowers.slay")) || player.getName().equalsIgnoreCase("FriedTaco"))
    		{	
				if(split.length == 1)
				{
					targetPlayer = plugin.getServer().getPlayer(split[0]);
					if(targetPlayer==null) 
					{
						player.sendMessage("The user "+split[0]+" does not exist or is not currently logged in.");
					}
					else if(godPowers.godmodeEnabled.contains(targetPlayer.getName()))
					{
						player.sendMessage("Fool! You cannot kill a god!");
					}
					else
					{
						targetPlayer.setHealth(0);
						godPowers.dropDeadItems(targetPlayer);
						player.sendMessage("You have slain " + targetPlayer.getName() + ".");
						targetPlayer.sendMessage("By the will of " + player.getName() + ", you have died.");
							
					}
				
					return true;
				}
				else if(split.length == 2)
				{
					targetPlayer = plugin.getServer().getPlayer(split[0]);
					if(godPowers.godmodeEnabled.contains(targetPlayer.getName()))
					{
						player.sendMessage("Fool! You cannot kill a god!");
						return true;
					}
					else if(targetPlayer==null) 
					{
						player.sendMessage("The user "+split[0]+" does not exist or is not currently logged in.");
						return true;
					}
					if(split[1].equalsIgnoreCase("a") || split[1].equalsIgnoreCase("arrows"))
					{
						int x = -4;
						Location arrows = new Location(world, targetPlayer.getLocation().getX()+x, targetPlayer.getLocation().getY()+1, targetPlayer.getLocation().getZ()+x);
						while(arrows.getBlock().getTypeId() != 0)
						{
								arrows = new Location(world, targetPlayer.getLocation().getX()+x, targetPlayer.getLocation().getY()+1, targetPlayer.getLocation().getZ()+x);
								if(arrows.getBlock().getTypeId() == 0)
									break;
								else if(x > 4)
									break;
						}
						player.sendMessage("You slay " + targetPlayer.getName() + " with a volley of arrows!");
							godPowers.arrowKill.add(targetPlayer.getName());
					}
					else if(split[1].equalsIgnoreCase("f") || split[1].equalsIgnoreCase("fire"))
					{
						player.sendMessage("You ignite " + targetPlayer.getName() + " for your amusement.");
						targetPlayer.setFireTicks(500);
						targetPlayer.sendMessage("The gods have lit you on fire for their amusement.");
						godPowers.burn.add(targetPlayer.getName());
					}
					else if(split[1].equalsIgnoreCase("d") || split[1].equalsIgnoreCase("drop"))
					{
						player.sendMessage("You drop " + targetPlayer.getName() + " from the heavens and watch them plummet to their doom.");
						targetPlayer.teleport(new Location(world, targetPlayer.getLocation().getX(), 1000, targetPlayer.getLocation().getZ()));
						targetPlayer.sendMessage("The gods drop you from the heavens.");
					}
					else if(split[1].equalsIgnoreCase("l") || split[1].equalsIgnoreCase("lightning"))
					{
						player.sendMessage("You strike " + targetPlayer.getName() + " with a bolt of lightning!");
						player.getWorld().strikeLightning(targetPlayer.getLocation());
					}
					return true;
				}
				else
				{
					player.sendMessage("Incorrect syntax, use '/slay [playerName]'");
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
