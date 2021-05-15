package com.elbert.zombie;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import java.util.List;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class hoin {
    public static void disting(List<String> zombie_list,List<String> human_list,List<String> superzombie_list) {
    	for(Player m : Bukkit.getOnlinePlayers()) {
    		if (human_list.contains(m.getName())) {
                m.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 99999, 0));
                m.removePotionEffect(PotionEffectType.NIGHT_VISION);
                m.getPlayer().setPlayerListName(ChatColor.WHITE + m.getName());
			}else if (zombie_list.contains(m.getName())) {
	            m.getPlayer().setPlayerListName(ChatColor.RED + m.getPlayer().getName());
                m.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 99999,0));
                m.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 99999, 0));
			}else if (superzombie_list.contains(m.getName())) {
				m.getPlayer().setPlayerListName(ChatColor.YELLOW + m.getPlayer().getName());
                m.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 99999, 0));
                m.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 99999, 0));
			}
    	}
    }public static List<String> noloag(List<String> m) {
    	List<String> s = new ArrayList<String>();
    	for(String r : m) {
    		if (!s.contains(r)) {
				s.add(r);
			}
    	}
    	return s;
    }
    public static List<Material> test(List<Material> s) {
    	s.add(Material.SHIELD);
        s.add(Material.DIAMOND_SWORD);
        s.add(Material.DIAMOND_HELMET);
        s.add(Material.DIAMOND_AXE);
        s.add(Material.DIAMOND_BOOTS);
        s.add(Material.DIAMOND_HELMET);
        s.add(Material.DIAMOND_CHESTPLATE);
        s.add(Material.DIAMOND_LEGGINGS);
        s.add(Material.IRON_SWORD);
        s.add(Material.IRON_HELMET);
        s.add(Material.IRON_AXE);
        s.add(Material.IRON_BOOTS);
        s.add(Material.IRON_HELMET);
        s.add(Material.IRON_CHESTPLATE);
        s.add(Material.IRON_LEGGINGS);
        s.add(Material.GOLDEN_SWORD);
        s.add(Material.GOLDEN_HELMET);
        s.add(Material.GOLDEN_AXE);
        s.add(Material.GOLDEN_BOOTS);
        s.add(Material.GOLDEN_HELMET);
        s.add(Material.GOLDEN_CHESTPLATE);
        s.add(Material.GOLDEN_LEGGINGS);
        s.add(Material.NETHERITE_SWORD);
        s.add(Material.NETHERITE_HELMET);
        s.add(Material.NETHERITE_AXE);
        s.add(Material.NETHERITE_BOOTS);
        s.add(Material.NETHERITE_HELMET);
        s.add(Material.NETHERITE_CHESTPLATE);
        s.add(Material.NETHERITE_LEGGINGS);
        s.add(Material.STONE_AXE);
        return s;
    }
    static ScoreboardManager manager = Bukkit.getScoreboardManager();
    static Scoreboard board = manager.getMainScoreboard();
    static List<String> zombie_list = new ArrayList<String>();
    static List<String> human_list = new ArrayList<String>();
    static List<String> superzombie_list = new ArrayList<String>();
    public static List<String> human(List<String> humanList){
       	humanList.add("momo_troll");
		return humanList;
    }public static List<String> zom(List<String> zomList){
    	zomList.add("jack");
    	zomList.add("elbert");
		return zomList;
    }public static List<String> superz(List<String> superList){
    	superList.add("SpAcE1_1DsT");
		return superList;
    }
}
