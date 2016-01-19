package com.example.android.popmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieFragment extends Fragment {

    private ArrayList<Movie> mMovies;
    private ImageAdapter mImageURLAdapter;

    public MovieFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // The ArrayAdapter will take data from a source and
        // use it to populate the ListView it's attached to.

        mImageURLAdapter =
                new ImageAdapter(
                        getActivity(), // The current context (this activity)
                        R.layout.grid_item_movie, // The name of the layout ID.
                        R.id.grid_item_movie_imageview, // The ID of the textview to populate.
                        new ArrayList<String>());

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        GridView gridview = (GridView) rootView.findViewById(R.id.gridview_movie);
        gridview.setAdapter(mImageURLAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getActivity(), "Position: " + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

    private void updateMovie() {
        FetchMovieTask movieTask = new FetchMovieTask();
        movieTask.execute();
    }

    @Override
    public void onStart() {
        super.onStart();
        updateMovie();
    }

    public class FetchMovieTask extends AsyncTask<String, Void, ArrayList<Movie>> {

        private final String LOG_TAG = FetchMovieTask.class.getSimpleName();


        @Override
        protected ArrayList<Movie> doInBackground(String... params) {

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String movieJsonStr = null;

            String sort_by = "popularity.desc";
            String api_key = "e3f90754dccef30eade4de34d3c5c7d0";

            try {
                // Construct the URL for the TMDB query
                // Possible parameters are avaiable at TMDB's API page, at
                // https://www.themoviedb.org/documentation/api
                final String MOVIE_BASE_URL =
                        "http://api.themoviedb.org/3/discover/movie?";
                final String SORT_PARAM = "sort_by";
                final String KEY_PARAM = "api_key";

                Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                        .appendQueryParameter(SORT_PARAM, sort_by)
                        .appendQueryParameter(KEY_PARAM, api_key)
                        .build();

                URL url = new URL(builtUri.toString());

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                movieJsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            try {
                return getMovieDataFromJson(movieJsonStr);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }

            // This will only happen if there was an error getting or parsing the forecast.
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> mMovies) {
            if (mMovies != null) {
                mImageURLAdapter.clear();
                for(int i=0; i<mMovies.size(); i++) {
                    String url = "http://image.tmdb.org/t/p/w185/"+mMovies.get(i).getmImageURL();
                    mImageURLAdapter.add(url);
                    Log.v("Adapter Loading: ",url);
                }
                // New data is back from the server.
            }
        }

        public ArrayList<Movie> getMovieDataFromJson(String movieJsonStr)
                throws JSONException {

            final String mTitle="original_title";
            final String mOverview="overview";
            final String mReleaseDate="release_date";
            final String mImageURL="poster_path";
            final String mRating="vote_average";

            JSONObject movieJson=new JSONObject(movieJsonStr);
            JSONArray results=movieJson.getJSONArray("results");
            mMovies=new ArrayList<Movie>();
            for (int i=0; i<results.length(); i++){
                JSONObject temp=results.getJSONObject(i);
                mMovies.add( new Movie(temp.getString(mTitle),
                        temp.getString(mOverview),
                        temp.getString(mReleaseDate),
                        temp.getString(mImageURL),
                        temp.getDouble(mRating)));
            }
            return mMovies;
        }
    }
}