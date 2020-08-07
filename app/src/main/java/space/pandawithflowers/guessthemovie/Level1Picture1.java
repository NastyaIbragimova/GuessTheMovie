package space.pandawithflowers.guessthemovie;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Level1Picture1 extends AppCompatActivity {
    private String rightAnswer = Array.level1[1].toUpperCase();
    private char[] wordsLetters;
    private int myCount = 0;
    private LinearLayout mainLayout1, mainLayout2, mainLayout3;
    private ImageView picture;
    private ArrayList<TextView> tvArray = new ArrayList<>();
    private ArrayList<TextView> letters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels_picture);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init() {
        wordsLetters = rightAnswer.toCharArray();
        mainLayout1 = findViewById(R.id.containerForWords1);
        mainLayout2 = findViewById(R.id.containerForWords2);
        mainLayout3 = findViewById(R.id.containerForWords3);
        picture = findViewById(R.id.picture);
        picture.setImageResource(R.drawable.fight_club_level1);
        createLetters();
        setLetters();
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
            Intent intent = new Intent(this, Level1.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
        }
    }

    public void onClickLetters(View view) {
        TextView let = (TextView) view;
        if (myCount < tvArray.size()) {
            if (wordsLetters[myCount] != ' ') {
                tvArray.get(myCount).setText(let.getText());
                myCount++;
            } else {
                tvArray.get(myCount + 1).setText(let.getText());
                myCount = myCount + 2;
            }
            let.setBackgroundResource(R.drawable.button_letter_pressed);
            let.setEnabled(false);
            if (isAnswerRight()) {
                Toast.makeText(this, "Win", Toast.LENGTH_SHORT).show();
                win();
            }
        } else {
            for (int i = 0; i < letters.size(); i++) {
                letters.get(i).setEnabled(false);
            }
        }
    }

    private void createLetters() {
        int size = wordsLetters.length;
        for (int i = 0; i < size; i++) {
            final TextView tv = new TextView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setPadding(5, 0, 5, 0);
            tv.setTextSize(24);
            tv.setWidth(80);
            tv.setTextColor(getColor(R.color.black95));
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            layoutParams.setMargins(10, 0, 10, 0);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv.setText(" ");
                }
            });
            tvArray.add(tv);
            if (tv.getParent() != null) {
                ((ViewGroup) tv.getParent()).removeView(tv);
            }
            if (wordsLetters[i] != ' ') {
                tv.setBackground(getResources().getDrawable(R.drawable.button_letter));
                tv.setText(" ");
            } else {
                tv.setVisibility(View.INVISIBLE);
                tv.setText("");
            }
            if (i < 10) {
                mainLayout1.addView(tv, layoutParams);
            } else if (i < 20) {
                mainLayout2.addView(tv, layoutParams);
            } else if (i < 30) {
                mainLayout3.addView(tv, layoutParams);
            }
        }
        for (int i = 0; i < Array.letters_id.length; i++) {
            int id = Array.letters_id[i];
            TextView textView = findViewById(id);
            letters.add(textView);
        }
    }

    private boolean isAnswerRight() {
        boolean result = false;
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < tvArray.size(); i++) {
            answer.append(tvArray.get(i).getText().toString());
        }
        String right = "";
        for (int i = 0; i < wordsLetters.length; i++) {
            if (wordsLetters[i] != ' ') {
                right += wordsLetters[i];
            }
        }
        if (answer.toString().equals(right)) {
            result = true;
        }
        return result;
    }

    private void win() {
        for (int i = 0; i < tvArray.size(); i++) {
            tvArray.get(i).setBackgroundResource(R.drawable.letters_right);
        }
        for (int i = 0; i < letters.size(); i++) {
            letters.get(i).setEnabled(false);
        }
    }
}
