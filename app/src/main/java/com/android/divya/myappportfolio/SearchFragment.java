package com.android.divya.myappportfolio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;


/**
 * A placeholder fragment containing a simple view.
 */
public class SearchFragment extends Fragment {

    public SearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        Intent intent = getActivity().getIntent();

        //Connect to the Spotify API with the wrapper
        SpotifyApi api = new SpotifyApi();

        //Create a SpotifyService object that we can use to get desired data
        SpotifyService spotify = api.getService();

       // ArtistsPager results = spotify.searchArtists("Beyonce");

        return rootView;
    }
}
