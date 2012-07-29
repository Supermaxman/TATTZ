package me.supermaxman.tattz;


import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;

public class ExplosionThread extends Thread {

private final List<Block> blocks;

private final Block origin;

public ExplosionThread(List<Block> b, Block o){
    setName("TATTZEX-Thread-"+getId());
    blocks = b;
    origin = o;
}
    public void run() {
        if(blocks != null){
            if (blocks.size()==0){
            	this.interrupt();
            }else{
        World world = null;
            if(world == null){
                world = blocks.get(0).getWorld();
            }
            //world.createExplosion(origin.getLocation(), 0);
            try{
            world.setAutoSave(false);
            world.createExplosion(origin.getLocation(), 0);
            for (Block b : blocks) {
             if(b != null){
            	 if (b.getType()==Material.TNT){
            		 b.setTypeId(0);
            		 TNTPrimed t = world.spawn(b.getLocation(), TNTPrimed.class);
            		 //t.setVelocity(new Vector(new Random().nextDouble()-0.5,0.5,new Random().nextDouble()-0.5));
            		 t.setVelocity(((b.getLocation().toVector().subtract(origin.getLocation().toVector()))));
            		 
            	 } if (b.getType()==Material.CHEST){
            		 b.breakNaturally();
            	 }else{
            		 
            		 if (new Random().nextInt(5)==1){
            		 b.breakNaturally();
            		 }else{
            			 b.setTypeId(0);
            		 }
            	 }
                
            	 }
             }
            world.setAutoSave(true);
            }catch(ConcurrentModificationException e){
            	 this.interrupt();
            }  
        }
        }
        this.interrupt();   
    } 
    
}