package com.elbert.zombie;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

public class EventListener implements Listener{
	List<String> zombielist = new ArrayList<String>();
    List<String> humanlist = new ArrayList<String>();
    List<String> superzombielist = new ArrayList<String>();
    static HashMap<Player, Integer> Deathcount = setting.Deathcount;
    static List<String> zombie_list = setting.zombie_list;
    static List<String> human_list = setting.human_list;
    static List<String> superzombie_list = setting.superzombie_list;
    static Team zombie = setting.zombie;
    static Team superzom = setting.superzom;
	static ItemStack ss = new ItemStack(Material.BOW, 1);
    Location lava_status_location,status_location,final_location;
    Random random = new Random();
    Integer sr = 1;
    //사망후 부활했을때
    @SuppressWarnings("deprecation")
	@EventHandler
    public void tsem(PlayerRespawnEvent e) {
        zombie.setColor(ChatColor.RED);
        Player target = e.getPlayer();    
        if (human_list.contains(target.getName())) {
            human_list.remove(target.getName());
            zombie_list.add(target.getName());    
            zombie.addPlayer(target);
            hoin.disting(zombie_list, human_list, zombie_list);
        }   
        e.setRespawnLocation(final_location);
    }
    //사망했을떄 좀비의 아이템 드롭 & 메세지 알림
    @EventHandler    
    public void test(PlayerDeathEvent e) {
        Player dead_player = e.getEntity();
        final_location = dead_player.getLocation();
        final_location = status_location;
        lava_status_location = dead_player.getLocation(); 
        Material s = lava_status_location.getBlock().getType();
        if (s != Material.LAVA && dead_player.getWorld() == Bukkit.getWorlds().get(0)) {
            status_location = dead_player.getLocation(); 
		}
        if (human_list.contains(dead_player.getName())) {
            for (OfflinePlayer p : Bukkit.getOnlinePlayers()) {
                p.getPlayer().sendTitle(ChatColor.RED + "생존자 사망", ChatColor.RED + e.getEntity().getName() + "님이 사망하셨습니다", 10, 100, 10);
            }
            return;
        }else if (zombie_list.contains(dead_player.getName())) {
			if (dead_player.getKiller() != null) {
				Integer ssr = random.nextInt(10);
				if (ssr == 0) {
					Integer rr = random.nextInt(5);
					switch (rr) {
				    case 0:
				    	dead_player.getLocation().getWorld().dropItem(status_location,new ItemStack(Material.DIAMOND,1));
				    	break;
				    case 1:
				    	dead_player.getLocation().getWorld().dropItem(status_location,new ItemStack(Material.GLASS_BOTTLE,1));
				    	break;
				    case 2:
				    	dead_player.getLocation().getWorld().dropItem(status_location,new ItemStack(Material.WATER_BUCKET,1));
				    	break;
				    case 3:
				    	dead_player.getLocation().getWorld().dropItem(status_location,new ItemStack(Material.PAPER,1));
				    	break;
				    case 4:
				    	dead_player.getLocation().getWorld().dropItem(status_location,new ItemStack(Material.GOLDEN_APPLE,1));
						break;
				    default:
				    	break;
					}
				}
			}
		}
    }
    // 구분 및 특징 
    @SuppressWarnings("deprecation")
	@EventHandler
    public void nolag(PlayerJoinEvent e) {
    	zombielist = hoin.zom(zombielist);
    	humanlist = hoin.human(humanlist);
    	superzombielist = hoin.superz(superzombielist);		
    	if (!e.getPlayer().hasPlayedBefore()) {
			if (humanlist.contains(e.getPlayer().getName())) {
				superzom.removePlayer(e.getPlayer());
                human_list.remove(e.getPlayer().getName());
                zombie_list.remove(e.getPlayer().getName());      
                superzombie_list.remove(e.getPlayer().getName());
                zombie.removePlayer(e.getPlayer());          
                human_list.add(e.getPlayer().getName()); 
			}else if (zombielist.contains(e.getPlayer().getName())) {
            	superzom.removePlayer(e.getPlayer());
                zombie_list.remove(e.getPlayer().getName());
                human_list.remove(e.getPlayer().getName());
                superzombie_list.remove(e.getPlayer().getName());
                zombie_list.add(e.getPlayer().getName());
                zombie.addPlayer(e.getPlayer());
			}else if (superzombielist.contains(e.getPlayer().getName())) {
				Deathcount.put(e.getPlayer(), 2);
                superzom.addPlayer(e.getPlayer());
                zombie.removePlayer(e.getPlayer());
                zombie_list.remove(e.getPlayer().getName());
                human_list.remove(e.getPlayer().getName());
                superzombie_list.remove(e.getPlayer().getName());
                superzombie_list.add(e.getPlayer().getName());
			}
		}   
    	hoin.disting(zombie_list, human_list, superzombie_list);
    }
    @EventHandler
    public void tst(PlayerInteractEvent e) {
    	hoin.disting(zombie_list, human_list, superzombie_list);
    }
    @EventHandler
    public void tst(PlayerCommandSendEvent e) {
    	zombie_list = setting.zombie_list;
        human_list = setting.human_list;
    	superzombie_list = setting.superzombie_list;
    	superzom.setColor(ChatColor.YELLOW);
        zombie.setColor(ChatColor.RED);
    }
    //백신 주사
    @SuppressWarnings("deprecation")
	@EventHandler
    public void nodap(EntityShootBowEvent e) {
    	Player r = (Player) e.getEntity();
    	if (r.getItemInHand().getEnchantmentLevel(Enchantment.LUCK) == 3) {
			ItemMeta sss = ss.getItemMeta();
			sss.setDisplayName("이미 다 쓴 활");
			ss.setItemMeta(sss);
			r.setItemInHand(ss);
		}
    }
    @SuppressWarnings("deprecation")
	@EventHandler
    public void onArrowHit(EntityDamageByEntityEvent e) {
        superzom.setColor(ChatColor.YELLOW);
        zombie.setColor(ChatColor.RED);
        zombie.setAllowFriendlyFire(false);
        if (e.getCause().equals(DamageCause.PROJECTILE)) {
            Arrow a = (Arrow)e.getDamager();
            Player player = (Player)a.getShooter();
            Player vic = (Player)e.getEntity(); 
            if (player.getItemInHand().getEnchantmentLevel(Enchantment.LUCK) == 3) {
                if (human_list.contains(player.getName()) && zombie_list.contains(vic.getName())) {
                    zombie_list.remove(vic.getName());
                    human_list.add(vic.getName());
                    e.setDamage(0.0);
                    player.setItemInHand(null);
                    hoin.disting(zombie_list, human_list, superzombie_list);
                    vic.sendTitle(ChatColor.AQUA + "바이러스 해방", ChatColor.AQUA + "바이러스로부터 해방되었습니다!", 10, 100, 10);
                    zombie.removePlayer(vic);
                    player.getInventory().removeItem(new ItemStack(Material.BOW, 1));
                    for ( Player s : Bukkit.getOnlinePlayers()) {
                        s.sendMessage(vic.getName() + ChatColor.AQUA + "님이 바이러스로부터 해방되었습니다!");
                    }
                }
                else if (human_list.contains(player.getName()) && superzombie_list.contains(vic.getName())) {
                    Integer life_number = Deathcount.get(vic);
                    life_number = life_number - 1;
                    if (life_number == 0) {
                        superzombie_list.remove(vic.getName());
                        human_list.add(vic.getName());
                        e.setDamage(0.0);
                        hoin.disting(zombie_list, human_list, superzombie_list);
                        player.setItemInHand(null);
                        superzom.removePlayer(vic);
                        Deathcount.remove(vic);
                        vic.sendTitle(ChatColor.AQUA + "바이러스 해방", ChatColor.AQUA + "바이러스로부터 해방되었습니다!", 10, 100, 10);
                        player.getInventory().removeItem(new ItemStack(Material.CAKE, 1));
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.sendMessage(vic.getName() + ChatColor.AQUA + "님이 바이러스로부터 해방되었습니다!");
                        }
                     
                    }
                    else if (life_number == 1) {
                        vic.sendMessage("1번 남았습니다");
                        player.sendMessage(ChatColor.AQUA+"당신의 화살로 인해 " + ChatColor.WHITE+vic.getName()+ChatColor.AQUA + "님의 생존자까지 필요한 백신이 한개로 줄어들었습니다");
                        Deathcount.replace(vic, life_number);
                        player.setItemInHand(null);
                        player.getInventory().removeItem(new ItemStack(Material.CAKE,1));
                    }
                }
            }
        }
    }

    //만들기 금지
    @EventHandler    
    public void ma(CraftItemEvent e) {
        List<Material> no_craft_tools = new ArrayList<Material>();
        no_craft_tools = hoin.test(no_craft_tools);
        ItemStack s = e.getCurrentItem();
        Player m = (Player)e.getWhoClicked();
        m.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 99999, 1));
        hoin.disting(zombie_list, human_list, superzombie_list);
        if (human_list.contains(m.getName())) {
			return;
		}
        if (no_craft_tools.contains(s.getType())) {
            e.setCancelled(true);
        }else if (s.getType().equals(Material.BOW) && s.getEnchantmentLevel(Enchantment.LUCK) == 3) {
        	e.setCancelled(true);
		}
        
    }
    //낙하데미지 0
    @EventHandler
    public void breakaleg(EntityDamageEvent e) {
        Player breakleg_player = (Player)e.getEntity();
        hoin.disting(zombie_list, human_list, superzombie_list);
        if (e.getCause().equals((Object)EntityDamageEvent.DamageCause.FALL)) {
            e.setDamage(0.0);
            breakleg_player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 5));
        }else if (e.getCause().equals(DamageCause.LAVA)) {
        	Double lava_damage = e.getDamage();
        	lava_damage = lava_damage / 2;
        	e.setDamage(lava_damage);
		}
    }
    //데미지 넣기
    @EventHandler
    public void zombie_kill(EntityDamageByEntityEvent e) {
    	
        Player damage_vic = (Player)e.getEntity();
        Player damage_attacker = (Player) e.getDamager();
        if (superzombie_list.contains(damage_attacker.getName())) {
        	sr = 2;
		}
        if (human_list.contains(damage_vic.getName())) {
			 damage_vic.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 1));
			 damage_vic.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 1));
			 Double s = e.getDamage();
			 s = s / 2 * sr;
			 e.setDamage(s);
			 sr = 1;
		}
       
        
    }
    //아이템 사용
    @EventHandler
    public void test(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (human_list.contains(player.getName())) {
                return;
            }
            if (event.getItem().getType() == Material.DIAMOND) {
                Integer ms = random.nextInt(zombie_list.size());
                Integer mr = random.nextInt(zombie_list.size());
                Integer s = event.getPlayer().getInventory().getItemInMainHand().getAmount();
                event.getPlayer().getInventory().getItemInMainHand().setAmount(s - 1);
                if (superzombie_list.contains(event.getPlayer().getName())) {
                	while(mr == ms) {
                		mr = random.nextInt(zombie_list.size());
                		if (zombie_list.size() == 1) {
							break;
						}
                	}
                	Player ss = Bukkit.getPlayer(zombie_list.get(mr));
                    Player sr = Bukkit.getPlayer(zombie_list.get(ms));
                    ss.teleport(event.getPlayer());
                    ss.sendMessage("슈퍼좀비가 당신을 호출했습니다");
                    sr.teleport(event.getPlayer());
                    sr.sendMessage("슈퍼좀비가 당신을 호출했습니다");
                }
                else if (zombie_list.contains(event.getPlayer().getName())) { 
                	Integer mh = random.nextInt(human_list.size());
                    Player m = Bukkit.getPlayer(human_list.get(mh));
                    Location sf = m.getLocation();
                    Double xa = sf.getX();
                    Double xz = sf.getZ();
                    player.sendMessage(m.getName() + "의 위치는" + Integer.toString(xa.intValue()) + ", ? , " + Integer.toString(xz.intValue()));
                    m.sendMessage("당신의 위치가 노출되었습니다");
                }
            }
            if (event.getItem().getType() == Material.EMERALD) {
                Integer s2 = event.getPlayer().getInventory().getItemInMainHand().getAmount();
                event.getPlayer().getInventory().getItemInMainHand().setAmount(s2 - 1);
                if (superzombie_list.contains(event.getPlayer().getName())) {
                    Integer random_human_list = random.nextInt(human_list.size());
                    Player trace = Bukkit.getPlayer(human_list.get(random_human_list));
                    Location first_location = event.getPlayer().getLocation();
                    Location finalLocation = trace.getLocation();
                    event.getPlayer().setGameMode(GameMode.SPECTATOR);
                    event.getPlayer().teleport(finalLocation);
                    try {
                        Thread.sleep(5000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    event.getPlayer().setGameMode(GameMode.SURVIVAL);
                    event.getPlayer().teleport(first_location);
                }
                else if (zombie_list.contains(event.getPlayer().getName())) {
                    Location r = event.getPlayer().getLocation();
                    event.getPlayer().teleport(event.getPlayer().getWorld().getHighestBlockAt(r).getLocation());
                }
            }
        }
    }
}
