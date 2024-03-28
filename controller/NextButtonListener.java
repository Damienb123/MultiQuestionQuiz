package controller;

import android.view.View;
public class NextButtonListener implements View.OnClickListener {

    MainController mainController;
    public void NextQuestionButtonListener(MainController mainController) {
        this.mainController = mainController;
    }
    @Override
    public void onClick(View view) {
        mainController.nextQuestion();
    }

}
