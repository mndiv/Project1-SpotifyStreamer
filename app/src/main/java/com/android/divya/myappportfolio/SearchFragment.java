package com.android.divya.myappportfolio;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;


/**
 * A placeholder fragment containing a simple view.
 */
public class SearchFragment extends Fragment {

    private ArrayAdapter<String> mArtistName;
    public SearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        mArtistName = new ArrayAdapter<String>(getActivity(), R.layout.list_item_artist,
                                                R.id.list_artist_name, new ArrayList<String>());
        Intent intent = getActivity().getIntent();

        ListView listView = (ListView)  rootView.findViewById(R.id.list_artists);
        listView.setAdapter(mArtistName);

        SpotifyTask spotifyTask = new SpotifyTask();
        spotifyTask.execute();

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public class SpotifyTask extends AsyncTask<Void, Void, String[]> {


        private final String LOG_TAG= SpotifyTask.class.getSimpleName();
        @Override
        protected String[] doInBackground(Void...params) {

            //connect to the Spotify API with the wrapper
            SpotifyApi api = new SpotifyApi();

            //Create a SpotifyService object that we can use to get desired data
            SpotifyService spotify = api.getService();

            ArtistsPager results = spotify.searchArtists("Paul");

            List<Artist> artists = results.artists.items;

            String artistNames[] = new String[artists.size()];
            for(int i=0;i<artists.size();i++){
                Artist artist = (Artist)artists.get(i);
                artistNames[i] = artist.name;
            }
            return artistNames;
            //return null;
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);

            if(strings != null){
                mArtistName.clear();
                for(String artist : strings)
                    mArtistName.add(artist);

            }
        }
    }
}
