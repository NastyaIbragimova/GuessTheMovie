package space.pandawithflowers.guessthemovie;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Level1Picture1 extends AppCompatActivity {
    private EditText answerWord1;
    private EditText answerWord2;
    private EditText answerWord3;
    private String rightAnswer = Array.level1[1].toUpperCase();
    private String[] words;
    private char[] wordsLetters;
    private int[] count = new int[27];
    private int myCount1=0;
    private int myCount2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels_picture);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
        setText();
        setVisibility();
        setLetters();

    }

    private void init() {
        words = rightAnswer.split(" ");
        answerWord1 = findViewById(R.id.answer_1word);
        answerWord2 = findViewById(R.id.answer_2word);
        answerWord3 = findViewById(R.id.answer_3word);
        wordsLetters = rightAnswer.toCharArray();
        for (int i = 0; i < count.length; i++) {
            count[i] = i;
        }
    }


    public void onClickLetter1(View view) {
    }

    private void setVisibility() {
        switch (words.length) {
            case 1:
                answerWord1.setVisibility(View.VISIBLE);
                break;
            case 2:
                answerWord1.setVisibility(View.VISIBLE);
                answerWord2.setVisibility(View.VISIBLE);
                break;
            case 3:
                answerWord1.setVisibility(View.VISIBLE);
                answerWord2.setVisibility(View.VISIBLE);
                answerWord3.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setText() {
        int countWords = words.length;
        StringBuilder word1 = new StringBuilder();
        StringBuilder word2 = new StringBuilder();
        StringBuilder word3 = new StringBuilder();
        if (countWords == 1) {
            for (int i = 0; i < words[0].length(); i++) {
                word1.append("_ ");
            }
            answerWord1.setText(word1);
        } else if (countWords == 2) {
            for (int i = 0; i < words[0].length(); i++) {
                word1.append("_ ");
            }
            for (int i = 0; i < words[1].length(); i++) {
                word2.append("_ ");
            }
            answerWord1.setText(word1);
            answerWord1.setMaxLines(word1.length());
            answerWord2.setText(word2);
            answerWord2.setMaxLines(word2.length());
        } else if (countWords == 3) {
            for (int i = 0; i < words[0].length(); i++) {
                word1.append("_ ");
            }
            for (int i = 0; i < words[1].length(); i++) {
                word2.append("_ ");
            }
            for (int i = 0; i < words[2].length(); i++) {
                word3.append("_ ");
            }
            answerWord1.setText(word1);
            answerWord2.setText(word2);
            answerWord3.setText(word3);
        }
    }

    private void setLetters() {
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < wordsLetters.length; i++) {
            if (wordsLetters[i] != ' ') {
                list.add(wordsLetters[i]);
            }
        }
        while (list.size() < 27) {
            Random random = new Random();
            int x = random.nextInt(32);
            list.add(Array.letters[x]);
        }
        Collections.shuffle(list);

        for (int i = 0; i < 27; i++) {
            int id = Array.letters_id[i];
            TextView let = findViewById(id);
            let.setText(String.valueOf(list.get(i)));
        }
    }

    //Кнопка для перехода назад
    public void onClickButtonBack(View view) {
        try {
            Intent intent = new Intent(this, GameLevels.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
        }
    }

    public void onClickLetters(View view) {
        char[] word1 = answerWord1.getText().toString().toCharArray();
        TextView letter = (TextView) view;
        word1[myCount1] = letter.getText().toString().charAt(0);
        word1[myCount1 + 1] = ' ';
        myCount1+=2;
        answerWord1.setText(String.valueOf(word1));
        Log.d("My log", String.valueOf(word1));
        Log.d("My log  answerWord 1 = ", answerWord1.length() + "");
        Log.d("My log   i =", myCount1 + "");
        if(myCount1>=word1.length){
            char[] word2 = answerWord2.getText().toString().toCharArray();
            word2[myCount2] = letter.getText().toString().charAt(0);
            word2[myCount2 + 1] = ' ';
            myCount2+=2;
            answerWord2.setText(String.valueOf(word2));
            Log.d("My log", String.valueOf(word2));
            Log.d("My log  answerWord 2 = ", answerWord2.length() + "");
            Log.d("My log   i =", myCount2 + "");
        }
    }
}
