// 
package com.elbert.zombie;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.Material;

import java.util.List;

public class vaccine3
{
    public static List<Material> saa;
    public static ItemStack wand;
    static ItemMeta meta;
    static ItemStack vacc;
    static ItemStack itema;
    static ShapelessRecipe sa;
    static ShapelessRecipe sf;
    static ShapedRecipe sh;
    static ShapelessRecipe ss;
    static ShapedRecipe sm;
    public static void init() { createBand(); }
	@SuppressWarnings("deprecation")
	private static void createBand() {
    	ItemStack ss = EventListener.ss;
    	
    	vacc = new ItemStack(Material.BOW,1);
        meta = vacc.getItemMeta();
        meta.addEnchant(Enchantment.LUCK, 3, false);
        meta.setDisplayName("vaccine");
        vacc.setItemMeta(meta);
        vacc.setDurability((short)(vacc.getType().getMaxDurability() - 2));
        sf = new ShapelessRecipe(NamespacedKey.minecraft("vac1"), vacc);
        sf.addIngredient(1, Material.DIAMOND);
        sf.addIngredient(1, Material.GLASS_BOTTLE);
        sf.addIngredient(1, Material.PAPER);
        sf.addIngredient(1,Material.GOLD_BLOCK);
        sf.addIngredient(1,Material.WATER_BUCKET);
        Bukkit.getServer().addRecipe(sf);
        ItemMeta sss = ss.getItemMeta();
		sss.setDisplayName("이미 다 쓴 활");
		ss.setItemMeta(sss);
        sh = new ShapedRecipe(NamespacedKey.minecraft("rebuild"), vacc);
        sh.shape(" S "," M "," T ");
        sh.setIngredient('S',new RecipeChoice.ExactChoice(ss));
        sh.setIngredient('M', Material.DIAMOND);
        sh.setIngredient('T', Material.PAPER);
        Bukkit.getServer().addRecipe(sh);
    }
}
