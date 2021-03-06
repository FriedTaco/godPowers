package com.FriedTaco.taco.godPowers;



	import java.io.File;
	import java.util.logging.Logger;
	import java.util.ArrayList;
	import java.util.HashMap;
	import org.bukkit.Location;
	import org.bukkit.World;
	import org.bukkit.configuration.file.FileConfiguration;
	import org.bukkit.enchantments.Enchantment;
	import org.bukkit.entity.Arrow;
	import org.bukkit.entity.Entity;
	import org.bukkit.entity.Player;
	import org.bukkit.event.player.PlayerLoginEvent;
	import org.bukkit.inventory.ItemStack;
	import org.bukkit.plugin.PluginDescriptionFile;
	import org.bukkit.plugin.java.JavaPlugin;
	import org.bukkit.plugin.PluginManager;
	import org.bukkit.util.Vector;




	public class godPowers extends JavaPlugin {
		@SuppressWarnings("unused")
		private Logger log;
		public String title = "";
	    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>(); 
	    public HashMap<String,Integer> curse = new HashMap<String,Integer>();
		public ArrayList<String> godmodeEnabled = new ArrayList<String>();
		public ArrayList<String> isJesus = new ArrayList<String>();
		public ArrayList<String> isInferno = new ArrayList<String>();
		public ArrayList<String> superJumper = new ArrayList<String>();
		public ArrayList<String> arrowKill = new ArrayList<String>();
		public ArrayList<String> burn = new ArrayList<String>();
		public ArrayList<String> gaia = new ArrayList<String>();
		public ArrayList<String> isZeus = new ArrayList<String>();
		public ArrayList<String> isVulcan = new ArrayList<String>();
		public ArrayList<String> DemiGod = new ArrayList<String>();
		public ArrayList<String> hades = new ArrayList<String>();
		public ArrayList<Integer> shovelDrops = new ArrayList<Integer>();
		public ArrayList<Integer> pickDrops = new ArrayList<Integer>();
		public ArrayList<Integer> axeDrops = new ArrayList<Integer>();
		public HashMap<String,String> list = new HashMap<String,String>();
		public double DemiModifier = 0;
		public boolean godModeOnLogin = true;
		public boolean godTools = true;
		protected static FileConfiguration Config;
		
		 public void loadConfig(){
			 try{
				File godPowers = new File("plugins" + File.separator + "godPowers" + File.separator + "config.yml");
				godPowers.mkdir();
	            Config = getConfig();
	            if(!Config.contains("general.God_Mode_Title"))
	            	Config.set("general.God_Mode_Title", "[God] ");
	            if(!Config.contains("general.God_Mode_On_Login"))
	            	Config.set("general.God_Mode_On_Login", true);
	            if(!Config.contains("general.DemiGod_Damage_Modifier"))
	            	Config.set("general.DemiGod_Damage_Modifier", 0.2);
	            if(!Config.contains("general.GodTools_Enabled"))
	            	Config.set("general.GodTools_Enabled", true);
	            title = Config.getString("God_Mode_Title", "");
	            godModeOnLogin = Config.getBoolean("God_Mode_On_Login", true);
	            DemiModifier= Config.getDouble("general.DemiGod_Damage_Modifier", 0.2);
	            godTools = Config.getBoolean("general.GodTools_Enabled",true);
	            
	            saveConfig();
			 } catch(Exception e){
				 
			 }
	        }
	    
	    public void onDisable() {
	    }
	    @Override
	    public void onEnable() {
	    	
	    	String message = "[GodPowers] Successfully registered command ";
	    	String error = "[GodPowers] ERROR another plugin has already taken the command ";
	    	try{
	    		getCommand("zeus").setExecutor(new ZeusCommand(this));
	    		System.out.println(message + "zeus.");
	    		list.put("zeus", "- Strike lightning with a swing of your arm!");
	    	} catch(Exception e) {
	    		System.out.println(error + "zeus.");
	    	}try{
	    		getCommand("godmode").setExecutor(new godModeCommand(this));
	    		System.out.println(message + "godmode.");
	    		list.put("godmode", "<Player> - Toggles godmode on and off.");
	    	} catch(Exception e) {
	    		System.out.println(error + "godmode.");
	    	}
	    	getCommand("godmodeon").setExecutor(new godModeCommand(this));
	    	getCommand("godmodeoff").setExecutor(new godModeCommand(this));
	    	try{
	    		getCommand("jesus").setExecutor(new JesusCommand(this));
	    		System.out.println(message + "jesus.");
	    		list.put("jesus", "<Player> - Allows you to walk on water and lava");
	    	} catch(Exception e) {
	    		System.out.println(error + "jesus.");
	    	}try{
	    		getCommand("die").setExecutor(new DieCommand(this));
	    		System.out.println(message + "die.");
	    		list.put("die", "- Causes you to die.");
	    	} catch(Exception e) {
	    		System.out.println(error + "die.");
	    	}try{
	    		getCommand("slay").setExecutor(new SlayCommand(this));
	    		System.out.println(message + "slay.");
	    		list.put("slay", "[Player] <arrows/fire/drop> - Kills a player with/without the optional method.");
	    	} catch(Exception e) {
	    		System.out.println(error + "slay.");
	    		try{
	    			getCommand("smite").setExecutor(new SlayCommand(this));
	    			System.out.println(message + "smite in place of slay.");
	    			list.put("smite", "[Player] <arrows/fire/drop> - Kills a player with/without the optional method.");
	    		}catch(Exception e1){
	    			System.out.println(error + "smite in place of slay.");
	    		}
	    	}try{
	    		getCommand("maim").setExecutor(new MaimCommand(this));
	    		System.out.println(message + "maim.");
	    		list.put("maim", "[Player] - Beat a player within an inch of their life!");
	    	} catch(Exception e) {
	    		System.out.println(error + "maim.");
	    	}try{
	    		getCommand("inferno").setExecutor(new InfernoCommand(this));
		    	System.out.println(message + "inferno.");
		    	list.put("inferno", "- Creates a trail of fire behind you.");
	    	} catch(Exception e) {
	    		System.out.println(error + "inferno.");
	    	}try{
		    	getCommand("superjump").setExecutor(new SuperJumpCommand(this));
		    	System.out.println(message + "superjump.");
		    	list.put("superjump", "- Be able to leap tall building in a single bound!");
	    	} catch(Exception e) {
	    		System.out.println(error + "superjump.");
	    	}try{
		    	getCommand("gaia").setExecutor(new GaiaCommand(this));
		    	System.out.println(message + "gaia.");
		    	list.put("gaia", "- Sprouts grass and flowers wherever you step.");
	    	} catch(Exception e) {
	    		System.out.println(error + "gaia.");
	    	}try{
		    	getCommand("heal").setExecutor(new HealCommand(this));
		    	System.out.println(message + "heal.");
		    	list.put("heal", "<Player> - Heals either you or the specified player.");
	    	} catch(Exception e) {
	    		System.out.println(error + "heal.");
	    	}try{
		    	getCommand("godpowers").setExecutor(new commands(this));
		    	System.out.println(message + "godpowers.");
		    	list.put("godpowers", "- Displays this message.");
	    	} catch(Exception e) {
	    		System.out.println(error + "godpowers. How dare they!");
	    	}try{
		    	getCommand("vulcan").setExecutor(new VulcanCommand(this));
		    	System.out.println(message + "vulcan.");
		    	list.put("vulcan", "- Fling fireballs at those pesky mortals!");
	    	} catch(Exception e) {
	    		System.out.println(error + "vulcan.");
	    	}try{
		    	getCommand("myballsareonfire").setExecutor(new VulcanCommand(this));
		    	System.out.println(message + "vulcan.");
		    	list.put("myballsareonfire", "- See vulcan.");
	    	} catch(Exception e) {
	    		System.out.println(error + "vulcan.");
	    	}try{
		    	getCommand("demigod").setExecutor(new DemiGodCommand(this));
		    	System.out.println(message + "demigod.");
		    	list.put("demigod", "- Allows you to take a small fraction of the damage you'd normally take.");
	    	} catch(Exception e) {
	    		System.out.println(error + "demigod.");
	    	}try{
	    		getCommand("hades").setExecutor(new HadesCommand(this));
	    		System.out.println(message + "hades.");
	    		list.put("hades", "- Corrupt the world as you walk through it.");
	    	} catch(Exception e) {
	    		System.out.println(error + "hades.");
	    	}try{
	    		getCommand("bless").setExecutor(new BlessCommand(this));
    			System.out.println(message + "bless.");
    		list.put("bless", "- Enchant your equipment with the power of gods!");
	    	} catch(Exception e) {
	    		System.out.println(error + "bless.");
	    	}try{
	    		getCommand("fusrodah").setExecutor(new FusRoDAH(this));
    			System.out.println(message + "FusRoDah.");
    		list.put("FusRoDAH", "- Enchants item in hand with Knockback level 10!");
	    	} catch(Exception e) {
	    		System.out.println(error + "FusRoDAH.");
	    	}try{
	    		getCommand("plutus").setExecutor(new PlutusCommand(this));
				System.out.println(message + "plutus.");
			list.put("plutus", "- Enchants item in hand with Wealth level 10!");
	    	} catch(Exception e) {
	    		System.out.println(error + "plutus");
	    	}
	    	loadConfig();
	        PluginManager pm = getServer().getPluginManager();
	        pm.registerEvents(new godPowersEntityListener(this), this);
	        pm.registerEvents(new godPowersPlayerListener(this), this);
	        PluginDescriptionFile pdfFile = this.getDescription();
	        System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
	        populateLists();
	    }
	    public void populateLists()
	    {
	    	shovelDrops.add(2);
	    	shovelDrops.add(3);
	    	shovelDrops.add(12);
	    	shovelDrops.add(13);
	    	shovelDrops.add(82);
	    	pickDrops.add(1);
	    	pickDrops.add(4);
	    	pickDrops.add(14);
	    	pickDrops.add(15);
	    	pickDrops.add(16);
	    	pickDrops.add(21);
	    	pickDrops.add(22);
	    	pickDrops.add(24);
	    	pickDrops.add(41);
	    	pickDrops.add(42);
	    	pickDrops.add(43);
	    	pickDrops.add(44);
	    	pickDrops.add(45);
	    	pickDrops.add(48);
	    	pickDrops.add(49);
	    	pickDrops.add(56);
	    	pickDrops.add(57);
	    	axeDrops.add(5);
	    	axeDrops.add(17);
	    	
	    }
	    public boolean isDebugging(final Player player) {
	        if (debugees.containsKey(player)) {
	            return debugees.get(player);
	        } else {
	            return false;
	        }
	    }

	    public void setDebugging(final Player player, final boolean value) {
	        debugees.put(player, value);
	    }
		void dropDeadItems(Player player)
		{
			if(player.getInventory() != null)
			{
				ItemStack[] item = player.getInventory().getContents();
				Location position = new Location(player.getWorld(), player.getLocation().getX(),player.getLocation().getY(), player.getLocation().getZ());
					for(int x=0; x<item.length; x++)
					{
						if(item[x] != null && item[x].getTypeId() != 0)
						{
							player.getWorld().dropItemNaturally(position,item[x]);
						}
					}
			}
		}

		public void recordEvent(PlayerLoginEvent event) {
			
		}
		public void arrowSlay(Location arrows, World world, Player player)
	    {
					arrows = new Location(world, player.getLocation().getX()+2, player.getLocation().getY()+1, player.getLocation().getZ()+2);
					Arrow arrow = world.spawnArrow(arrows, new Vector(5, 1, 5), 2.0f, 4.0f);
					arrow.setFireTicks(100);
					arrow.teleport((Entity) player);
	    }
		public void bless(Player p)
		{
			for(ItemStack i : p.getInventory().getContents())
			{
				if(i!=null)
				{
					switch(i.getTypeId())
					{
					case 256:
						i.addEnchantment(Enchantment.DIG_SPEED, 5);
						i.addEnchantment(Enchantment.DURABILITY, 3);
						//i.addEnchantment(Enchantment.SILK_TOUCH, 1);
						i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
						break;
					case 257:
						i.addEnchantment(Enchantment.DIG_SPEED, 5);
						i.addEnchantment(Enchantment.DURABILITY, 3);
						//i.addEnchantment(Enchantment.SILK_TOUCH, 1);
						i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
						break;
					case 258:
						i.addEnchantment(Enchantment.DIG_SPEED, 5);
						i.addEnchantment(Enchantment.DURABILITY, 3);
						//i.addEnchantment(Enchantment.SILK_TOUCH, 1);
						i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
						break;
					case 261:
						i.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
						i.addEnchantment(Enchantment.ARROW_FIRE, 1);
						i.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
						i.addEnchantment(Enchantment.ARROW_INFINITE, 1);
						break;
					case 267:
						i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
						i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
						i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
						i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
						i.addEnchantment(Enchantment.KNOCKBACK, 2);
						i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
						break;
					case 268:
						i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
						i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
						i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
						i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
						i.addEnchantment(Enchantment.KNOCKBACK, 2);
						i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
						break;
					case 269:
						i.addEnchantment(Enchantment.DIG_SPEED, 5);
						i.addEnchantment(Enchantment.DURABILITY, 3);
						//i.addEnchantment(Enchantment.SILK_TOUCH, 1);
						i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
						break;
					case 270:
						i.addEnchantment(Enchantment.DIG_SPEED, 5);
						i.addEnchantment(Enchantment.DURABILITY, 3);
						//i.addEnchantment(Enchantment.SILK_TOUCH, 1);
						i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
						break;
					case 271:
						i.addEnchantment(Enchantment.DIG_SPEED, 5);
						i.addEnchantment(Enchantment.DURABILITY, 3);
						//i.addEnchantment(Enchantment.SILK_TOUCH, 1);
						i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
						break;
					case 272:
						i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
						i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
						i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
						i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
						i.addEnchantment(Enchantment.KNOCKBACK, 2);
						i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
						break;
					case 273:
						i.addEnchantment(Enchantment.DIG_SPEED, 5);
						i.addEnchantment(Enchantment.DURABILITY, 3);
						//i.addEnchantment(Enchantment.SILK_TOUCH, 1);
						i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
						break;
					case 274:
						i.addEnchantment(Enchantment.DIG_SPEED, 5);
						i.addEnchantment(Enchantment.DURABILITY, 3);
						i.addEnchantment(Enchantment.SILK_TOUCH, 1);
						i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
						break;
					case 275:
						i.addEnchantment(Enchantment.DIG_SPEED, 5);
						i.addEnchantment(Enchantment.DURABILITY, 3);
						//i.addEnchantment(Enchantment.SILK_TOUCH, 1);
						i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
						break;
					case 276:
						i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
						i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
						i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
						i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
						i.addEnchantment(Enchantment.KNOCKBACK, 2);
						i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
						break;
					case 277:
						i.addEnchantment(Enchantment.DIG_SPEED, 5);
						i.addEnchantment(Enchantment.DURABILITY, 3);
						//i.addEnchantment(Enchantment.SILK_TOUCH, 1);
						i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
						break;
					case 278:
						i.addEnchantment(Enchantment.DIG_SPEED, 5);
						i.addEnchantment(Enchantment.DURABILITY, 3);
						//i.addEnchantment(Enchantment.SILK_TOUCH, 1);
						i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
						break;
					case 279:
						i.addEnchantment(Enchantment.DIG_SPEED, 5);
						i.addEnchantment(Enchantment.DURABILITY, 3);
						//i.addEnchantment(Enchantment.SILK_TOUCH, 1);
						i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
						break;
					case 283:
						i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
						i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
						i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
						i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
						i.addEnchantment(Enchantment.KNOCKBACK, 2);
						i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
						break;
					case 284:
						i.addEnchantment(Enchantment.DIG_SPEED, 5);
						i.addEnchantment(Enchantment.DURABILITY, 3);
						//i.addEnchantment(Enchantment.SILK_TOUCH, 1);
						i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
						break;
					case 285:
						i.addEnchantment(Enchantment.DIG_SPEED, 5);
						i.addEnchantment(Enchantment.DURABILITY, 3);
						//i.addEnchantment(Enchantment.SILK_TOUCH, 1);
						i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
						break;
					case 286:
						i.addEnchantment(Enchantment.DIG_SPEED, 5);
						i.addEnchantment(Enchantment.DURABILITY, 3);
						//i.addEnchantment(Enchantment.SILK_TOUCH, 1);
						i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
						break;
					case 298:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						i.addEnchantment(Enchantment.OXYGEN, 3);
						i.addEnchantment(Enchantment.WATER_WORKER, 1);
						break;
					case 299:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						break;
					case 300:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						break;
					case 301:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
						break;
					case 302:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						i.addEnchantment(Enchantment.OXYGEN, 3);
						i.addEnchantment(Enchantment.WATER_WORKER, 1);
						break;
					case 303:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						break;
					case 304:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						break;
					case 305:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
						break;
					case 306:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						i.addEnchantment(Enchantment.OXYGEN, 3);
						i.addEnchantment(Enchantment.WATER_WORKER, 1);
						break;
					case 307:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						break;
					case 308:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						break;
					case 309:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
						break;
					case 310:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						i.addEnchantment(Enchantment.OXYGEN, 3);
						i.addEnchantment(Enchantment.WATER_WORKER, 1);
						break;
					case 311:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						break;
					case 312:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						break;
					case 313:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
						break;
					case 314:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						i.addEnchantment(Enchantment.OXYGEN, 3);
						i.addEnchantment(Enchantment.WATER_WORKER, 1);
						break;
					case 315:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						break;
					case 316:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						break;
					case 317:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
						break;
					}
				}
			}
			for(ItemStack i : p.getInventory().getArmorContents())
			{
				if(i!=null)
				{
					switch(i.getTypeId())
					{
					case 298:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						i.addEnchantment(Enchantment.OXYGEN, 3);
						i.addEnchantment(Enchantment.WATER_WORKER, 1);
						break;
					case 299:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						break;
					case 300:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						break;
					case 301:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
						break;
					case 302:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						i.addEnchantment(Enchantment.OXYGEN, 3);
						i.addEnchantment(Enchantment.WATER_WORKER, 1);
						break;
					case 303:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						break;
					case 304:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						break;
					case 305:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
						break;
					case 306:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						i.addEnchantment(Enchantment.OXYGEN, 3);
						i.addEnchantment(Enchantment.WATER_WORKER, 1);
						break;
					case 307:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						break;
					case 308:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						break;
					case 309:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
						break;
					case 310:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						i.addEnchantment(Enchantment.OXYGEN, 3);
						i.addEnchantment(Enchantment.WATER_WORKER, 1);
						break;
					case 311:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						break;
					case 312:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						break;
					case 313:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
						break;
					case 314:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						i.addEnchantment(Enchantment.OXYGEN, 3);
						i.addEnchantment(Enchantment.WATER_WORKER, 1);
						break;
					case 315:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						break;
					case 316:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						break;
					case 317:
						i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
						i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
						i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
						i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
						i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
						break;
					}
				}
			}
		}
	}




