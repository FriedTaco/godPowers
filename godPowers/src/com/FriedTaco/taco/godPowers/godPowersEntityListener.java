package com.FriedTaco.taco.godPowers;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;


public class godPowersEntityListener implements Listener
{
	@SuppressWarnings("unused")
	private final godPowers plugin;
	
    public godPowersEntityListener(godPowers instance) {
        plugin = instance;
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onFoodLevelChange(FoodLevelChangeEvent event)
    {
    	if(event.getEntity() instanceof Player && godPowers.godmodeEnabled.contains(((Player) event.getEntity()).getName()))
    		event.setCancelled(true);
    }
    @EventHandler(priority = EventPriority.HIGH)
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
			else if(godPowers.burn.contains(player.getName()) && player.getHealth() <= event.getDamage())
			{
				godPowers.burn.remove(player.getName());
				player.setFireTicks(0);
			}
			else if(godPowers.arrowKill.contains(player.getName()) && player.getHealth() <= event.getDamage())
			{
				godPowers.arrowKill.remove(player.getName());
			}
			else if(godPowers.DemiGod.contains(player.getName()))
			{
				event.setDamage((int) (event.getDamage() * godPowers.DemiModifier));
			}
		}
    	if(event instanceof EntityDamageByEntityEvent)
		{
			EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
			if(e.getDamager() instanceof Player)
			{
				Player p = (Player) e.getDamager();
				ItemStack i = p.getItemInHand();
				if(i.containsEnchantment(Enchantment.KNOCKBACK) && i.getEnchantmentLevel(Enchantment.KNOCKBACK) == 10)
				{
					if(!(e.getEntity() instanceof Player))
					{
						Vector v = p.getEyeLocation().getDirection();
						v.setX(v.getX()*25);
						v.setZ(v.getZ()*25);
						v.setY(v.getY()*2);
						e.getEntity().setVelocity(v);
					}
				}
			}
		}
    }

	
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return false;
	}
	public void setCancelled(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
	
}