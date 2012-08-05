package com.beecub.bLog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public class bConfigManager {
	
	protected static bLog bLog;
    protected static FileConfiguration conf;
//    protected File confFile;
    
    static List<String> toChat = new ArrayList<String>();
    static List<String> noCommand = new ArrayList<String>(); 
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
		toChat = conf.getStringList("toChat");
	}
	
	private static void noCommand() {
//		noCommand.clear();
//		noCommand = conf.getStringList("noCommand.", noCommand);
		noCommand = conf.getStringList("noCommand");
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
