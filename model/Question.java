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
