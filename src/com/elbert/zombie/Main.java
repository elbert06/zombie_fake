
package com.elbert.zombie;

import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
public class Main extends JavaPlugin{
    public void onEnable() {
        vaccine3.init();
        getCommand("one").setExecutor(new setting());
        getCommand("diamond").setExecutor(new diamond());
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        for(World s : Bukkit.getWorlds()) {
        	s.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        	s.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        	s.setGameRule(GameRule.KEEP_INVENTORY, false);
        }
        
    }

	public void onDisable() {
    	for (Player m : Bukkit.getOnlinePlayers()) {
    		m.setPlayerListName(ChatColor.WHITE + m.getName());
            m.removePotionEffect(PotionEffectType.GLOWING);
            m.removePotionEffect(PotionEffectType.SATURATION);
            m.removePotionEffect(PotionEffectType.NIGHT_VISION);
            
        }
    	setting.superzom.unregister();
    	setting.zombie.unregister();
    	hoin.board.getTeams().clear();
    	
    }
}
