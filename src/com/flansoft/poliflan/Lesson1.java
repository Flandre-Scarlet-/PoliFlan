package com.flansoft.poliflan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.util.Log;

public class Lesson1 {

	private Dictionary dictionary;
	private List<List<String>> cases = new ArrayList<List<String>>();
	private String answer;
	private int answerWordsCount;
	
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
		
		double random = Math.random();
		if (random < 0.3) {
			// add neglection
			if (pronoun.equals("he") || pronoun.equals("she")) {
				question += " doesn't";
			} else {
				question += " don't";
			}
			answer += " non";
			options = new ArrayList<String>();
			options.add("non");
			cases.add(options);
		} else if (random < 0.6) {
			// past
		} else if (random < 0.8) {
			// question
		} else if (random < 0.9) {
			// simple
		} else {
			// special phrases
		}
		
		List<Verb> verbs = dictionary.getVerbs();
		Collections.shuffle(verbs);
		Verb verb = verbs.get(0);
		question += " " + verb.getEnglish(question);
		Log.d("TAG", "pronount=" + pronoun.getItalian() + "; verb=" + verb.getItalian(pronoun.getItalian()));
		answer += " " + verb.getItalian(pronoun.getItalian());
		options = new ArrayList<String>();
		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 2; ++j) {
				options.add(verbs.get(i).getItalian(pronouns.get(j).getItalian()));
			}
		}
		cases.add(options);
		answerWordsCount = cases.size();
		return question;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public int getAnswerWordsCount() {
		return answerWordsCount;
	}
	
	public List<String> getNextCases() {
		return cases.remove(0);
	}
	
}
