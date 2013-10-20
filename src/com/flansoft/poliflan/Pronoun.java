package com.flansoft.poliflan;

import java.util.HashMap;
import java.util.Map;

public class Pronoun {

	private Map<String, String> words = new HashMap<String, String>();
	private boolean isPolite;
	
	public Pronoun(String english, String italian) {
		this.words.put("en", english);
		this.words.put("it", italian);
	}
	
	public Pronoun(String english, String italian, boolean isPolite) {
		this(english, italian);
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
