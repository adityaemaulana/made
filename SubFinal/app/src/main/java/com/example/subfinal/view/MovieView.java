package com.example.subfinal.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.subfinal.R;
import com.example.subfinal.adapter.MovieAdapter;
import com.example.subfinal.model.Movie;
import com.example.subfinal.viewmodel.MainViewModel;

import java.util.ArrayList;

public class MovieView extends Fragment {
    private MovieAdapter adapter;
    private ProgressBar progressBar;
    private MainViewModel viewModel;

    public MovieView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.pb_movie);
        progressBar.setVisibility(View.VISIBLE);

        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        if (!viewModel.hasQuery) {
            viewModel.setMovies();
        }
        viewModel.getMovies().observe(this, observerMovies);

        adapter = new MovieAdapter(getContext());

        RecyclerView rvMovie = view.findViewById(R.id.rv_movie);
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovie.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_menu, menu);

        final SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressBar.setVisibility(View.VISIBLE);
                viewModel.getMoviesByQuery(query);
                viewModel.hasQuery = true;
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_reset) {
            if (viewModel.hasQuery) {
                progressBar.setVisibility(View.VISIBLE);
                viewModel.setMovies();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private Observer<ArrayList<Movie>> observerMovies = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(@Nullable final ArrayList<Movie> movies) {
            if (movies != null) {
                progressBar.setVisibility(View.GONE);

                adapter.setMovies(movies);
                adapter.setOnItemClickListener(new MovieAdapter.OnItemMovieClick() {
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
