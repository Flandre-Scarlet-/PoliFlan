package com.flansoft.poliflan;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import android.util.Xml;

public class DictionaryParser {

	private static final String TAG = "polifland";
	private InputStream in;
	
	public DictionaryParser(InputStream in) {
		this.in = in;
	}
	
	public Dictionary parse() throws XmlPullParserException, IOException {
		Dictionary dictionary = new Dictionary();
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setInput(in, null);
			parser.nextTag();
			parser.require(XmlPullParser.START_TAG, null, "dictionary");
			while (parser.next() != XmlPullParser.END_TAG) {
				if (parser.getEventType() != XmlPullParser.START_TAG) {
					continue;
				}
				String name = parser.getName();
				if (name.equals("pronoun")) {
					dictionary.getPronouns().add(parsePronoun(parser));
				} else if (name.equals("verb")) {
					dictionary.getVerbs().add(parseVerb(parser));
				} else if (name.equals("special")) {
					Map<String, String> words = parseSpecial(parser);
					dictionary.getToBe().putAll(words);
				}
			}
		} finally {
			in.close();
		}
		return dictionary;
	}
	
	private Pronoun parsePronoun(XmlPullParser parser) throws XmlPullParserException, IOException {
		Map<String, String> words = new HashMap<String, String>();
		parser.require(XmlPullParser.START_TAG, null, "pronoun");
		boolean isPolite = false;
		String englishForm = "";
		String italianForm = "";
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String language = parser.getName();
			if (language.equals("en")) {
				parser.require(XmlPullParser.START_TAG, null, language);
				parseWords(parser, words);
				englishForm = words.get("word");
				String specific = words.get("specific");
				isPolite = "polite".equals(specific);
				parser.require(XmlPullParser.END_TAG, null, language);
			} else if (language.equals("it")) {
				parser.require(XmlPullParser.START_TAG, null, language);
				parseWords(parser, words);
				italianForm = words.get("word");
				parser.require(XmlPullParser.END_TAG, null, language);
			}
		}
		parser.require(XmlPullParser.END_TAG, null, "pronoun");
		return new Pronoun(englishForm, italianForm, isPolite);
	}
	
	private Verb parseVerb(XmlPullParser parser) throws XmlPullParserException, IOException {
		Map<String, String> words = new HashMap<String, String>();
		parser.require(XmlPullParser.START_TAG, null, "verb");
		String pastForm = null;
		String englishForm = "";
		String italianForm = "";
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String language = parser.getName();
			if (language.equals("en")) {
				parser.require(XmlPullParser.START_TAG, null, language);
				parseWords(parser, words);
				pastForm = words.get("specific");
				englishForm = words.get("word");
				parser.require(XmlPullParser.END_TAG, null, language);
			} else if (language.equals("it")) {
				parser.require(XmlPullParser.START_TAG, null, language);
				parseWords(parser, words);
				italianForm = words.get("word");
				parser.require(XmlPullParser.END_TAG, null, language);
			}
		}
		parser.require(XmlPullParser.END_TAG, null, "verb");
		if (pastForm != null) {
			return new Verb(englishForm, italianForm, pastForm);
		}
		return new Verb(englishForm, italianForm);
	}
	
	private void parseWords(XmlPullParser parser, Map<String, String> words) throws XmlPullParserException, IOException {
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			parser.require(XmlPullParser.START_TAG, null, name);
			String word = readText(parser);
			words.put(name, word);
			Log.d(TAG, name + ": " + word);
			parser.require(XmlPullParser.END_TAG, null, name);
		}
	}
	
	private Map<String, String> parseSpecial(XmlPullParser parser) throws XmlPullParserException, IOException {
		Map<String, String> words = new HashMap<String, String>();
		parser.require(XmlPullParser.START_TAG, null, "special");
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			parser.require(XmlPullParser.START_TAG, null, "it");
			parseWords(parser, words);
			parser.require(XmlPullParser.END_TAG, null, "it");
		}
		parser.require(XmlPullParser.END_TAG, null, "special");
		return words;
	}
	
	private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
		String result = "";
		if (parser.next() == XmlPullParser.TEXT) {
			result = parser.getText();
			parser.nextTag();
		}
		return result;
	}
}
