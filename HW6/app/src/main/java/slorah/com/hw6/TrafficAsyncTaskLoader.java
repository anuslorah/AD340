package slorah.com.hw6;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class TrafficAsyncTaskLoader extends AsyncTaskLoader<String> {

    private String queryString;

    public TrafficAsyncTaskLoader(Context context, String queryString) {
        super(context);
        this.queryString = queryString;
    }

    //starts networkoperations
    @Nullable
    @Override
    public String loadInBackground() {
        String baseURL = "https://web6.seattle.gov/Travelers/api/Map/Data";
        return NetWorkConnection.getData(baseURL, "zoomId", queryString, "type", "2");
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
