package com.flansoft.poliflan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lesson1 {

	private Dictionary dictionary;
	private List<List<String>> cases = new ArrayList<List<String>>();
	private String answer;
	
	public Lesson1(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	
	public String getQuestion() {
		String question = "";
		answer = "";
		List<Pronoun> pronouns = dictionary.getPronouns();
		Collections.shuffle(pronouns);
		Pronoun pronoun = pronouns.get(0);
		question += pronoun.getEnglish();
		answer += pronoun.getItalian();
		List<String> options = new ArrayList<String>();
		for (Pronoun option: pronouns) {
			options.add(option.getItalian());
		}
		cases.add(options);
		
		List<Verb> verbs = dictionary.getVerbs();
		Collections.shuffle(verbs);
		Verb verb = verbs.get(0);
		question += " " + verb.getEnglish(question);
		answer += " " + verb.getItalian(answer);
		options = new ArrayList<String>();
		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 2; ++j) {
				options.add(verbs.get(i).getItalian(pronouns.get(j).getItalian()));
			}
		}
		cases.add(options);
		
		return question;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public List<String> getCases() {
		return cases.remove(0);
	}
	
}
