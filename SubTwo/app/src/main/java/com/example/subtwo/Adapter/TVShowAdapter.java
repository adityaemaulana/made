package com.example.subtwo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.subtwo.Model.TVShow;
import com.example.subtwo.R;

import java.util.ArrayList;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.ShowVH> {
    private ArrayList<TVShow> showList;
    private Context context;
    private OnItemMovieClick onItemMovieClick;

    public TVShowAdapter(Context context) {
        this.context = context;
    }

    public void setMovies(ArrayList<TVShow> showList) {
        this.showList = showList;
    }

    public void setOnItemClickListener(OnItemMovieClick onItemMovieClick) {
        this.onItemMovieClick = onItemMovieClick;
    }

    @NonNull
    @Override
    public ShowVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tv, viewGroup, false);
        return new ShowVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShowVH showVH, int i) {
        TVShow m = showList.get(i);

        showVH.tvTitle.setText(m.getTitle());
        showVH.tvDescription.setText(m.getDescription());
        showVH.tvGenre.setText(m.getGenre());

        Glide.with(context).load(m.getImage()).into(showVH.ivMovie);

        showVH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemMovieClick.onItemClicked(showVH.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return showList.size();
    }

    public class ShowVH extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDescription, tvGenre;
        private ImageView ivMovie;

        public ShowVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_show_title);
            tvDescription = itemView.findViewById(R.id.tv_show_description);
            tvGenre = itemView.findViewById(R.id.tv_show_genre);
            ivMovie = itemView.findViewById(R.id.iv_show);
        }
    }

    public interface OnItemMovieClick {
        void onItemClicked(int position);
    }
}
