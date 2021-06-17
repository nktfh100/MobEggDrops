package com.nktfh100.MobEggDrops.events;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.nktfh100.MobEggDrops.main.MobEggDrops;
import com.nktfh100.MobEggDrops.managers.ConfigManager;

public class EntityDeath implements Listener {

	private MobEggDrops plugin;
	private Random random = new Random();

	public EntityDeath(MobEggDrops instance) {
		this.plugin = instance;
	}

	@EventHandler
	public void onMobDeath(EntityDeathEvent ev) {
		if (plugin.getConfigManager().isMobBlacklisted(ev.getEntityType())) {
			return;
		}
		ConfigManager configManager = plugin.getConfigManager();

		Material eggMat = Material.getMaterial(ev.getEntityType().toString() + "_SPAWN_EGG");
		if (eggMat != null) {
			if (Math.random() * 100 <= configManager.getChance()) {
				Location loc = ev.getEntity().getLocation();
				Item droppedItem = loc.getWorld().dropItem(loc, new ItemStack(eggMat, getRandomNumber(configManager.getMinimumAmount(), configManager.getMaximumAmount() + 1)));
				droppedItem.getWorld().spawnParticle(configManager.getParticleType(), droppedItem.getLocation().add(0, 0.65, 0), configManager.getParticleAmount(), configManager.getParticleOffsetX(),
						configManager.getParticleOffsetY(), configManager.getParticleOffsetZ());
				if (configManager.getParticleType() != null) {
					new BukkitRunnable() {

						private int i = 0;

						@Override
						public void run() {
							if (droppedItem.isDead() || i >= configManager.getParticleTime()) {
								this.cancel();
								return;
							}
							droppedItem.getWorld().spawnParticle(configManager.getParticleType(), droppedItem.getLocation().add(0, 0.65, 0), 1, 0, 0, 0);
							i++;
						}
					}.runTaskTimer(plugin, 20L, 20L);
				}
			}
		}
	}

	private int getRandomNumber(int min, int max) {
		return random.nextInt(max - min) + min;
	}
}
