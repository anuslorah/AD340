package slorah.com.homework2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Interesting";
    private static final int RESULT_ID = 1;
    public static final String MESSAGE_ID = "my.message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "Main activity created");
        Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }

    public void onClick(View button) { //can call it whatever, doesn't have to be onclick

        Log.i(TAG, "Main activity button " + button.getId() + " clicked");

        EditText textBox = (EditText) findViewById(R.id.textEditor);
        String mymessage = textBox.getText().toString();
        textBox.getText().clear();

        Intent intent = new Intent(this, ActivityTwo.class);
        intent.putExtra(MESSAGE_ID, mymessage);
        startActivityForResult(intent, RESULT_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent content){
        if(requestCode == RESULT_ID){
            if(resultCode == RESULT_OK){
                TextView label = (TextView)findViewById(R.id.response);
                String  mymessage = label.getText().toString();
                mymessage = content.getStringExtra(ActivityTwo.RESULT);
                label.setText(mymessage);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "Main activity starting");
        //Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "Main activity resuming");
        //Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "Main activity Paused");
        //Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "Main activity stopped");
        //Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Main activity destroyed");
        //Log.i(TAG, this.getLifecycle().getCurrentState().toString());
    }
}

