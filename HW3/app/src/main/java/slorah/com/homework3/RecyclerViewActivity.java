package slorah.com.homework3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

//based on video example
public class RecyclerViewActivity extends Activity {

    final static String TAG = "RecyclerView Anu";
    public static final int MOVIE_POS = 0;
    private static final int RESULT_ID = 1;
    private ZombieMovies[] movie;
    public String send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        this.movie = ZombieMovies.initMovies();
        MovieRecyclerAdapter adapter = new MovieRecyclerAdapter(movie);
        recyclerView.setAdapter(adapter);

        adapter.setListener(new MovieRecyclerAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Log.i(TAG, "Clicked " + position + " " + movie[position].getTitle());
                send = Integer.toString(position);
                send();
            }
        });
    }

    public void send() {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("MOVIE_POS", send);
        startActivity(intent);
    }
}
