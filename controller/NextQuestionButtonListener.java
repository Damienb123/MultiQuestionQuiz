package controller;

import android.view.View;

public class NextQuestionButtonListener implements View.OnClickListener {

    MainController mainController;

    public NextQuestionButtonListener (MainController mainController){
        this.mainController = mainController;

    }

    @Override
    public void onClick(View view){
        mainController.nextQuestion();
    }

}
