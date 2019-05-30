package slorah.com.hw7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import slorah.com.hw7.R;

import static slorah.com.hw7.MainActivity.MESSAGE_ID;

public class RecyclerViewActivity extends Activity {

    final static String TAG = "RECYCLERVIEW";
    private TrafficCams[] cams;
    public String data;
    final static String RESULT = "RECYCLERVIEW RESPONSE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        Intent intent = getIntent();
        String data = intent.getStringExtra(MESSAGE_ID);
        Log.e(TAG, data);

        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        this.cams = TrafficCams.initCams(data);
        CameraRecyclerAdapter adapter = new CameraRecyclerAdapter(cams);
        recyclerView.setAdapter(adapter);

        adapter.setListener(new CameraRecyclerAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Log.i(TAG, "Clicked " + position + " " + cams[position].getImageUrl());
                Intent intent = new Intent(getApplicationContext(),
                        MapActivity.class);
                String[] camPackage = new String[4];
                camPackage[0] = Double.toString(cams[position].getLatitude());
                camPackage[1] = Double.toString(cams[position].getLongitude());
                camPackage[2] = cams[position].getLabel();
                camPackage[3] = cams[position].getImageUrl();
                intent.putExtra(RESULT, camPackage);
                startActivity(intent);
            }
        });
    }
}
/*
    Bundle bundle = new Bundle();
    bundle.putDouble("KEY_LAT", cams[position].getLatitude());
    bundle.putDouble("KEY_LNG", cams[position].getLongitude());
    */