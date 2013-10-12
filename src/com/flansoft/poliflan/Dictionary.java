package com.flansoft.poliflan;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {

	private List<Pronoun> pronouns = new ArrayList<Pronoun>();
	private List<Verb> verbs = new ArrayList<Verb>();
	
	public List<Pronoun> getPronouns() {
		return pronouns;
	}
	
	public List<Verb> getVerbs() {
		return verbs;
	}
	
}
