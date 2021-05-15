package com.elbert.zombie;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

import org.bukkit.scoreboard.Team;
import org.bukkit.entity.Player;
import java.util.List;
import org.bukkit.command.CommandExecutor;

public class setting implements CommandExecutor{
    static List<String> zombie_list = hoin.zombie_list;
    static List<String> human_list = hoin.human_list;
    static List<String> superzombie_list = hoin.superzombie_list;
    static Team zombie = hoin.board.registerNewTeam("zombie");
    static Team superzom = hoin.board.registerNewTeam("super");
    static HashMap<Player, Integer> Deathcount = new HashMap<Player, Integer>();
    
    @SuppressWarnings("deprecation")
	public boolean onCommand( CommandSender sender, Command cmd,String label,String[] args) {
        if (cmd.getName().equalsIgnoreCase("one")) {
        	zombie.setColor(ChatColor.RED);
            zombie.setAllowFriendlyFire(false);
            String on = args[0];
            Player effect = Bukkit.getPlayer(on);
            superzombie_list = EventListener.superzombie_list;
            human_list = EventListener.human_list;
            zombie_list = EventListener.zombie_list; 
            if (args[1].equals("human")) {
            	superzom.removePlayer(effect);
                human_list.remove(effect.getName());
                zombie_list.remove(effect.getName());      
                superzombie_list.remove(effect.getName());
                zombie.removePlayer(effect);          
                human_list.add(effect.getName()); 
                sender.sendMessage(String.valueOf(effect.getName()) + " = " + "human");  
                hoin.disting(zombie_list, human_list, superzombie_list);
            }
            else if (args[1].equals("zombie")) {
                Bukkit.getPlayer(on).setPlayerListName(ChatColor.RED + effect.getName());
            	superzom.removePlayer(effect);
                zombie_list.remove(effect.getName());
                human_list.remove(effect.getName());
                superzombie_list.remove(effect.getName());
                zombie_list.add(effect.getName());
                zombie.addPlayer(effect);
                sender.sendMessage(String.valueOf(effect.getName()) + " = " + "zombie");
                hoin.disting(zombie_list, human_list, superzombie_list);
            }
            else if (args[1].equals("vaccine")) {
                ItemStack item = new ItemStack(Material.BOW, 1);
                item.setDurability((short)(item.getType().getMaxDurability() - 2));
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("vaccine");
                meta.addEnchant(Enchantment.LUCK, 3, false);
                item.setItemMeta(meta);
                Bukkit.getPlayer(on).getInventory().addItem(item);
            }
            else if (args[1].equals("superzombie")) {
                Deathcount.put(Bukkit.getPlayer(on), 2);
                superzom.addPlayer(effect);
                zombie.removePlayer(effect);
                zombie_list.remove(effect.getName());
                human_list.remove(effect.getName());
                superzombie_list.remove(effect.getName());
                superzombie_list.add(effect.getName());
                sender.sendMessage(String.valueOf(effect.getName()) + " = " + "super zombie");
                hoin.disting(zombie_list, human_list, superzombie_list);
            }
        }
        
        sender.sendMessage(Integer.toString(human_list.size()) + Integer.toString(zombie_list.size()));
        return false;
    }
}
