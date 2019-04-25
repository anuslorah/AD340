package slorah.com.homework3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity Anu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchSecondActivity(View view) {
        Log.d(TAG, "Button clicked!");
        Intent intent = new Intent(this, RecyclerViewActivity.class);
        startActivity(intent);
    }

    public void onClick(View view) {
        Log.i(TAG, view.getClass().getSimpleName());

        if (view.getId() == R.id.button1) {
            launchSecondActivity(view);
            return;
        }
        // show image in toast
        if (view.getId() == R.id.button2) {
            Toast.makeText(getApplicationContext(), "Button two", Toast.LENGTH_SHORT).show();
            return;
        }
        // styled toast
        if (view.getId() == R.id.button3) {
            StyleableToast.makeText(getApplicationContext(), "Button three, custom toast", Toast.LENGTH_SHORT, R.style.fancyToast).show();
            return;
        }
        // regular toast
        if (view.getId() == R.id.button4) {
            Toast.makeText(getApplicationContext(), "Button four", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
