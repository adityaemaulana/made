package com.example.subfinal.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.subfinal.model.Movie;
import com.example.subfinal.model.Show;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainViewModel extends ViewModel {
    private static final String API_KEY = "608b9921cc89d66dfa854b7f47277e58";
    private MutableLiveData<ArrayList<Movie>> movieList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Show>> showList = new MutableLiveData<>();

    public boolean hasQuery = false;

    public void setMovies() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> resultList = new ArrayList<>();
        final String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY + "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject object = new JSONObject(result);
                    JSONArray results = object.getJSONArray("results");

                    for (int i = 0; i < results.length(); i++) {
                        JSONObject movieObj = results.getJSONObject(i);
                        Movie movie = new Movie(movieObj);
                        resultList.add(movie);
                    }

                    movieList.postValue(resultList);

                } catch (Exception e) {
                    Log.d("Exception: ", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure: ", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<Movie>> getMovies() {
        return movieList;
    }

    public void setShows() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Show> resultList = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/tv?api_key=" + API_KEY + "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject object = new JSONObject(result);
                    JSONArray results = object.getJSONArray("results");

                    for (int i = 0; i < results.length(); i++) {
                        JSONObject showObj = results.getJSONObject(i);
                        Show show = new Show(showObj);
                        resultList.add(show);
                    }

                    showList.postValue(resultList);

                } catch (Exception e) {
                    Log.d("Exception: ", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure: ", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<Show>> getShows() {
        return showList;
    }

    public void getMoviesByQuery(final String query) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> resultList = new ArrayList<>();
        final String url = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY +
                "&language=en-US&query=" + query;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject object = new JSONObject(result);
                    JSONArray results = object.getJSONArray("results");

                    for (int i = 0; i < results.length(); i++) {
                        JSONObject movieObj = results.getJSONObject(i);
                        Movie movie = new Movie(movieObj);
                        resultList.add(movie);
                    }

                    movieList.postValue(resultList);

                } catch (Exception e) {
                    Log.d("Exception: ", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure: ", error.getMessage());
            }
        });
    }

    public void getShowsByQuery(final String query) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Show> resultList = new ArrayList<>();
        final String url = "https://api.themoviedb.org/3/search/tv?api_key=" + API_KEY +
                "&language=en-US&query=" + query;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject object = new JSONObject(result);
                    JSONArray results = object.getJSONArray("results");

                    for (int i = 0; i < results.length(); i++) {
                        JSONObject showObj = results.getJSONObject(i);
                        Show show = new Show(showObj);
                        resultList.add(show);
                    }

                    showList.postValue(resultList);

                } catch (Exception e) {
                    Log.d("Exception: ", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure: ", error.getMessage());
            }
        });
    }
}
