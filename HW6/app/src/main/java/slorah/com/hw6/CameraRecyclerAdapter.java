package slorah.com.hw6;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class CameraRecyclerAdapter extends RecyclerView.Adapter<CameraRecyclerAdapter.ViewHolder> {

        private Listener listener;

        public void setListener(Listener listener) {
            this.listener = listener;
        }

        interface Listener {
            void onClick(int position);
        }

        private TrafficCams[] cams;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            private CardView layout;

            public ViewHolder(CardView v) {
                super(v);
                layout = v;
            }
        }

        public CameraRecyclerAdapter(TrafficCams[] cams) {
            this.cams = cams;
        }

        @Override
        public int getItemCount() {
            return cams.length;
        }

        @Override
        public CameraRecyclerAdapter.ViewHolder onCreateViewHolder(
                ViewGroup parent, int viewType) {
            CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_card, parent, false);
            return new ViewHolder(cardView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            CardView cardView = holder.layout;
            TextView label = cardView.findViewById(R.id.label);
            ImageView pic = cardView.findViewById(R.id.camera_pic);

            TrafficCams cam = cams[position];
            label.setText(cam.getLabel());

            Picasso.get().load(cams[position].getImageUrl()).into(pic);

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
