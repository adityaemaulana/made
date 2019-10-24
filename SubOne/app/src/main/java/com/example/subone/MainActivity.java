package com.example.subone;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String[] dataJudul, dataTanggal, dataDeskripsi, dataRating, dataDurasi, dataGenre;
    private TypedArray dataPhoto;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);
        ListView listView = findViewById(R.id.lv_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = movies.get(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
                startActivity(intent);
            }
        });

        prepareData();
        addData();
    }

    private void addData() {
        movies = new ArrayList<>();

        for (int i = 0; i < dataJudul.length; i++) {
            Movie m = new Movie();
            m.setJudul(dataJudul[i]);
            m.setTanggal(dataTanggal[i]);
            m.setDeskripsi(dataDeskripsi[i]);
            m.setPhoto(dataPhoto.getResourceId(i, -1));
            m.setGenre(dataGenre[i]);
            m.setDurasi(dataDurasi[i]);
            m.setRating(dataRating[i]);
            movies.add(m);
        }

        adapter.setMovies(movies);
    }

    private void prepareData() {
        dataJudul = getResources().getStringArray(R.array.data_judul);
        dataTanggal = getResources().getStringArray(R.array.data_tanggal);
        dataDeskripsi = getResources().getStringArray(R.array.data_deskripsi);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
        dataRating = getResources().getStringArray(R.array.data_rating);
        dataDurasi = getResources().getStringArray(R.array.data_durasi);
        dataGenre = getResources().getStringArray(R.array.data_genre);
    }
}
