package com.example.favoritecatalogue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.favoritecatalogue.R;
import com.example.favoritecatalogue.model.Movie;

import java.util.ArrayList;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.MovieVH> {

    private ArrayList<Movie> movieList = new ArrayList<>();
    private Context context;
    private OnItemFavoriteMovieClick onItemFavoriteMovieClick;

    public FavoriteMovieAdapter(Context context) {
        this.context = context;
    }

    public void setFavoriteMovies(ArrayList<Movie> movies) {
        if (movieList.size() > 0)
            movieList.clear();

        movieList.addAll(movies);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemFavoriteMovieClick onItemFavoriteMovieClick) {
        this.onItemFavoriteMovieClick = onItemFavoriteMovieClick;
    }

    @NonNull
    @Override
    public MovieVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new MovieVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieVH movieVH, int i) {
        Movie m = movieList.get(i);

        movieVH.tvTitle.setText(m.getTitle());
        movieVH.tvDescription.setText(m.getDescription());

        Glide.with(context).load(m.getImage()).into(movieVH.ivMovie);

        movieVH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemFavoriteMovieClick.onItemClicked(movieVH.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieVH extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDescription;
        private ImageView ivMovie;

        public MovieVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            ivMovie = itemView.findViewById(R.id.iv_movie);
        }
    }

    public interface OnItemFavoriteMovieClick {
        void onItemClicked(int position);
    }
}
