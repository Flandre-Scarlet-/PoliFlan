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
		if (random < 0.5) {
			// ask question! perche!
		}
		
		random = Math.random();
		boolean isPast = false;
		if (random < 0.5) {
			isPast = true;
		}
		
		random = Math.random();
		boolean isNegation = false;
		if (random < 0.5) {
			isNegation = true;
			if (isPast) {
				question += " didn't";
			} else if (pronoun.equals("he") || pronoun.equals("she")) {
				question += " doesn't";
			} else {
				question += " don't";
			}
			answer += " non";
			options = new ArrayList<String>();
			options.add("non");
			cases.add(options);
		}
		
		List<Verb> verbs = dictionary.getVerbs();
		Collections.shuffle(verbs);
		Verb verb = verbs.get(0);
		question += " ";
		if (isNegation) {
			question += verb.getEnglish("");
		} else if (isPast) {
			question += verb.getEnglishPast();
		} else {
			question += verb.getEnglish(pronoun.getEnglish());
		}
		Log.d("TAG", "pronoun=" + pronoun.getItalian() + "; verb=" + verb.getItalian(pronoun.getItalian()));
		answer += " ";
		if (isPast) {
			String amete = dictionary.getToBe().get(pronoun.getItalian());
			answer += amete + " " + verb.getItalianPast();
			options = new ArrayList<String>();
			options.addAll(dictionary.getToBe().values());
			options.add(0, amete);
			cases.add(options);
			options = new ArrayList<String>();
			for (int i = 0; i < 2; ++i) {
				for (int j = 0; j < 2; ++j) {
					options.add(verbs.get(i).getItalian(pronouns.get(j).getItalian()));
				}
				options.add(verbs.get(i).getItalianPast());
			}
		} else {
			answer += verb.getItalian(pronoun.getItalian());
			options = new ArrayList<String>();
			for (int i = 0; i < 2; ++i) {
				for (int j = 0; j < 2; ++j) {
					options.add(verbs.get(i).getItalian(pronouns.get(j).getItalian()));
				}
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
