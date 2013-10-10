package com.flansoft.poliflan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lesson1Question implements Translatable {

	private Pronoun pronoun;
	private Verb verb;
	
	List<Pronoun> pronouns = new ArrayList<Pronoun>();
	List<Verb> verbs = new ArrayList<Verb>();
	
	public Lesson1Question() {
		Pronoun pronoun = new Pronoun("I", "io", "я", Conjugation.FIRST);
		pronouns.add(pronoun);
		
		pronoun = new Pronoun("you", "tu", "ты", Conjugation.SECOND);
		pronouns.add(pronoun);

		pronoun = new Pronoun("he", "lui", "он", Conjugation.THIRD);
		pronouns.add(pronoun);

		pronoun = new Pronoun("she", "lei", "она", Conjugation.THIRD);
		pronouns.add(pronoun);

		pronoun = new Pronoun("we", "noi", "мы", Conjugation.FIRST_PLURAL);
		pronouns.add(pronoun);
		
		pronoun = new Pronoun("you", "voi", "ты", Conjugation.SECOND_PLURAL);
		pronouns.add(pronoun);	

		pronoun = new Pronoun("they", "loro", "они", Conjugation.THIRD_PLURAL);
		pronouns.add(pronoun);
		
		Verb verb = new Verb("speak", "parlare", "говорить");
		verbs.add(verb);
		
		verb = new Verb("eat", "mangiare", "кушать");
		verbs.add(verb);
		
		verb = new Verb("look", "guardare", "смотреть");
		verbs.add(verb);
		
		verb = new Verb("play", "giocare", "играть");
		verbs.add(verb);
		
		verb = new Verb("work", "lavorare", "работать");
		verbs.add(verb);
		
		verb = new Verb("love", "amare", "любить");
		verbs.add(verb);
		
		verb = new Verb("listen", "ascoltare", "слушать");
		verbs.add(verb);
		
		verb = new Verb("learn", "imparare", "учить");
		verbs.add(verb);
		
		verb = new Verb("live", "abitare", "жить");
		verbs.add(verb);
		
		Collections.shuffle(pronouns);
		Collections.shuffle(verbs);
		
		this.pronoun = pronouns.get(0);
		this.verb = verbs.get(0);
	}

	public Translatable[] getPronounChoices() {
		return pronouns.toArray(new Translatable[pronouns.size()]);
	}
	
	public Translatable[] getVerbChoices() {
		return verbs.toArray(new Translatable[verbs.size()]);
	}
	
	@Override
	public String getItalian() {
		verb.setConjugation(pronoun.getConjugation());
		return pronoun.getItalian() + " " + verb.getItalian();
	}

	@Override
	public String getEnglish() {
		verb.setConjugation(pronoun.getConjugation());
		return pronoun.getEnglish() + " " + verb.getEnglish();
	}

	@Override
	public String getRussian() {
		// TODO Auto-generated method stub
		return null;
	}
}
