package slorah.com.hw7;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TrafficCams {

    private final static String TAG = "TRAFFICCAMS";
    private String label;
    private String imageUrl;
    private Double latitude;
    private Double longitude;

    public TrafficCams(String label) {
        this.label = label;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLabel() {
        return label;
    }

    public Double getLatitude() { return  latitude; }

    public Double getLongitude() { return longitude; }

    public static class Builder {
        TrafficCams cam;

        public Builder() {
            cam = new TrafficCams("");
        }

        public Builder label(String label) {
            cam.label = label;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            cam.imageUrl = imageUrl;
            return this;
        }

        public Builder latitude(Double latitude) {
            cam.latitude = latitude;
            return this;
        }

        public Builder longitude(Double longitude) {
            cam.longitude = longitude;
            return this;
        }

        public TrafficCams build() {
            return cam;
        }
    }

    public static TrafficCams[] initCams(String s) {

        ArrayList<TrafficCams> camArray = new ArrayList<>();
        String imgUrl = "";
        String imgUrlFull = "";
        String description = "";
        Double latitude = 0.0;
        Double longitude = 0.0;
        String type = "";
        String sdot = "http://www.seattle.gov/trafficcams/images/";
        String wsdot = "http://images.wsdot.wa.gov/nw/";

        try {
            //create object
            JSONObject rootObject = new JSONObject(s);
            JSONArray results = rootObject.getJSONArray("Features");

            for (int i=0; i < results.length(); i++) {
                //JSONArray res = results.getJSONArray(i);
                JSONObject firstObject = results.getJSONObject(i);

                JSONArray pointCoordinates = firstObject.getJSONArray("PointCoordinate");
                latitude = pointCoordinates.getDouble(0);
                longitude = pointCoordinates.getDouble(1);

                JSONArray cameras = firstObject.getJSONArray("Cameras");
                JSONObject firstResult2 = cameras.getJSONObject(0);
                imgUrl = firstResult2.getString("ImageUrl");
                description = firstResult2.getString("Description");
                type = firstResult2.getString("Type");
                Log.d(TAG, Double.toString(latitude));
                Log.d(TAG, Double.toString(longitude));


                if (type.equalsIgnoreCase("wsdot")){
                    imgUrlFull = wsdot + imgUrl;
                    //Log.e(TAG, imgUrlFull);
                } else {
                    imgUrlFull = sdot + imgUrl;
                    //Log.e(TAG, imgUrlFull);
                }

                camArray.add(new TrafficCams.Builder().label(description)
                        .imageUrl(imgUrlFull)
                        .latitude(latitude)
                        .longitude(longitude)
                        .build());
            }

        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
        }

        TrafficCams[] cams = new TrafficCams[camArray.size()];
        cams = camArray.toArray(cams);
        return cams;
    }
}