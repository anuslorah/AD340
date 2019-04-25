package slorah.com.homework3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

// based on video example
public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder> {

    private Listener listener;

    public void setListener(Listener listener) {

        this.listener = listener;
    }

    interface Listener {
        void onClick(int position);
    }

    private ZombieMovies[] movies;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView layout;

        public ViewHolder(CardView v) {
            super(v);
            layout = v;
        }
    }

    public MovieRecyclerAdapter(ZombieMovies[] movies) {

        this.movies = movies;
    }

    @Override
    public int getItemCount() {

        return movies.length;
    }

    @Override
    public MovieRecyclerAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_card, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CardView cardView = holder.layout;
        TextView title = cardView.findViewById(R.id.title);
        TextView year = cardView.findViewById(R.id.year);

        Context context = cardView.getContext();

        ZombieMovies movie = movies[position];
        title.setText(movie.getTitle());

        year.setText(Integer.toString(movies[position].getYear()));

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }
}
