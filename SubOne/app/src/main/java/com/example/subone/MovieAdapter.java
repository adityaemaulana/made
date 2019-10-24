package com.example.subone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Movie> movies;

    public MovieAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        }

        ViewHolder viewHolder = new ViewHolder(convertView);
        Movie movie = (Movie) getItem(position);
        viewHolder.bind(movie);

        return convertView;
    }

    private class ViewHolder {
        private TextView tvJudul, tvTanggal, tvDeskripsi;
        private ImageView ivPhoto;

        ViewHolder(View view) {
            tvJudul = view.findViewById(R.id.tv_judul);
            tvTanggal = view.findViewById(R.id.tv_tanggal);
            tvDeskripsi = view.findViewById(R.id.tv_deskripsi);
            ivPhoto = view.findViewById(R.id.iv_photo);
        }

        void bind(Movie movie) {
            tvJudul.setText(movie.getJudul());
            tvTanggal.setText(movie.getTanggal());
            tvDeskripsi.setText(movie.getDeskripsi());
            Glide.with(context).load(movie.getPhoto()).into(ivPhoto);
        }
    }
}
