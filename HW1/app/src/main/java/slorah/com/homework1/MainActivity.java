package slorah.com.homework1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView myText = new TextView(this);
        myText.setText("My name is Anu Slorah. \nI am front end developer \nright now I am most excited about Python.");
        setContentView(myText);
    }
}
