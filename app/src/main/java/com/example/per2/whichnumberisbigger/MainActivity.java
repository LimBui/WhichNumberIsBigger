package com.example.per2.whichnumberisbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonLeft;
    private Button buttonRight;
    private TextView textViewScore;
    private int score;
    private int leftNum;
    private int rightNum;

    public static final int MAX_NUM = 1000;
    public static final int MIN_NUM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        randomizeAndUpdateDisplay();
    }

    private void randomizeAndUpdateDisplay() {
        // TODO set the score
        String scoreString = getResources().getString(R.string.score_main);
        textViewScore.setText(scoreString + score);
        //TODO randomize numbers
        randomizeNumbers();
        //TODO set the button values
        buttonLeft.setText(String.valueOf(leftNum));
        buttonRight.setText(String.valueOf(rightNum));

    }
    private void randomizeNumbers() {
        //generate a random number for the left
        int range = MAX_NUM - MIN_NUM + 1;
        leftNum = MIN_NUM + (int)(Math.random() * range);
        rightNum = MIN_NUM + (int)(Math.random() * range);
        //generate a random number for the right but make sure it doesn't
        while (leftNum == rightNum) {
            rightNum = genNumber();
        }
        //match the left
    }

    private int genNumber() {
        int range = MAX_NUM - MIN_NUM + 1;
        return MIN_NUM + (int)(Math.random() * range);
    }

    private void wireWidgets() {
        buttonLeft = findViewById(R.id.button_main_left);
        buttonRight = findViewById(R.id.button_main_right);
        textViewScore = findViewById(R.id.textView_main_score);
    }

    public void onClickRight(View view) {
        checkAnswer( false);
    }
    public void onClickLeft(View view) {
        checkAnswer(  true);
    }
    public void checkAnswer(boolean leftPressed) {
        String message;
        if((leftNum > rightNum && leftPressed) || rightNum > leftNum && !leftPressed) {
            score++;
            message = "Correct!";
        }
        else {
            score--;
            message = "Less Correct!";
        }
        randomizeAndUpdateDisplay();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
