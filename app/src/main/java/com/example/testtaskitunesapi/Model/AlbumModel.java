
package com.example.testtaskitunesapi.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlbumModel {

    private String collectionName;
    private String artistName;
    private String releaseDate;
    private String artworkUrl;
    private List<Track> tracks = new ArrayList<>();
    private int size;



    public AlbumModel() {
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getArtworkUrl() {
        return artworkUrl;
    }

    public void setArtworkUrl(String artworkUrl) {
        this.artworkUrl = artworkUrl;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Track track) {

        this.tracks.add(track);
    }

    public int getSize() {
        return tracks.size();
    }


}
