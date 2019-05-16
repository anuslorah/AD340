package slorah.com.hw5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private static final String TAG = "LOOKIE ...";
    private String gramString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.gram_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.gram_choice,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // First item is disabled and it is used for hint
        if(position > 0){
            // Showing selected spinner item
            //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            gramString = item;
        } else {
            gramString = "0";
            //Toast.makeText(parent.getContext(), "Selected: " + gramString, Toast.LENGTH_LONG).show();
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        //Log.i(TAG, "Nothing selected in spinner ...");
    }

    @Override
    public void onClick(View view) {

        TextView gaugeOutput = (TextView) findViewById(R.id.gaugeText);
        int grams;

        //get grams
        if (gramString == "choose skein weight") {
            gaugeOutput.setText("Please select skein weight.");
            grams = 0;
        } else {
            grams = Integer.parseInt(gramString);
        }

        //get yards
        EditText yardInput = (EditText) findViewById(R.id.my_yards);
        String yardString = yardInput.getText().toString();;
        int yards;
        if (yardString.isEmpty()) {
            gaugeOutput.setText("Please select skein length.");
        } else {
            //find gauge
            yardString = yardInput.getText().toString();
            yards = Integer.parseInt(yardString);
            Yarn newYarn = new Yarn(yards, grams);
            String gauge = newYarn.findGauge(yards, grams);
            gaugeOutput.setText(gauge);
        }
    }//end onClick
}
