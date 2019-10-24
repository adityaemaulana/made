package com.example.subtri.view;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.ProgressBar;

import com.example.subtri.R;
import com.example.subtri.adapter.ShowAdapter;
import com.example.subtri.model.Show;
import com.example.subtri.viewmodel.MainViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowView extends Fragment {
    private ShowAdapter adapter;
    private ProgressBar progressBar;

    public ShowView() {
        // Required empty public constructor
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

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.setShows();
        viewModel.getShows().observe(this, observerShows);

        adapter = new ShowAdapter(getContext());
        adapter.notifyDataSetChanged();

        RecyclerView rvMovie = view.findViewById(R.id.rv_show);
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovie.setAdapter(adapter);
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
                        startActivity(intent);
                    }
                });
            }
        }
    };
}
