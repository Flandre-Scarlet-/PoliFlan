package com.flansoft.poliflan;

import java.util.HashMap;
import java.util.Map;

public class Verb {

	private Map<String, String> infinitive = new HashMap<String, String>();
	private String englishPast = "";
	
	public Verb(String english, String italian) {
		infinitive.put("en", english);
		infinitive.put("it", italian);
	}
	
	public Verb(String english, String italian, String englishPast) {
		this(english, italian);
		this.englishPast = englishPast;
	}
	
	private String getItalianRoot() {
		String word = this.infinitive.get("it");
		word = word.substring(0, word.length() - 3);
		return word;
	}
	
	public String getEnglish(String pronoun) {
		if (pronoun == null || "".equals(pronoun)) {
			return infinitive.get("en");
		}
		if (pronoun.equals("he") || pronoun.equals("she")) {
			return infinitive.get("en") + 's';
		}
		return infinitive.get("en");
		
	}
	
	public String getEnglishPast() {
		if (englishPast.length() > 0) {
			return englishPast;
		}
		String inf = infinitive.get("en");
		char lastChar = inf.charAt(inf.length() - 1);
		if (lastChar == 'e') {
			return inf + "d";
		}
		return inf + "ed";
	}
	
	public String getItalian(String pronoun) {
		if (pronoun == null || "".equals(pronoun)) {
			return infinitive.get("it");
		}
		String root = getItalianRoot();
		if (pronoun.equals("io")) {
			return root + "o";
		} else if (pronoun.equals("tu")) {
			return root + "i";
		} else if (pronoun.equals("lui") || pronoun.equals("lei") || pronoun.equals("Lei")) {
			return root + "a";
		} else if (pronoun.equals("noi")) {
			return root + "iamo";
		} else if (pronoun.equals("voi")) {
			return root + "ate";
		} else if (pronoun.equals("loro")) {
			return root + "ano";
		}
		return this.infinitive.get("it");
	}
	
	public String getItalianPast() {
		String root = getItalianRoot();
		return root + "ato";
	}
}
