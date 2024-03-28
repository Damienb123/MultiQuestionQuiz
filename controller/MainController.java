package controller;

import com.multiquestionquiz.R;
import model.Question;
import model.QuestionBank;
import view.View;


public class MainController {

    View view;
    QuestionBank questionBank;
    boolean correctAnswer;
    public MainController() {
        questionBank = new QuestionBank();
    }
    public void setView(View view) {
        this.view = view;
    }
    public void answerSelected(boolean answer) {
        if (answer == correctAnswer) {
            view.popUpToast(R.string.correct);
        } else {
            view.popUpToast(R.string.incorrect);
        }
    }
    public void nextQuestion() {
        Question q = questionBank.nextQuestion();
        view.setQuestionText(q.getTextResId());
        correctAnswer = q.answerIsTrue();
    }
}
