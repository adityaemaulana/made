package com.example.subfour.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.subfour.R;
import com.example.subfour.model.Show;

import java.util.ArrayList;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ShowVH> {
    private ArrayList<Show> showList = new ArrayList<>();
    private Context context;
    private OnItemShowClick onItemShowClick;

    public ShowAdapter(Context context) {
        this.context = context;
    }

    public void setShows(ArrayList<Show> shows) {
        showList.clear();
        showList.addAll(shows);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemShowClick onItemShowClick) {
        this.onItemShowClick = onItemShowClick;
    }

    @NonNull
    @Override
    public ShowVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_show, viewGroup, false);
        return new ShowVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShowVH showVH, int i) {
        Show m = showList.get(i);

        showVH.tvTitle.setText(m.getTitle());
        showVH.tvDescription.setText(m.getDescription());

        Glide.with(context).load(m.getImage()).into(showVH.ivMovie);

        showVH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemShowClick.onItemClicked(showVH.getAdapterPosition());
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

    public interface OnItemShowClick {
        void onItemClicked(int position);
    }
}
