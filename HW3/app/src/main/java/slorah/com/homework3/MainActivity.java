package slorah.com.homework3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
            Toast toast = Toast.makeText(getApplicationContext(), "Look, a yellow dandelion!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 100);
            LinearLayout toastContentView = (LinearLayout) toast.getView();
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageResource(R.drawable.dandelion);
            toastContentView.addView(imageView, 0);
            toast.show();
            return;
        }
        // styled toast
        if (view.getId() == R.id.button3) {
            StyleableToast.makeText(getApplicationContext(),"Button three", Toast.LENGTH_LONG, R.style.fancyToast).show();
            return;
        }
        // regular toast
        if (view.getId() == R.id.button4) {
            Toast.makeText(getApplicationContext(),"Button four",Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
