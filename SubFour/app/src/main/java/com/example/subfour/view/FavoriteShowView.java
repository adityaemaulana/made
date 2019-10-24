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
import com.example.subfour.adapter.FavoriteShowAdapter;
import com.example.subfour.db.MainHelper;
import com.example.subfour.model.Show;
import com.example.subfour.viewmodel.FavoriteShowViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteShowView extends Fragment {
    private FavoriteShowAdapter adapter;
    private ProgressBar progressBar;
    private MainHelper mainHelper;
    private FavoriteShowViewModel viewModel;
    private MainHelper dbHelper;

    public FavoriteShowView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_show_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.pb_favorite_show);
        progressBar.setVisibility(View.VISIBLE);

        // Init Database Helper
        dbHelper = MainHelper.getInstance(getContext());

        // Init View Model
        viewModel = ViewModelProviders.of(this).get(FavoriteShowViewModel.class);
        viewModel.setShows(dbHelper);
        viewModel.getShows().observe(this, observerShows);

        // Init RecyclerView Adapter
        adapter = new FavoriteShowAdapter(getContext());

        RecyclerView rvMovie = view.findViewById(R.id.rv_favorite_show);
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovie.setAdapter(adapter);
    }

    private Observer<ArrayList<Show>> observerShows = new Observer<ArrayList<Show>>() {
        @Override
        public void onChanged(@Nullable final ArrayList<Show> shows) {
            if (shows != null) {
                progressBar.setVisibility(View.GONE);

                adapter.setFavoriteShows(shows);
                adapter.setOnItemClickListener(new FavoriteShowAdapter.OnItemFavoriteShowClick() {
                    @Override
                    public void onItemClicked(int position) {
                        Intent intent = new Intent(getActivity(), DetailView.class);
                        intent.putExtra(DetailView.EXTRA_SHOW, shows.get(position));
                        startActivity(intent);
                    }
                });
            }
        }
    };

}