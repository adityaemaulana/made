package com.example.subfour.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.subfour.R;
import com.example.subfour.adapter.FavoriteMovieAdapter;
import com.example.subfour.db.MainHelper;
import com.example.subfour.model.Movie;
import com.example.subfour.viewmodel.FavoriteMovieViewModel;

import java.util.ArrayList;

public class FavoriteMovieView extends Fragment {
    private FavoriteMovieAdapter adapter;
    private ProgressBar progressBar;
    private FavoriteMovieViewModel viewModel;

    private MainHelper dbHelper;

    public FavoriteMovieView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.pb_favorite_movie);
        progressBar.setVisibility(View.VISIBLE);

        // Init Database Helper
        dbHelper = MainHelper.getInstance(getContext());

        // Init View Model
        viewModel = ViewModelProviders.of(this).get(FavoriteMovieViewModel.class);
        viewModel.setMovies(dbHelper);
        viewModel.getMovies().observe(this, observerMovies);

        // Init RecyclerView Adapter
        adapter = new FavoriteMovieAdapter(getContext());

        RecyclerView rvMovie = view.findViewById(R.id.rv_favorite_movie);
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovie.setAdapter(adapter);
    }

    private Observer<ArrayList<Movie>> observerMovies = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(@Nullable final ArrayList<Movie> movies) {
            if (movies != null) {
                progressBar.setVisibility(View.GONE);

                adapter.setFavoriteMovies(movies);
                adapter.setOnItemClickListener(new FavoriteMovieAdapter.OnItemFavoriteMovieClick() {
                    @Override
                    public void onItemClicked(int position) {
                        Intent intent = new Intent(getActivity(), DetailView.class);
                        intent.putExtra(DetailView.EXTRA_MOVIE, movies.get(position));
                        startActivity(intent);
                    }
                });
            }
        }
    };
}
