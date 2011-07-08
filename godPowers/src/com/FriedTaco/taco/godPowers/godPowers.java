package com.FriedTaco.taco.godPowers;



	import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.util.logging.Level;
	import java.util.logging.Logger;
	import java.util.ArrayList;
	import java.util.HashMap;
	import org.bukkit.Location;
	import org.bukkit.World;
	import org.bukkit.entity.Arrow;
	import org.bukkit.entity.Entity;
	import org.bukkit.entity.Player;
	import org.bukkit.event.Event.Priority;
	import org.bukkit.event.Event;
	import org.bukkit.event.player.PlayerLoginEvent;
	import org.bukkit.inventory.ItemStack;
	import org.bukkit.plugin.PluginDescriptionFile;
	import org.bukkit.plugin.java.JavaPlugin;
	import org.bukkit.plugin.PluginManager;
	import com.nijiko.permissions.PermissionHandler;
	import com.nijikokun.bukkit.Permissions.Permissions;
	import org.bukkit.plugin.Plugin;
	import org.bukkit.util.Vector;




	public class godPowers extends JavaPlugin {
		private Logger log;
		int x, y, z, oldX, oldY, oldZ, temp;
		static String title = "";
	    private final godPowersPlayerListener playerListener  = new godPowersPlayerListener(this);
	    private final godPowersEntityListener entityListener = new godPowersEntityListener(this);
	    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();  
		public static ArrayList<String> godmodeEnabled = new ArrayList<String>();
		public static ArrayList<String> isJesus = new ArrayList<String>();
		public static ArrayList<String> isInferno = new ArrayList<String>();
		public static ArrayList<String> superJumper = new ArrayList<String>();
		public static ArrayList<String> arrowKill = new ArrayList<String>();
		public static ArrayList<String> burn = new ArrayList<String>();
		public static ArrayList<String> gaia = new ArrayList<String>();
		public static ArrayList<String> isZeus = new ArrayList<String>();
		public static ArrayList<String> isVulcan = new ArrayList<String>();
		public static ArrayList<String> DemiGod = new ArrayList<String>();
		public static double DemiModifier = 0;
		public static boolean godModeOnLogin = true;
		public static PermissionHandler Permissions;

	   
		 private void setupPermissions() {
		      Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");

		      if (godPowers.Permissions == null) 
		      {
		          if (test != null) {
		              godPowers.Permissions = ((Permissions)test).getHandler();
		              System.out.println("[GodPowers] Permissions detected. Now using permissions.");
		          } else {
		             System.out.println("[GodPowers] Permissions NOT detected. Giving permission to ops.");
		          }
		      }
		  }
		 
	    public void loadSettings() {
	    	final String dir = "plugins";
	        if (!new File(dir + File.separator + "godPowers.properties").exists()) {
	            FileWriter writer = null;
	            try {
	                writer = new FileWriter(dir + File.separator + "godPowers.properties");
	                writer.write("God Powers v 2.2 configuration\r\n");
	                writer.write("#This is a prefix for your name when you have godMode activated. Leave it blank if you don't want one.\r\n");
	                writer.write("godModeTitle=[God] \r\n");
	                writer.write("#If set to false, nobody will be able to have godMode activated on login.\r\n");
	                writer.write("godModeOnLogin=true\r\n");
	                writer.write("#Determines what percentage of damage will be taken by players using DemiGod.\r\n");
	                writer.write("#A setting of one will mean that they will take as much damage as usual, zero means no damage.\r\n");
	                writer.write("DemiGodDamage=0.2\r\n");
	                
	                } catch (Exception e) {
	                log.log(Level.SEVERE,
	                        "Exception while creating godPowers.properties", e);
	                try {
	                    if (writer != null)
	                        writer.close();
	                } catch (IOException ex) {
	                    log
	                            .log(
	                                    Level.SEVERE,
	                                    "Exception while closing writer for godPowers.properties",
	                                    ex);
	                }
	            } finally {
	                try {
	                    if (writer != null)
	                        writer.close();
	                } catch (IOException e) {
	                    log
	                            .log(
	                                    Level.SEVERE,
	                                    "Exception while closing writer for godPowers.properties",
	                                    e);
	                }
	            }
	        }
	        
	        PropertiesFile properties = new PropertiesFile(dir + File.separator + "godPowers.properties");
	        try {
	          title = properties.getString("godModeTitle", "");
	          godModeOnLogin = properties.getBoolean("godModeOnLogin", true);
	          DemiModifier = properties.getDouble("DemiGodDamage", 0.2);
	        } catch (Exception e) {
	            log.log(Level.SEVERE,
	                    "Exception while reading from godPowers.properties", e);
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
	    	} catch(Exception e) {
	    		System.out.println(error + "zeus.");
	    	}try{
	    		getCommand("godmode").setExecutor(new godModeCommand(this));
	    		System.out.println(message + "godmode.");
	    	} catch(Exception e) {
	    		System.out.println(error + "godmode.");
	    	}
	    	getCommand("godmodeon").setExecutor(new godModeCommand(this));
	    	getCommand("godmodeoff").setExecutor(new godModeCommand(this));
	    	try{
	    		getCommand("jesus").setExecutor(new JesusCommand(this));
	    		System.out.println(message + "jesus.");
	    	} catch(Exception e) {
	    		System.out.println(error + "jesus.");
	    	}try{
	    		getCommand("die").setExecutor(new DieCommand(this));
	    		System.out.println(message + "die.");
	    	} catch(Exception e) {
	    		System.out.println(error + "zeus.");
	    	}try{
	    		getCommand("slay").setExecutor(new SlayCommand(this));
	    		System.out.println(message + "slay.");
	    	} catch(Exception e) {
	    		System.out.println(error + "slay.");
	    		try{
	    			getCommand("smite").setExecutor(new SlayCommand(this));
	    			System.out.println(message + "smite in place of slay.");
	    		}catch(Exception e1){
	    			System.out.println(error + "smite in place of slay.");
	    		}
	    	}try{
	    		getCommand("maim").setExecutor(new MaimCommand(this));
	    		System.out.println(message + "maim.");
	    	} catch(Exception e) {
	    		System.out.println(error + "maim.");
	    	}try{
	    		getCommand("inferno").setExecutor(new InfernoCommand(this));
		    	System.out.println(message + "inferno.");
	    	} catch(Exception e) {
	    		System.out.println(error + "inferno.");
	    	}try{
		    	getCommand("superjump").setExecutor(new SuperJumpCommand(this));
		    	System.out.println(message + "superjump.");
	    	} catch(Exception e) {
	    		System.out.println(error + "superjump.");
	    	}try{
		    	getCommand("gaia").setExecutor(new GaiaCommand(this));
		    	System.out.println(message + "gaia.");
	    	} catch(Exception e) {
	    		System.out.println(error + "gaia.");
	    	}try{
		    	getCommand("heal").setExecutor(new HealCommand(this));
		    	System.out.println(message + "heal.");
	    	} catch(Exception e) {
	    		System.out.println(error + "heal.");
	    	}try{
		    	getCommand("godpowers").setExecutor(new commands(this));
		    	System.out.println(message + "godpowers.");
	    	} catch(Exception e) {
	    		System.out.println(error + "godpowers. How dare they!");
	    	}try{
		    	getCommand("vulcan").setExecutor(new VulcanCommand(this));
		    	System.out.println(message + "vulcan.");
	    	} catch(Exception e) {
	    		System.out.println(error + "vulcan.");
	    	}try{
		    	getCommand("myballsareonfire").setExecutor(new VulcanCommand(this));
		    	System.out.println(message + "vulcan.");
	    	} catch(Exception e) {
	    		System.out.println(error + "vulcan.");
	    	}try{
		    	getCommand("demigod").setExecutor(new DemiGodCommand(this));
		    	System.out.println(message + "demigod.");
	    	} catch(Exception e) {
	    		System.out.println(error + "demigod.");
	    	}
	    	//getCommand("heal").setExecutor(new HealCommand(this));
	    	loadSettings();
	        PluginManager pm = getServer().getPluginManager();
	        pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Priority.Normal, this);
	        pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Priority.Normal, this);
	        pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Priority.Normal, this);
	        pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Priority.Highest, this);
	        pm.registerEvent(Event.Type.PLAYER_ANIMATION, playerListener, Priority.Highest, this);
	        PluginDescriptionFile pdfFile = this.getDescription();
	        System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
	        setupPermissions();
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

		public void setGodmodeEnabled(ArrayList<String> godmodeEnabled) {
			godPowers.godmodeEnabled = godmodeEnabled;
		}

		public ArrayList<String> getGodmodeEnabled() {
			return godmodeEnabled;
		}
		static void dropDeadItems(Player player)
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
			// TODO Auto-generated method stub
			
		}
		public static void arrowSlay(Location arrows, World world, Player player)
	    {
					arrows = new Location(world, player.getLocation().getX()+2, player.getLocation().getY()+1, player.getLocation().getZ()+2);
					Arrow arrow = world.spawnArrow(arrows, new Vector(5, 1, 5), 2.0f, 4.0f);
					arrow.setFireTicks(100);
					arrow.teleport((Entity) player);
	    }
	}




