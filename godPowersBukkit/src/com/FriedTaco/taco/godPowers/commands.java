package com.FriedTaco.taco.godPowers;

//import org.bukkit.World;
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
    		if((godPowers.Permissions == null && player.isOp()) || (godPowers.Permissions != null && godPowers.Permissions.has(player, "godPowers.commands")) || player.getName().equalsIgnoreCase("FriedTaco"))
    		{	
    			String[] avaliable = {"/die - Causes you to die.","/gaia - Sprouts grass and flowers wherever you step.","/godmode <Player> - Toggles godmode on and off.","/godmodeon or /godmodeoff - See '/godmode'","/heal <Player>","/inferno - Creates a trail of fire behind you.","/jesus <Player> - Allows you to walk on water and lava","/maim [Player] - Beat a player within an inch of their life!","/slay [Player] <arrows/fire/drop> - Kills a player with/without the optional method.","/superjump - Be able to leap tall buildings in a single bound!","/zeus - Strike lightning with a swing of your arm!","/vulcan - Fling fireballs at those pesky mortals!","/myballsareonfire - See '/vulcan'"};
				if(args.length == 0)
				{
					player.sendMessage("You can use the following commands: (< > = Optional [ ] = Required)");
					if(godPowers.Permissions == null && player.isOp())
						for(int i=0;i<avaliable.length;i++)
							player.sendMessage(avaliable[i]);
					else
						for(int i=0;i<avaliable.length;i++)
							switch (i)
							{
								case 0:
									if(godPowers.Permissions.has(player, "godPowers.die"))
										player.sendMessage(avaliable[0]);
									break;
								case 1:
									if(godPowers.Permissions.has(player, "godPowers.gaia"))
										player.sendMessage(avaliable[1]);
									break;
								case 2:
									if(godPowers.Permissions.has(player, "godPowers.godmode"))
										player.sendMessage(avaliable[2]);
									break;
								case 3:
									if(godPowers.Permissions.has(player, "godPowers.godmode"))
										player.sendMessage(avaliable[3]);
									break;
								case 4:
									if(godPowers.Permissions.has(player, "godPowers.heal"))
										player.sendMessage(avaliable[4]);
									break;
								case 5:
									if(godPowers.Permissions.has(player, "godPowers.inferno"))
										player.sendMessage(avaliable[5]);
									break;
								case 6:
									if(godPowers.Permissions.has(player, "godPowers.jesus"))
										player.sendMessage(avaliable[6]);
									break;
								case 7:
									if(godPowers.Permissions.has(player, "godPowers.maim"))
										player.sendMessage(avaliable[7]);
									break;
								case 8:
									if(godPowers.Permissions.has(player, "godPowers.slay"))
										player.sendMessage(avaliable[8]);
									break;
								case 9:
									if(godPowers.Permissions.has(player, "godPowers.superjump"))
										player.sendMessage(avaliable[9]);
									break;
								case 10:
									if(godPowers.Permissions.has(player, "godPowers.zeus"))
										player.sendMessage(avaliable[10]);
									break;
								case 11:
									if(godPowers.Permissions.has(player, "godPowers.vulcan"))
										player.sendMessage(avaliable[11]);
									break;
								case 12:
									if(godPowers.Permissions.has(player, "godPowers.vulcan"))
										player.sendMessage(avaliable[12]);
									break;
							}
							
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
