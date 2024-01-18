package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizPage extends AppCompatActivity implements View.OnClickListener{

    TextView questionTextView,scoreTxt,quesNoTxt;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int score=0;
    int totalQuestion = QuestionAnswer1.question.length;
    int currentQuestionIndex = 0;
    int topic;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);

        topic = getIntent().getIntExtra("topic", 0);
        questionTextView = findViewById(R.id.textQues);
        scoreTxt = findViewById(R.id.textScore);
        quesNoTxt = findViewById(R.id.textQuesNo);
        ansA = findViewById(R.id.buttonAns1);
        ansB = findViewById(R.id.buttonAns2);
        ansC = findViewById(R.id.buttonAns3);
        ansD = findViewById(R.id.buttonAns4);
        submitBtn = findViewById(R.id.btnSubmit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        loadNewQuestion();

    }


    @Override
    public void onClick(View view) {
        ansA.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.nord2)));
        ansB.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.nord2)));
        ansC.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.nord2)));
        ansD.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.nord2)));

        ansA.setTextColor(getResources().getColor(R.color.white));
        ansB.setTextColor(getResources().getColor(R.color.white));
        ansC.setTextColor(getResources().getColor(R.color.white));
        ansD.setTextColor(getResources().getColor(R.color.white));


        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.btnSubmit && topic==1){
            if(selectedAnswer.equals(QuestionAnswer1.correctAnswers[currentQuestionIndex])){
                score++;
                scoreTxt.setText("Score : " + String.valueOf(score));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            currentQuestionIndex++;
            loadNewQuestion();
        }
        else if (clickedButton.getId()==R.id.btnSubmit && topic==2) {
            if(selectedAnswer.equals(QuestionAnswer2.correctAnswers[currentQuestionIndex])){
                score++;
                scoreTxt.setText("Score : " + String.valueOf(score));
            }
            currentQuestionIndex++;
            loadNewQuestion();
        }
        else if (clickedButton.getId()==R.id.btnSubmit && topic==3) {
            if(selectedAnswer.equals(QuestionAnswer3.correctAnswers[currentQuestionIndex])){
                score++;
                scoreTxt.setText("Score : " + String.valueOf(score));
            }
            currentQuestionIndex++;
            loadNewQuestion();
        }
        else{
            selectedAnswer  = clickedButton.getText().toString();
            clickedButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.nord_yellow)));
            clickedButton.setTextColor(getResources().getColor(R.color.black));
        }
    }

    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion ){
            finishQuiz();
            return;
        }

        quesNoTxt.setText(String.valueOf(currentQuestionIndex+1)+"/5");
        
        if(topic==1){
            questionTextView.setText(QuestionAnswer1.question[currentQuestionIndex]);
            ansA.setText(QuestionAnswer1.choices[currentQuestionIndex][0]);
            ansB.setText(QuestionAnswer1.choices[currentQuestionIndex][1]);
            ansC.setText(QuestionAnswer1.choices[currentQuestionIndex][2]);
            ansD.setText(QuestionAnswer1.choices[currentQuestionIndex][3]);
        }
        else if (topic==2) {
            questionTextView.setText(QuestionAnswer2.question[currentQuestionIndex]);
            ansA.setText(QuestionAnswer2.choices[currentQuestionIndex][0]);
            ansB.setText(QuestionAnswer2.choices[currentQuestionIndex][1]);
            ansC.setText(QuestionAnswer2.choices[currentQuestionIndex][2]);
            ansD.setText(QuestionAnswer2.choices[currentQuestionIndex][3]);
        }
        else if (topic==3) {
            questionTextView.setText(QuestionAnswer3.question[currentQuestionIndex]);
            ansA.setText(QuestionAnswer3.choices[currentQuestionIndex][0]);
            ansB.setText(QuestionAnswer3.choices[currentQuestionIndex][1]);
            ansC.setText(QuestionAnswer3.choices[currentQuestionIndex][2]);
            ansD.setText(QuestionAnswer3.choices[currentQuestionIndex][3]);
        }

    }

    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestion*0.60){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+" out of "+ totalQuestion)
                .setNegativeButton("Restart",(dialogInterface, i) -> restartQuiz() )
                .setPositiveButton("Quit",(dialogInterface, i) -> quitQuiz())
                .setCancelable(false)
                .show();
    }

    void quitQuiz(){
        Intent intent = new Intent(QuizPage.this,MainActivity.class);
        startActivity(intent);
    }
    void restartQuiz()
    {
        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion();
    }

}