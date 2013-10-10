package com.flansoft.poliflan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView upperText;
	TextView lowerText;
	List<Button> answerButtons = new ArrayList<Button>();
	
	int countUpperWords = 1;
	int countLowerWords = 0;
	
	List<Pronoun> pronouns = new ArrayList<Pronoun>();
	List<Verb> verbs = new ArrayList<Verb>();
	Lesson1Question question;
	
	public MainActivity() {
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
	}
	
	private void generateQuestion() {
		upperText.setBackgroundColor(getResources().getColor(R.color.white));
		lowerText.setText("");
		setClickableAnswerButtons(true);
		Collections.shuffle(pronouns);
		Collections.shuffle(verbs);
		Collections.shuffle(answerButtons);
		question = new Lesson1Question(pronouns.get(0), verbs.get(0));
		upperText.setText(question.getEnglish());
		for (int i = 0; i < answerButtons.size(); ++i) {
			answerButtons.get(i).setText(pronouns.get(i).getItalian());
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		upperText = (TextView)findViewById(R.id.textView1);
		lowerText = (TextView)findViewById(R.id.textView2);
		
		answerButtons.add((Button) findViewById(R.id.button1));
		answerButtons.add((Button) findViewById(R.id.button2));
		answerButtons.add((Button) findViewById(R.id.button3));
		answerButtons.add((Button) findViewById(R.id.button4));
		answerButtons.add((Button) findViewById(R.id.button5));
		
		generateQuestion();
	}
	
	public void onClick(View v){
		Button b = (Button)v;
		String buttonText = b.getText().toString();
		Log.d("upperText", upperText.getText().toString());
		lowerText.setText(lowerText.getText() + buttonText + " ");
		Log.d("lowerText", lowerText.getText().toString());
		countLowerWords++;
		if (countUpperWords <= countLowerWords) {
			check();
		}
	}
	 
	public void check(){
		setClickableAnswerButtons(false);
		String model_answer = question.getItalian();
		String answer = lowerText.getText().toString().trim();
		Log.d("CHECK_upper", model_answer);
		Log.d("CHECK_lower", answer);
		if (model_answer.equals(answer)) {
			upperText.setBackgroundColor(getResources().getColor(R.color.green));
		} else{
			upperText.setBackgroundColor(getResources().getColor(R.color.red));
		}
		Handler handler = new Handler();
		handler.postDelayed(new Runnable(){
			public void run(){
				generateQuestion();
			}
		}, 1000);
	}
	
	public void setClickableAnswerButtons(Boolean set){
		for (Button button : answerButtons) {
			button.setClickable(set);
		}
	}
}
