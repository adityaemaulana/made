package com.example.subfinal.view;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.subfinal.R;
import com.example.subfinal.adapter.ShowAdapter;
import com.example.subfinal.model.Show;
import com.example.subfinal.viewmodel.MainViewModel;

import java.util.ArrayList;

public class ShowView extends Fragment {
    private ShowAdapter adapter;
    private ProgressBar progressBar;
    private MainViewModel viewModel;

    public ShowView() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.pb_show);
        progressBar.setVisibility(View.VISIBLE);

        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        if (!viewModel.hasQuery) {
            viewModel.setShows();
        }
        viewModel.getShows().observe(this, observerShows);

        adapter = new ShowAdapter(getContext());
        adapter.notifyDataSetChanged();

        RecyclerView rvShow = view.findViewById(R.id.rv_show);
        rvShow.setLayoutManager(new LinearLayoutManager(getContext()));
        rvShow.setAdapter(adapter);
    }

    private Observer<ArrayList<Show>> observerShows = new Observer<ArrayList<Show>>() {
        @Override
        public void onChanged(@Nullable final ArrayList<Show> shows) {
            if (shows != null) {
                progressBar.setVisibility(View.GONE);

                adapter.setShows(shows);
                adapter.setOnItemClickListener(new ShowAdapter.OnItemShowClick() {
                    @Override
                    public void onItemClicked(int position) {
                        Intent intent = new Intent(getActivity(), DetailView.class);
                        intent.putExtra(DetailView.EXTRA_SHOW, shows.get(position));
                        Log.d("ShowView", String.valueOf(shows.get(position).getIdShow()));
                        startActivity(intent);
                    }
                });
            }
        }
    };

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_menu, menu);

        final SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressBar.setVisibility(View.VISIBLE);
                viewModel.getShowsByQuery(query);
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
                viewModel.setShows();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
