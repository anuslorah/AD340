package slorah.com.hw7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import slorah.com.hw7.R;

import static slorah.com.hw7.MainActivity.MESSAGE_ID;

public class RecyclerViewActivity extends Activity {

    final static String TAG = "RECYCLERVIEW";
    private TrafficCams[] cams;
    public String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        Intent intent = getIntent();
        String data = intent.getStringExtra(MESSAGE_ID);
        //Log.e(TAG, data);

        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        this.cams = TrafficCams.initCams(data);
        CameraRecyclerAdapter adapter = new CameraRecyclerAdapter(cams);
        recyclerView.setAdapter(adapter);
    }
}

