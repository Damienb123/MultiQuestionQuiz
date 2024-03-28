package model;

import com.multiquestionquiz.R;
public class QuestionBank {
    private final Question[] questions =
            new Question[] {
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
