package com.FriedTaco.taco.godPowers;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.entity.*;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;


public class godPowersEntityListener extends EntityListener implements Cancellable
{
	@SuppressWarnings("unused")
	private final godPowers plugin;
	
    public godPowersEntityListener(godPowers instance) {
        plugin = instance;
    }
    public void onEntityDamage(EntityDamageEvent event)
    {
    	if(event.getEntity() instanceof Player)
		{
			Player player = (Player) event.getEntity();
			if(godPowers.godmodeEnabled.contains(player.getName()))
			{
				player.setHealth(20);
				event.setCancelled(true);
			}
			else if(godPowers.superJumper.contains(player.getName()) && event.getCause() == DamageCause.FALL)
			{
				event.setCancelled(true);
			}
			else if(godPowers.burn.contains(player.getName()) && player.getHealth() <= 1)
			{
				godPowers.burn.remove(player.getName());
				player.setHealth(0);
				player.setFireTicks(0);
			}
			else if(godPowers.arrowKill.contains(player.getName()) && player.getHealth() <= 1)
			{
				godPowers.arrowKill.remove(player.getName());
				player.setHealth(0);
			}
		}
    }

	
	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setCancelled(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
	
}