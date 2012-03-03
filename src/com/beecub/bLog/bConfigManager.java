package com.beecub.bLog;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class bConfigManager {
	
	protected static bLog bLog;
    protected static FileConfiguration conf;
//    protected File confFile;
    
    static Set<String> toChat = new HashSet<String>();
    static Set<String> noCommand = new HashSet<String>(); 
    static boolean logHtml = true;
	
	@SuppressWarnings("static-access")
	public bConfigManager(bLog bLog) {
    	this.bLog = bLog;

    	load();
        new File(bLog.getDataFolder() + "/ChatLog/").mkdir();
        new File(bLog.getDataFolder() + "/CommandLog/").mkdir();
    }    
    
	private static void load() {
		conf = bLog.getConfig();
		reloadConfigElements();
    }
	
	public static void reload() {
		bLog.reloadConfig();
		reloadConfigElements();
	}
	
	private static void reloadConfigElements() {
    	toChat();
    	noCommand();
    	logHtml = conf.getBoolean("logHtml", true);
	}
	
	private static void toChat() {
//		toChat.clear();
//		toChat = conf.getStringList("toChat.", toChat);
		ConfigurationSection cs = conf.getConfigurationSection("toChat");
		toChat = cs.getKeys(false);
	}
	
	private static void noCommand() {
//		noCommand.clear();
//		noCommand = conf.getStringList("noCommand.", noCommand);
		ConfigurationSection cs = conf.getConfigurationSection("noCommand");
		noCommand = cs.getKeys(false);
	}
	
	public static boolean isToChat(String message) {
		if(toChat.contains(message)) {
			return true;
		}
		return false;
	}
	
	public static boolean isNoCommand(String message) {
		if(noCommand.contains(message)) {
			return true;
		}
		return false;
	}
	
	public static boolean isHtmlLogEnabled() {
		return logHtml;
	}
}
