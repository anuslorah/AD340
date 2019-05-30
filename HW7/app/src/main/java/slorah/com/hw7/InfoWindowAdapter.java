package slorah.com.hw7;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;

public class InfoWindowAdapter extends Activity implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public InfoWindowAdapter(Context context) {
        context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {

        Intent intent = getIntent();
        String[] myCam = intent.getStringArrayExtra(RecyclerViewActivity.RESULT);
        Double camLatitude= Double.parseDouble(myCam[0]);
        Double camLongitude = Double.parseDouble(myCam[1]);
        String camLabel = myCam[2];
        String camUrl = myCam[3];

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.cam_info, null);

        TextView camInfo = (TextView) view.findViewById(R.id.camera_name);
        camInfo.setText(camLabel);

        ImageView camImage = (ImageView) view.findViewById(R.id.camera_image);
        Picasso.get().load(camUrl).into(camImage);
        return view;
    }
}