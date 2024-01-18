package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button CS, GK, RND;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CS = findViewById(R.id.buttonCS);
        GK = findViewById(R.id.buttonGK);
        RND = findViewById(R.id.buttonRND);

        CS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goto_quiz(1);
            }
        });

        GK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goto_quiz(2);
            }
        });

        RND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goto_quiz(3);
            }
        });
    }

    public void goto_quiz(int i){
        Intent intent = new Intent(MainActivity.this, QuizPage.class);
        intent.putExtra("topic", i);
        startActivity(intent);
    }
}