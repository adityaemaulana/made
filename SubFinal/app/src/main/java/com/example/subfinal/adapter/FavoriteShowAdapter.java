package com.example.subfinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.subfinal.R;
import com.example.subfinal.model.Show;

import java.util.ArrayList;

public class FavoriteShowAdapter extends RecyclerView.Adapter<FavoriteShowAdapter.ShowVH> {

    private ArrayList<Show> showList = new ArrayList<>();
    private Context context;
    private OnItemFavoriteShowClick onItemFavoriteShowClick;

    public FavoriteShowAdapter(Context context) {
        this.context = context;
    }

    public void setFavoriteShows(ArrayList<Show> shows) {
        if (showList.size() > 0)
            showList.clear();

        showList.addAll(shows);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemFavoriteShowClick onItemFavoriteShowClick) {
        this.onItemFavoriteShowClick = onItemFavoriteShowClick;
    }

    @NonNull
    @Override
    public ShowVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_show, viewGroup, false);
        return new ShowVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShowVH showVH, int i) {
        Show s = showList.get(i);

        showVH.tvTitle.setText(s.getTitle());
        showVH.tvDescription.setText(s.getDescription());

        Glide.with(context).load(s.getImage()).into(showVH.ivMovie);

        showVH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemFavoriteShowClick.onItemClicked(showVH.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return showList.size();
    }

    public class ShowVH extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDescription;
        private ImageView ivMovie;

        public ShowVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_show_title);
            tvDescription = itemView.findViewById(R.id.tv_show_description);
            ivMovie = itemView.findViewById(R.id.iv_show);
        }
    }

    public interface OnItemFavoriteShowClick {
        void onItemClicked(int position);
    }
}