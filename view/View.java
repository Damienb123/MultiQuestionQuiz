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
