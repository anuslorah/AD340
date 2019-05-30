package slorah.com.hw7;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<String> {

    private TextView results;
    private ImageView cameraImage;
    public static final String MESSAGE_ID = "my.message";
    public String s;
    private final static String TAG = "MAINACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton = findViewById(R.id.button_get_data);
        myButton.setOnClickListener(this);
        results = findViewById(R.id.label_result);
        cameraImage = findViewById(R.id.test_image);
    }

    @Override
    public void onClick(View view){

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        if (info != null && info.isConnected()) {
            results.setText(getResources().getString(R.string.waiting));

            Bundle bundle = new Bundle();
            bundle.putString("queryString", "13");
            getSupportLoaderManager().restartLoader(0, bundle, this);
        } else {
            results.setText(getResources().getString(R.string.no_connection));
        }
    }

    //starts work in activity
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        String queryString = "";
        if (bundle != null) {
            queryString = bundle.getString("queryString");
        }

        //Log.e(TAG, queryString);
        //launchCams(queryString);
        return new TrafficAsyncTaskLoader(this, queryString);
    }

    //does work
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {

        Log.e(TAG, s);
        launchCams(s);
       //see json
        //results.setText(s);
//  //view one image
//        String imgUrl = "";
//        String imgUrlFull = "";
//
//        String description = "";
//        String type = "";
//        String sdot = "http://www.seattle.gov/trafficcams/images/";
//        String wsdot = "http://images.wsdot.wa.gov/nw/";
//
//        try{
//            //create object
//            JSONObject rootObject = new JSONObject(s);
//            JSONArray results = rootObject.getJSONArray("Features");
//            JSONObject firstResult = results.getJSONObject(0);
//            JSONArray cameras =  firstResult.getJSONArray("Cameras");
//            JSONObject firstResult2 = cameras.getJSONObject(0);
//            imgUrl = firstResult2.getString("ImageUrl");
//            description = firstResult2.getString("Description");
//            type = firstResult2.getString("Type");
//
//            if (type == "wsdot") {
//                imgUrlFull = wsdot + imgUrl;
//            } else {
//                imgUrlFull = sdot + imgUrl;
//            }
//
//
//        }catch (Exception e){
//            Log.e(TAG, e.getLocalizedMessage());
//        }
//
//        results.setText(description);
//        Picasso.get().load(imgUrlFull).into(cameraImage);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
    }

    public void launchCams(String query) {

        Log.d(TAG, "Button clicked!");
        Intent intent = new Intent(this, RecyclerViewActivity.class);
        intent.putExtra(MESSAGE_ID, query);
        startActivity(intent);
    }
}