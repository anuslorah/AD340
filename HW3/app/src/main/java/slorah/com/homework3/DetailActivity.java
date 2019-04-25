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

        setContentView(R.layout.recycler_card_detail);

        TextView title1 = findViewById(R.id.title);
        title1.setText( movie[position].getTitle()));

        TextView year = findViewById(R.id.year);
        year.setText(Integer.toString(movie[position].getYear()));

        TextView director = (TextView)findViewById(R.id.director);
        director.setText(movie[position].getDirector());

        TextView description = (TextView)findViewById(R.id.description);
        description.setText(movie[position].getDescription());


    }
}

