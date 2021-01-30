package ru.ItzDarth.DarthRPG.api.language;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum Language {
	RUSSIAN(new ConcurrentHashMap<>(), new ConcurrentHashMap<>()),
	ENGLISH(new ConcurrentHashMap<>(), new ConcurrentHashMap<>());
	
	private Map<String, String> messages;
	private Map<String, List<String>> listMessages;
	
	Language(Map<String, String> messages, Map<String, List<String>> listMessages) {
		this.messages = messages;
		this.listMessages = listMessages;
	}
	
	public String getMessage(String key) {
		return messages.get(key);
	}
	
	public List<String> getListMessage(String key) {
		return listMessages.get(key);
	}
	
	public void addMessage(String key, String message) {
		this.messages.put(key, message);
	}
	
	public void addListMessage(String key, List<String> listMessage) {
		this.listMessages.put(key, listMessage);
	}
	
}
