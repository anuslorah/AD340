package slorah.com.hw6;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CameraAdapter extends ArrayAdapter<TrafficCams> {

        private final Context context;
        private final TrafficCams[] cams;

        public CameraAdapter(Context context, TrafficCams[] values) {
            super(context, -1, values);
            this.context = context;
            this.cams = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);

            TextView label = new TextView(context);
            label.setText(cams[position].getLabel());

            TextView url = new TextView(context);
            url.setText(cams[position].getImageUrl());

            layout.addView(label);
            layout.addView(url);
            return layout;
        }
    }
