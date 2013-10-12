package com.flansoft.poliflan;

import java.util.HashMap;
import java.util.Map;

public class Pronoun {

	private Map<String, String> words = new HashMap<String, String>();
	private boolean isPolite;
	
	public Pronoun(Map<String, String> words) {
		this.words = words;
	}
	
	public Pronoun(Map<String, String> words, boolean isPolite) {
		this.words = words;
		this.isPolite = isPolite;
	}

	public String getEnglish() {
		if (isPolite) {
			return words.get("en") + " (polite)";
		} else if (words.get("it").equals("voi")) {
			return words.get("en") + " (plural)";
		}
		return words.get("en");
	}
	
	public String getItalian() {
		return words.get("it");
	}
	
	public boolean isPolite() {
		return isPolite;
	}
}
