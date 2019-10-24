package com.example.subtwo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.subtwo.Activity.DetailActivity;
import com.example.subtwo.Adapter.MovieAdapter;
import com.example.subtwo.DummyData;
import com.example.subtwo.Model.Movie;
import com.example.subtwo.R;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieFragment extends Fragment {
    private RecyclerView rvMovie;
    private ArrayList<Movie> movies;

    public static final String EXTRA_MOVIE = "extra_movie";

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        rvMovie = view.findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);

        movies = DummyData.getDummyMovies();

        showRecyclerView();
    }

    private void showRecyclerView() {
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        MovieAdapter adapter = new MovieAdapter(getContext());
        adapter.setMovies(movies);
        rvMovie.setAdapter(adapter);

        adapter.setOnItemClickListener(new MovieAdapter.OnItemMovieClick() {
            @Override
            public void onItemClicked(int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(EXTRA_MOVIE, movies.get(position));
                startActivity(intent);
            }
        });
    }
}