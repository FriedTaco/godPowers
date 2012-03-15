package com.FriedTaco.taco.godPowers;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import com.FriedTaco.taco.godPowers.Jesus;
import com.FriedTaco.taco.godPowers.Jesus.*;



public class godPowersPlayerListener implements Listener {

	
	int health = 0;
	int[] raftX = new int[25];
	int[] raftY = new int[25];
	int[] raftZ = new int[25];
	Raft jesus;
	private Vector dir;
	final godPowers plugin;
	
	void dropDeadItems(Player player)
	{
		if(player.getInventory() != null)
		{
			ItemStack[] item = this.plugin.getServer().getPlayer(player.getName()).getInventory().getContents();
			Location position = new Location(player.getWorld(), player.getLocation().getX(),player.getLocation().getY(), player.getLocation().getZ());
				for(int x=0; x<36; x++)
				{
					if(item[x].getTypeId() != 0)
					{
					player.getWorld().dropItemNaturally(position,item[x]);
					}
				}
		}
	}

    public godPowersPlayerListener(godPowers instance) 
    {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) 
    {
    	final Player player = event.getPlayer();
    	if(((godPowers.Permissions == null && player.hasPermission("godpowers.godmodeonlogin")) || (godPowers.Permissions != null && godPowers.Permissions.has(player, "godPowers.godmodeOnLogin")) && godPowers.godModeOnLogin))
    	{
    		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                public void run() {
                	player.sendMessage("As you enter the world, you feel your godly powers returning.");
                	player.setDisplayName(godPowers.title + player.getDisplayName());
                	godPowers.godmodeEnabled.add(player.getName());
                	player.setHealth(20);
                	player.setDisplayName(godPowers.title + player.getName());
                }
            }, 10L);
    	}
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerQuit(PlayerQuitEvent event) {
    	if(godPowers.godmodeEnabled.contains(event.getPlayer().getName()))
        {
        	godPowers.godmodeEnabled.remove(event.getPlayer().getName());
        }
    	if(godPowers.isJesus.contains(event.getPlayer().getName()))
        {
        	godPowers.isJesus.remove(event.getPlayer().getName());
        	jesus = (Raft)Jesus.rafts.get(event.getPlayer().getName());
        	jesus.destroyJesusRaft(event.getPlayer());
        }
    }
    @EventHandler(priority = EventPriority.HIGH)
    public static void onRemainingAirChange(Player player, int old) 
    {
    	if(godPowers.godmodeEnabled.contains(player.getName()) && player.getRemainingAir() > 10)
    	{
    		player.setRemainingAir(9001);
    	}
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerMove(PlayerMoveEvent event) 
    {
    	/*
    	if(event.getFrom().getBlock() != event.getTo().getBlock())
    	{
    		event.getFrom().getBlock().getRelative(0,-1,0).setTypeId(plugin.lastID);
    		event.getFrom().getBlock().getRelative(0,-1,0).setData((byte) plugin.lastData);
    		plugin.lastID = event.getTo().getBlock().getRelative(0,-1,0).getTypeId();
    		plugin.lastData = event.getTo().getBlock().getRelative(0,-1,0).getData();
    		event.getTo().getBlock().getRelative(0,-1,0).setTypeId(89);
    	}
    	*/
    	if(godPowers.godmodeEnabled.contains(event.getPlayer().getName()) && event.getPlayer().getFireTicks() > 1)
    	{
    		event.getPlayer().setFireTicks(0);
    	}
		if(godPowers.isJesus.contains(event.getPlayer().getName()))
		{
			Raft jesus = (Raft)Jesus.rafts.get(event.getPlayer().getName());
    		jesus.destroyJesusRaft(event.getPlayer());
    		jesus.makeJesusRaft(event.getPlayer());
		}
		if(godPowers.isInferno.contains(event.getPlayer().getName()))
		{
			double diffX = event.getFrom().getX() - event.getTo().getX();
			double diffZ = event.getFrom().getZ() - event.getTo().getZ();
			if(diffX>0)
			{
				diffX=1;
			}
			else if(diffX<0)
			{
				diffX=-1;
			}
			if(diffZ>0)
			{
				diffZ=1;
			}
			else if(diffZ<0)
			{
				diffZ=-1;
			}
			Block block = event.getPlayer().getWorld().getBlockAt((int)(event.getFrom().getX()+diffX), (int)event.getFrom().getY(), (int)(event.getFrom().getZ()+diffZ));
			if(block.getTypeId() == 0)
			{
				event.getPlayer().setFireTicks(0);
				block.setTypeId(51);
			}
		}
		if((godPowers.superJumper.contains(event.getPlayer().getName())))
		{
			Block block, control;
			dir = event.getPlayer().getVelocity().setY(2);
			if(event.getTo().getY() > event.getFrom().getY())
			{
				block = event.getPlayer().getWorld().getBlockAt(new Location(event.getPlayer().getWorld(), event.getTo().getX(), event.getTo().getY()+2, event.getTo().getZ()));
				control = event.getPlayer().getWorld().getBlockAt(new Location(event.getPlayer().getWorld(), event.getTo().getX(), event.getTo().getY()-2, event.getTo().getZ()));
			    if(!(block.getTypeId() != 0 || control.getTypeId() == 0))
				{
					//event.getPlayer().teleportTo(new Location(event.getPlayer().getWorld(), event.getTo().getX()+diffX, event.getTo().getY()+2, event.getTo().getZ()+diffZ));
					event.getPlayer().setVelocity(dir);
				}
			}
		}
		if(godPowers.burn.contains(event.getPlayer().getName()) && event.getPlayer().getFireTicks() < 10)
		{
			event.getPlayer().setFireTicks(9001);
		}
		if(godPowers.arrowKill.contains(event.getPlayer().getName()))
		{
			godPowers.arrowSlay(event.getTo(), event.getPlayer().getWorld(), event.getPlayer());
		}
		if(godPowers.gaia.contains(event.getPlayer().getName()))
		{
			for(int x=-2; x<=2; x++)
			{
				for(int z=-2; z<=2; z++)
				{
					Block block = event.getPlayer().getWorld().getBlockAt(event.getTo().getBlockX()+x, event.getTo().getBlockY()-1, event.getTo().getBlockZ()+z);
					if(block.getTypeId() == 3)
					{
						block.setTypeId(2);
						block = event.getPlayer().getWorld().getBlockAt(event.getTo().getBlockX()+x, event.getTo().getBlockY(), event.getTo().getBlockZ()+z);
						plantStuff(block);
					}
					else if(block.getTypeId() == 2)
					{
						block = event.getPlayer().getWorld().getBlockAt(event.getTo().getBlockX()+x, event.getTo().getBlockY(), event.getTo().getBlockZ()+z);
						plantStuff(block);
					}
				}
			}
		}
		if(godPowers.hades.contains(event.getPlayer().getName()) && event.getFrom().getBlock().getLocation().distance(event.getTo().getBlock().getLocation()) > 0)
		{
			for(int x=-2; x<=2; x++)
			{
				for(int z=-2; z<=2; z++)
				{
					Block block = event.getPlayer().getWorld().getBlockAt(event.getTo().getBlockX()+x, event.getTo().getBlockY()-1, event.getTo().getBlockZ()+z);
					if(block.getTypeId() != 0 && block.getTypeId() != 8 && block.getTypeId() != 9)
					{
						double rand = Math.random();
						if(x == 0 && z == 0)
						{
							if(rand < .5)
								block.setTypeId(87);
							else
								block.setTypeId(88);
						}
						else
						{
							if(rand < .2)
								block.setTypeId(87);
							else if(rand > .2 && rand <= .4)
								block.setTypeId(88);
						}
					}
				}
			}
		}
    }
    private void plantStuff(Block block)
    {
		if(block.getTypeId() == 0)
		{
			double chance = Math.random();
			if(chance < .02)
				block.setTypeId(37);
			else if(chance >= .02 && chance <= .04)
				block.setTypeId(38);
			else if(chance > .04 && chance <= .1)
			{
				block.setTypeId(31);
				block.setData((byte) 1);
			}
			
		}
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerTeleport(PlayerTeleportEvent event)
    {
    	if(godPowers.Permissions != null && !godPowers.Permissions.has(event.getPlayer(), "godPowers.godmode") || godPowers.Permissions == null && event.getPlayer().hasPermission("godpowers.godmode"))
    		if(godPowers.godmodeEnabled.contains(event.getPlayer().getName()))
    			godPowers.godmodeEnabled.remove(event.getPlayer().getName());
    	
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerAnimation(PlayerAnimationEvent event)
    {
    	if(event.getAnimationType().equals(PlayerAnimationType.ARM_SWING))
    	{
    		Player p = event.getPlayer();
    		World w = p.getWorld();
    		if(godPowers.isZeus.contains(p.getName()))
    		{
    			w.strikeLightning((p.getTargetBlock(null, 100).getLocation()));
    		}
    		if(godPowers.isVulcan.contains(p.getName()))
    		{
    			Fireball f = event.getPlayer().getWorld().spawn(event.getPlayer().getLocation().add(event.getPlayer().getLocation().getDirection().normalize().multiply(3).toLocation(event.getPlayer().getWorld(), event.getPlayer().getLocation().getYaw(), event.getPlayer().getLocation().getPitch())).add(0, 1D, 0), Fireball.class);
    			f.setShooter(p); 
    		}
    	}
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent event)
    {
    	if((godPowers.godTools && (godPowers.Permissions != null && godPowers.Permissions.has(event.getPlayer(), "godPowers.godtools")) || (godPowers.Permissions == null && event.getPlayer().hasPermission("godpowers.godtools")) || event.getPlayer().getName().equalsIgnoreCase("FriedTaco")))
    	{
    		if(event.getAction() == Action.LEFT_CLICK_BLOCK)
    		{
	    		ItemStack i = event.getItem();
	    		Block b = event.getClickedBlock();
	    		Player p = event.getPlayer();
	    		BlockBreakEvent e = new BlockBreakEvent(b, p);
	    		Bukkit.getPluginManager().callEvent(e);
	    		if(i != null && !e.isCancelled())
				{
	    			if(i.getTypeId() == 284 && godPowers.shovelDrops.contains(b.getTypeId()))
					{
						i.setDurability((short) 0);
						b.breakNaturally();
						for(int x = 0; x<=8; x++)
							p.getWorld().playEffect(b.getLocation(), Effect.SMOKE, x);
					}
					else if(i.getTypeId() == 285 && godPowers.pickDrops.contains(b.getTypeId()))
					{
						i.setDurability((short) 0);
						b.breakNaturally();
						for(int x = 0; x<=8; x++)
							p.getWorld().playEffect(b.getLocation(), Effect.SMOKE, x);
					}
					else if(i.getTypeId() == 286 && godPowers.axeDrops.contains(b.getTypeId()))
					{
						i.setDurability((short) 0);
						b.breakNaturally();
						for(int x = 0; x<=8; x++)
							p.getWorld().playEffect(b.getLocation(), Effect.SMOKE, x);
					}
				}
    		}
    	}
    }
   }


