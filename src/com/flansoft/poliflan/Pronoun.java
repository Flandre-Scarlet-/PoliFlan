package com.flansoft.poliflan;

import java.util.HashMap;
import java.util.Map;

public class Pronoun {

	Conjugation conjugation;
	Map<String, String> words = new HashMap<String, String>();
	
	public Pronoun(Map<String, String> words, Conjugation conjugation) {
		this.words = words;
		this.conjugation = conjugation;
	}

	public Conjugation getConjugation() {
		return conjugation;
	}

	public Map<String, String> getWords() {
		return words;
	}
}
