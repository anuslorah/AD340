package slorah.com.homework3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
        //position = movie.indexOf(title);

        setContentView(R.layout.recycler_card_detail);

        TextView title1 = findViewById(R.id.title);
        title1.setText("POS: " + position);
        Log.i(TAG, "position" + position + " " + isInt(position));



//        TextView year = findViewById(R.id.year);
//        year.setText(movie[position].getYear());

//        TextView director = (TextView)findViewById(R.id.director);
//        year.setText(movie[position].getDirector());
//
//
//        TextView description = (TextView)findViewById(R.id.description);
//        year.setText(movie[position].getDescription());


    }
}

