package com.example.subfour.view;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.subfour.adapter.ShowAdapter;
import com.example.subfour.model.Show;
import com.example.subfour.viewmodel.MainViewModel;

import java.util.ArrayList;

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

        MainViewModel viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        viewModel.setShows();
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
}
