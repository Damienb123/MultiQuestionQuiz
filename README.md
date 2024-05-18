# MultiQuestionQuiz

## Table of Contents
1. [Overview](#Overview)
2. [Main Features](#Main-Function)
3. [Installation](#Installation)
4. [Usage](#Usage)
5. [App Evaluation](#App-Evaluation)
6. [Product Specification](#Product-Specification)
   - User Stories
   - Screen Archetypes
   - Screen Navigation
7. [Schema](#Schema)
   - Models
8. [Code Struture](#Code-Structure)
    - MainActivity.java
    - AnswerButtonListner.java
    - MainController.java
    - NextQuestionButtonListner.java
    - Question.java
    - QuestionBank.java
    - View.java
    - activity_main.xml
    - colors.xml
    - strings.xml
    - themes.xml
9. [Video Walkthrough](#Video-Walkthrough)

## Overview
The MultiQuestionQuiz App is an Android application that presents a series of true/false questions to the user. The user can answer the questions and navigate through them using the provided buttons. The app provides immediate feedback on whether the selected answer is correct or incorrect.

## Main Features
- Display a series of questions to the user.
- Allow the user to answer each question as true or false.
- Provide immediate feedback on the correctness of the selected answer.
- Allow the user to proceed to the next question.
Display a toast message indicating whether the answer was correct or incorrect.

## Installation
- Clone the repository or download the source code files.
- Open the project in Android Studio.
- Build and run the project on an Android emulator or a physical device.

## Usage
- Launch the app on your device.
- Read the displayed question.
- Select either "True" or "False" to answer the question.
- Receive immediate feedback through a toast message.
- Click "Next" to proceed to the next question.

## App Evaluation
- **Category:** Education
- **Mobile:** Mobile is essential for active learning wherever you are to maintain soft skills of any level.
- **Story:** Aims to provide an engaging and educational experience for users by presenting a series of true or false questions on various topics.
- **Market:** High school students, College students, or any adult 21 and up.
- **Habit:** Students are needing a desire to require active learning that is interactive, efficient, and simple to understand.
- **Scope:** Providing a user-friendly experience, present a diverse range of questions, offer immediate feedback, support a seamless transition between questions, and enhancing user engagement through interactive features.
## Product Specification
## 1. User Stories

### Viewing Questions
**Required Must-have Stories**
- [X] User has the ability to see the displayed questions on the screen.
- [X] User can read the provided questions on the screen to answer them.

### Answering Questions 
- [X] User has the provided options to select to answer the following displayed questions.
which are "True" or "False".
- [X] User can provide responses, see if it's correct or wrong, and move on to next question.

### Receiving Feedback
- [X] User can receive immediate feedback prior to answering the displayed questions on the screen.
- [X] User can see if the following questions answered are shown as correct or incorrect.

### Navigating to Next Question
- [X] User can proceed to the next question after answering the current displayed question from the screen.
- [X] User wants to be able to move on to each question after answering or go back to retry.

### 2. Screen Achetypes
1. Question Screen
- Description: Displays a question with "True" and "False" buttons for the user to select their answer.
- Components:
    - TextView: Displays the current question.
    - Button: "True" option.
    - Button: "False" option.
    - Button: "Next" option to proceed to the next question.

### 3. Screen Navigation:
Initial Launch
- Destination: Question Screen
- Action: When the app is launched, the user is presented with the first question on the Question Screen.

Answering a Question
- Current Screen: Question Screen
- Action: The user selects either the "True" or "False" button.
- Destination: The same Question Screen, displaying a toast message indicating if the answer was correct or incorrect.

Proceeding to Next Question
- Current Screen: Question Screen
- Action: The user clicks the "Next" button.
- Destination: The Question Screen updates to display the next question in the sequence.

## Schema
### Models

Question
| Property	| Type	| Description |
| --- | --- | --- |
| textResid | Integer | Resource ID of the question text |
| answer | Boolean | True if th answer is true, false otherwise |

QuestionBank
| Property	| Type	| Description |
| --- | --- | --- |
| questions | Array | List of 'Question' objects |
| questionIndex | Integer | Index of the current question |

MainController
| Property	| Type	| Description |
| --- | --- | --- |
| view | View | The 'View' associated with the controller |
| questionBank | QuestionBank | The 'QuestionBank' used in the controller |
| correctAnswer | Boolean | True if the correct answer is correct |

View
| Property	| Type	| Description |
| --- | --- | --- |
| mainActivity | MainActivity | The main activity of the app |
| questionTextView | TextView | The TextView displaying the question |
    
## Code Structure
MainActivity.java
- This is the main activity that initializes the app's interface and sets up the controllers for handling user interactions.
```
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

```

AnswerButtonListener.java
- Handles the logic when the user selects an answer.
```
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

```

MainController.java
- Manages the app's main logic, including navigating through questions and checking answers.
```
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

```

NextQuestionButtonListener.java
- Handles the logic when the user clicks the "Next" button to proceed to the next question.
```
package controller;

import android.view.View;

public class NextQuestionButtonListener implements View.OnClickListener {

    MainController mainController;

    public NextQuestionButtonListener(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void onClick(View view) {
        mainController.nextQuestion();
    }
}

```

Question.java
- Represents a question with its text resource ID and the correct answer.
```
package model;

public class Question {
    int textResId;
    boolean answer;

    public Question(int textResId, boolean answer) {
        this.textResId = textResId;
        this.answer = answer;
    }

    public int getTextResId() {
        return textResId;
    }

    public boolean answerIsTrue() {
        return answer;
    }
}

```

QuestionBank.java
- Holds a collection of questions and provides the next question in the sequence.
```
package model;

import com.multiquestionquiz.R;

public class QuestionBank {
    private final Question[] questions = new Question[] {
        new Question(R.string.question_australia, true),
        new Question(R.string.question_oceans, true),
        new Question(R.string.question_mideast, false),
        new Question(R.string.question_africa, false),
        new Question(R.string.question_americas, true),
        new Question(R.string.question_asia, true)
    };
    private int questionIndex = 0;

    public Question nextQuestion() {
        Question question = questions[questionIndex];
        questionIndex = (questionIndex + 1) % questions.length;
        return question;
    }
}

```

View.java
- Handles the view logic, including setting the question text and showing toast messages.
```
package view;

import android.widget.TextView;
import android.widget.Toast;
import com.multiquestionquiz.MainActivity;

public class View {
    MainActivity mainActivity;
    TextView questionTextView;

    public View(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setQuestionTextView(TextView questionTextView) {
        this.questionTextView = questionTextView;
    }

    public void setQuestionText(int resid) {
        questionTextView.setText(resid);
    }

    public void popUpToast(int resid) {
        Toast t = Toast.makeText(mainActivity, resid, Toast.LENGTH_SHORT);
        t.show();
    }
}

```

activity_main.xml
- Defines the layout of the main activity, including the question text view and the buttons.
```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center"
        android:orientation="vertical">
        <TextView
            android:id="@+id/question_text_view"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:padding="24dp" />

        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:orientation="horizontal">
            <Button
                android:id="@+id/true_button"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="@string/true_label" />
            <Button
                android:id="@+id/false_button"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="@string/false_label" />
        </LinearLayout>
        <Button
            android:id="@+id/next_question_button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/next_question_label" />
    </LinearLayout>
</androidx.constraintlayout.widget.ConstraintLayout>

```

colors.xml
- Defines the color resources used in the app.
```
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="black">#FF000000</color>
    <color name="white">#FFFFFFFF</color>
</resources>
```

strings.xml
- Defines the string resources used in the app, including questions and button labels.
```
<resources>
    <string name="app_name">MultiQuestionQuiz</string>
    <string name="question_australia">Canberra is the capital of Australia.</string>
    <string name="question_oceans">The Pacific Ocean is larger than the Atlantic Ocean.</string>
    <string name="question_mideast">The Suez Canal connects the Mediterranean Sea and the Red Sea.</string>
    <string name="question_africa">The Sahara Desert is in Southern Africa.</string>
    <string name="question_americas">The Amazon River is the longest river in the Americas.</string>
    <string name="question_asia">Mount Everest is the highest mountain in Asia.</string>
    <string name="true_label">True</string>
    <string name="false_label">False</string>
    <string name="next_question_label">Next</string>
    <string name="correct">Correct!</string>
    <string name="incorrect">Incorrect.</string>
</resources>
```

themes.xml
- Defines the theme resources used in the app.
```
<resources xmlns:tools="http://schemas.android.com/tools">
    <style name="Theme.MultiQuestionQuiz" parent="Theme.MaterialComponents.DayNight.DarkActionBar">
        <item name="colorPrimary">@color/black</item>
        <item name="colorPrimaryVariant">@color/black</item>
        <item name="colorOnPrimary">@color/white</item>
        <item name="colorSecondary">@color/white</item>
        <item name="colorSecondaryVariant">@color/white</item>
        <item name="colorOnSecondary">@color/black</item>
        <item name="colorSurface">@color/black</item>
        <item name="colorOnSurface">@color/white</item>
        <item name="colorBackground">@color/white</item>
        <item name="colorOnBackground">@color/black</item>
        <item name="colorError">@color/black</item>
        <item name="colorOnError">@color/white</item>
        <item name="android:statusBarColor" tools:targetApi="l">@color/black</item>
        <item name="android:windowLightStatusBar" tools:targetApi="m">true</item>
    </style>
</resources>
```

## Video Walkthrough
