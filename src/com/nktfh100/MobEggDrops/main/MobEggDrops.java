package com.nktfh100.MobEggDrops.main;

import org.bukkit.plugin.java.JavaPlugin;

import com.nktfh100.MobEggDrops.events.EntityDeath;
import com.nktfh100.MobEggDrops.managers.ConfigManager;

public class MobEggDrops extends JavaPlugin {

	private static MobEggDrops instance;

	public MobEggDrops() {
		instance = this;
	}
	
	private ConfigManager configManager;
	
	@Override
	public void onEnable() {
		this.configManager = new ConfigManager(this);
		this.configManager.loadConfig();
		
		this.getServer().getPluginManager().registerEvents(new EntityDeath(this), this);
	}

	public static MobEggDrops getInstance() {
		return instance;
	}

	public ConfigManager getConfigManager() {
		return configManager;
	}
}
