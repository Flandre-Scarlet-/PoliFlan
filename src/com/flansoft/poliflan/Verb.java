package com.flansoft.poliflan;

import java.util.HashMap;
import java.util.Map;

public class Verb implements Translatable {

	private Map<String, String> infinitive = new HashMap<String, String>();
	private Conjugation conjugation = Conjugation.INFINITIVE;
	
	public Verb(String en, String it, String ru) {
		this.infinitive.put("en",  en);
		this.infinitive.put("it",  it);
		this.infinitive.put("ru", ru);
	}
	
	private String getItalianRoot() {
		String word = this.infinitive.get("it");
		word = word.substring(0, word.length() - 3);
		return word;
	}
	
	public void setConjugation(Conjugation conjugation) {
		this.conjugation = conjugation;
	}
	
	@Override
	public String getItalian() {
		String root = getItalianRoot();
		switch (conjugation) {
		case INFINITIVE:
			return this.infinitive.get("it");
		case FIRST:
			return root + "o";
		case SECOND:
			return root + "i";
		case THIRD:
			return root + "a";
		case FIRST_PLURAL:
			return root + "iamo";
		case SECOND_PLURAL:
			return root + "ate";
		case THIRD_PLURAL:
			return root + "ano";
		}
		return this.infinitive.get("it");
	}

	@Override
	public String getEnglish() {
		switch (conjugation) {
		case INFINITIVE:
		case FIRST:
		case SECOND:
		case FIRST_PLURAL:
		case SECOND_PLURAL:
		case THIRD_PLURAL:
			return this.infinitive.get("en");
		case THIRD:
			return this.infinitive.get("en") + "s";
		}
		return this.infinitive.get("en");
	}

	@Override
	public String getRussian() {
		// TODO Auto-generated method stub
		return null;
	}
}
