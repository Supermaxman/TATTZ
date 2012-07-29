package me.supermaxman.tattz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class TATTZ extends JavaPlugin implements Listener{
	
	//Required
	public static Map<String, Location> rloc = new HashMap<String, Location>();
	public static Map<String, Location> lloc = new HashMap<String, Location>();
	
	public static TATTZ plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	@Override
	public void onDisable() {this.logger.info("TATTZ Disabled, no more threads...");}
	@Override
	public void onEnable() {
        getServer().getPluginManager().registerEvents(new TATTZ(), this);
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled, Thread All The ThingZ!");
		
	}
	
	
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event){
		if (event.isCancelled()){
			return;
		}
		if (event.getEntity() instanceof TNTPrimed){
			event.setCancelled(true);
		List<Block> blocks = event.blockList();
		   Thread thread =new ExplosionThread(blocks, event.getEntity().getLocation().getBlock());
           thread.start();
		}
	}
	
	
	
	
	
	
	
	
	
	
}