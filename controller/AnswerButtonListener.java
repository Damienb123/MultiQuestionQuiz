package controller;

import android.view.View;
public class AnswerButtonListener implements View.OnClickListener {

    boolean answer;
    MainController mainController;
    public AnswerButtonListener(boolean answer, MainController mainController) {
        this.answer = answer;
        this.mainController = mainController;
    }
    @Override
    public void onClick(View view) {
        mainController.answerSelected(answer);
    }

}
