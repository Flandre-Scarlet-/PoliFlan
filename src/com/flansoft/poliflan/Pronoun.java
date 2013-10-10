package com.flansoft.poliflan;

import java.util.HashMap;
import java.util.Map;

public class Pronoun implements Translatable {

	Conjugation conjugation;
	Map<String, String> words = new HashMap<String, String>();
	
	public Pronoun(String en, String it, String ru, Conjugation conjugation) {
		words.put("en", en);
		words.put("it", it);
		words.put("ru", ru);
		this.conjugation = conjugation;
	}

	public Conjugation getConjugation() {
		return conjugation;
	}

	@Override
	public String getItalian() {
		return words.get("it");
	}

	@Override
	public String getEnglish() {
		if (conjugation == Conjugation.SECOND_PLURAL) {
			return words.get("en") + " (plural)";
		}
		return words.get("en");
	}

	@Override
	public String getRussian() {
		return words.get("ru");
	}
}
