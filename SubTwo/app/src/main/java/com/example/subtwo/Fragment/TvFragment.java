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

import com.example.subtwo.Activity.DetailShowActivity;
import com.example.subtwo.Adapter.TVShowAdapter;
import com.example.subtwo.DummyData;
import com.example.subtwo.Model.TVShow;
import com.example.subtwo.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment {
    private RecyclerView rvShow;
    private ArrayList<TVShow> shows;

    public static final String EXTRA_SHOW = "extra_show";

    public TvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvShow = view.findViewById(R.id.rv_tv);
        rvShow.setHasFixedSize(true);

        shows = DummyData.getDumyyShows();

        showRecyclerView();
    }

    public void showRecyclerView() {
        rvShow.setLayoutManager(new LinearLayoutManager(getContext()));
        TVShowAdapter adapter = new TVShowAdapter(getContext());
        adapter.setMovies(shows);
        rvShow.setAdapter(adapter);

        adapter.setOnItemClickListener(new TVShowAdapter.OnItemMovieClick() {
            @Override
            public void onItemClicked(int position) {
                Intent intent = new Intent(getActivity(), DetailShowActivity.class);
                intent.putExtra(EXTRA_SHOW, shows.get(position));
                startActivity(intent);
            }
        });
    }
}
