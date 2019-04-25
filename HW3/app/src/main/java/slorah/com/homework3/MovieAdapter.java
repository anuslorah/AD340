package slorah.com.homework3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

//based on video example
public class MovieAdapter extends ArrayAdapter<ZombieMovies> {

    private final Context context;
    private final ZombieMovies[] movies;

    public MovieAdapter(Context context, ZombieMovies[] values) {
        super(context, -1, values);
        this.context = context;
        this.movies = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView title = new TextView(context);
        title.setText(movies[position].getTitle());

        TextView year = new TextView(context);
        year.setText(Integer.toString(movies[position].getYear()));

        layout.addView(title);
        layout.addView(year);
        return layout;
    }
}
