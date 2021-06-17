package com.nktfh100.MobEggDrops.managers;

import java.util.List;

import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

import com.nktfh100.MobEggDrops.main.MobEggDrops;

public class ConfigManager {

	private MobEggDrops plugin;

	private double chance;
	private int minimumAmount;
	private int maximumAmount;
	private List<String> blackListedMobs;
	
	private Particle particleType;
	private int particleAmount;
	private double particleOffsetX;
	private double particleOffsetY;
	private double particleOffsetZ;
	private int particleTime;
	
	public ConfigManager(MobEggDrops instance) {
		this.plugin = instance;
	}

	public void loadConfig() {
		this.plugin.saveDefaultConfig();
		this.plugin.reloadConfig();

		FileConfiguration config = this.plugin.getConfig();

		this.chance = config.getDouble("chance", 50.0);
		this.minimumAmount = config.getInt("minimum-amount", 1);
		this.maximumAmount = config.getInt("maximum-amount", 2);

		this.blackListedMobs = config.getStringList("blacklisted-mobs");
		
		Particle particle = Particle.valueOf(config.getString("particles.type").toUpperCase());
		if(particle != null) {
			this.particleType = particle;
			this.particleAmount = config.getInt("particles.amount", 5);
			this.particleOffsetX = config.getDouble("particles.offset.x", 0.2);
			this.particleOffsetY = config.getDouble("particles.offset.y", 0.2);
			this.particleOffsetZ = config.getDouble("particles.offset.z", 0.2);
			this.particleTime = config.getInt("particles.time", 5);
		}
	}

	public boolean isMobBlacklisted(EntityType entityType) {
		return this.blackListedMobs.contains(entityType.toString());
	}

	public double getChance() {
		return chance;
	}

	public int getMinimumAmount() {
		return minimumAmount;
	}

	public int getMaximumAmount() {
		return maximumAmount;
	}

	public Particle getParticleType() {
		return particleType;
	}

	public int getParticleAmount() {
		return particleAmount;
	}

	public double getParticleOffsetX() {
		return particleOffsetX;
	}

	public double getParticleOffsetY() {
		return particleOffsetY;
	}

	public double getParticleOffsetZ() {
		return particleOffsetZ;
	}

	public int getParticleTime() {
		return particleTime;
	}
}
