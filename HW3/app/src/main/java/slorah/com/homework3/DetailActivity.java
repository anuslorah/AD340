package slorah.com.homework3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

public class DetailActivity extends Activity {

    final static String TAG = "Detailctivity Anu";
    private ZombieMovies[] movie;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            position = Integer.parseInt(bundle.getString("MOVIE_POS"));
        }

        this.movie = ZombieMovies.initMovies();

        setContentView(R.layout.recycler_card_detail);

        TextView title1 = findViewById(R.id.title);
        title1.setText(movie[position].getTitle());
        //Log.i(TAG, movie[position].getTitle());

        TextView year1 = findViewById(R.id.year);
        year1.setText(Integer.toString(movie[position].getYear()));

        // from http://square.github.io/picasso/
        ImageView pic = (ImageView)findViewById(R.id.pic);
        Picasso.get().load(movie[position].getImageLink()).into(pic);

        TextView director = (TextView)findViewById(R.id.director);
        director.setText(movie[position].getDirector());

        TextView description = (TextView)findViewById(R.id.description);
        description.setText(movie[position].getDescription());
    }
}

