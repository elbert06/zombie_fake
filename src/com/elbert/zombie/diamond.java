package com.elbert.zombie;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class diamond implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd,String label,String[] args) {
		Player s = (Player) sender;
		Location trace = s.getLocation();
		int x = (int) trace.getX();		
		x = x - 8;
		int y = (int) trace.getY();
		y = y - 100;
		if (y < 0) {
			y = 0;
		}
		int z = (int) trace.getZ();
		z = z - 8;
		int newx = x + 16;
		int newy = y + 200;
		int newz = z + 16;
		while (x < newx) {
			y = (int) trace.getY();
			y = y - 100;
			if (y < 0) {
				y = 0;
			}
			while(y < newy) {
				z = (int) trace.getZ();
				z = z - 8;
				while(z < newz) {
					Location trace_b = new Location(s.getWorld(),x,y,z);
					Material sr = trace_b.getBlock().getType();
					if (sr.equals(Material.DIAMOND_ORE)) {
						s.sendMessage("다이아 의 위치는" + Integer.toString(x) +" "+Integer.toString(y) + " "+Integer.toString(z));
						return false;
					}
					z++;
				}
				y++;
			}
			x++;
		}
		s.sendMessage("주위 16칸에는 다이아가 없습니다");
		return false;
	}
	
}
