package com.flansoft.poliflan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {

	private List<Pronoun> pronouns = new ArrayList<Pronoun>();
	private List<Verb> verbs = new ArrayList<Verb>();
	private Map<String, String> toBe = new HashMap<String, String>();
	
	public List<Pronoun> getPronouns() {
		return pronouns;
	}
	
	public List<Verb> getVerbs() {
		return verbs;
	}
	
	public Map<String, String> getToBe() {
		return toBe;
	}
	
}
