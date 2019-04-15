package slorah.com.homework2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityTwo extends Activity {

    private static final String TAG = "Interesting";
    public static final String RESULT = "my.response";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "Second activity created");

        Intent intent = getIntent();
        String myMessage = intent.getStringExtra((MainActivity.MESSAGE_ID));

        setContentView(R.layout.activity_two);

        TextView label = (TextView)findViewById(R.id.second_activity_text);
        label.setText(myMessage);
    }

    public void onClick(View view) { //can call it whatever, doesn't have to be onclick

        Log.i(TAG, "Second activity button clicked");

        EditText textBox = (EditText) findViewById(R.id.response);
        String message = textBox.getText().toString();
        textBox.getText().clear();


        Intent responseIntent = new Intent();
        responseIntent.putExtra(ActivityTwo.RESULT, message);

        setResult(Activity.RESULT_OK, responseIntent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "Second activity starting");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "Second activity resuming");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "Second activity Paused");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "Second activity stopped");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Second activity destroyed");
    }
}
