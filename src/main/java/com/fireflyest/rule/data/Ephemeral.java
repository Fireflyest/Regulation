package com.fireflyest.rule.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Ephemeral {
	
	public static Ephemeral getInstance() { return eph; }
	
	private static Ephemeral eph = new Ephemeral();
	
//	private static Set<String>online = new HashSet<>();
	private static Map<String, String>back = new HashMap<>();
	private static Map<String, Integer>level = new HashMap<>();
	private static Set<String>mute = new HashSet<>();
	private static Set<String>dnd = new HashSet<>();

	private Ephemeral() {
	}
	
//	public static void addOnline(String name) {
//		online.add(name);
//	}
//
//	public static void removeOnline(String name) {
//		online.remove(name);
//	}
//
//	public static boolean isOnline(String name) {
//		return online.contains(name);
//	}
	
	public static void addMute(String name) {
		mute.add(name);
	}
	
	public static void removeMute(String name) {
		mute.remove(name);
	}
	
	public static boolean isMute(String name) {
		return mute.contains(name);
	}
	
	public static void addDnd(String name) {
		dnd.add(name);
	}
	
	public static void removeDnd(String name) {
		dnd.remove(name);
	}
	
	public static boolean isDnd(String name) {
		return dnd.contains(name);
	}
	
//	public static Set<String> getOnlines() {
//		return online;
//	}
	
	public static void putBack(String name, String loc) {
		back.put(name, loc);
	}
	
	public static String getBack(String name) {
		return back.getOrDefault(name, "");
	}
	
	public static void putLevel(String name, int value) {
		level.put(name, value);
	}
	
	public static int getLevel(String name) {
		return level.getOrDefault(name, 0);
	}
	
}
