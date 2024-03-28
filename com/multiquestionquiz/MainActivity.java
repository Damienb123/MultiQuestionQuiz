package com.multiquestionquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import view.View;

import controller.AnswerButtonListener;
import controller.MainController;
import controller.NextQuestionButtonListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainController mainController = new MainController();
        View view = new View(this);

        mainController.setView(view);

        Button trueButton = findViewById(R.id.true_button);
        Button falseButton = findViewById(R.id.false_button);
        Button nextQuestionButton = findViewById(R.id.next_question_button);

        trueButton.setOnClickListener(new AnswerButtonListener(true, mainController));
        falseButton.setOnClickListener(new AnswerButtonListener(false, mainController));
        nextQuestionButton.setOnClickListener(new NextQuestionButtonListener(mainController));

        TextView questionTextView = findViewById(R.id.question_text_view);
        view.setQuestionTextView(questionTextView);

        mainController.nextQuestion();
    }
}