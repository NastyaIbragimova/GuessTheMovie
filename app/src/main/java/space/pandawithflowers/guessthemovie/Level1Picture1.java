package space.pandawithflowers.guessthemovie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Level1Picture1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels_picture);
     /*   Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

    }



    //Кнопка для перехода назад
    public void onClickButtonBack(View view){
        try {
            Intent intent = new Intent(this, GameLevels.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
        }
    }
    //Изменение системной кнопки "Назад"
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(this, GameLevels.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
        }
    }
}
