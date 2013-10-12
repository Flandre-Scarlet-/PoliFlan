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
				}
			}
		} finally {
			in.close();
		}
		return dictionary;
	}
	
	private Pronoun parsePronoun(XmlPullParser parser) throws XmlPullParserException, IOException {
		Map<String, String> words = new HashMap<String, String>();
		boolean isPolite = false;
		parser.require(XmlPullParser.START_TAG, null, "pronoun");
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String language = parser.getName();
			if (language.equals("en")) {
				parser.require(XmlPullParser.START_TAG, null, language);
				isPolite = parseWord(parser, language, words);
				parser.require(XmlPullParser.END_TAG, null, language);
			} else if (language.equals("it")) {
				parser.require(XmlPullParser.START_TAG, null, language);
				parseWord(parser, language, words);
				parser.require(XmlPullParser.END_TAG, null, language);
			}
		}
		parser.require(XmlPullParser.END_TAG, null, "pronoun");
		return new Pronoun(words, isPolite);
	}
	
	private Verb parseVerb(XmlPullParser parser) throws XmlPullParserException, IOException {
		Map<String, String> words = new HashMap<String, String>();
		parser.require(XmlPullParser.START_TAG, null, "verb");
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String language = parser.getName();
			if (language.equals("en")) {
				parser.require(XmlPullParser.START_TAG, null, language);
				parseWord(parser, language, words);
				parser.require(XmlPullParser.END_TAG, null, language);
			} else if (language.equals("it")) {
				parser.require(XmlPullParser.START_TAG, null, language);
				parseWord(parser, language, words);
				parser.require(XmlPullParser.END_TAG, null, language);
			}
		}
		parser.require(XmlPullParser.END_TAG, null, "verb");
		return new Verb(words);
	}
	
	private boolean parseWord(XmlPullParser parser, String language, Map<String, String> words) throws XmlPullParserException, IOException {
		boolean isPolite = false;
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals("word")) {
				parser.require(XmlPullParser.START_TAG, null, "word");
				String word = readText(parser);
				words.put(language, word);
				Log.d(TAG, "Word: " + word);
				parser.require(XmlPullParser.END_TAG, null, "word");
			} else if (name.equals("specific")) {
				parser.require(XmlPullParser.START_TAG, null, "specific");
				String specific = readText(parser);
				Log.d(TAG, "specific: " + specific);
				if (specific.equals("polite")) {
					isPolite = true;
				}
				parser.require(XmlPullParser.END_TAG, null, "specific");
			}
			
		}
		return isPolite;
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
