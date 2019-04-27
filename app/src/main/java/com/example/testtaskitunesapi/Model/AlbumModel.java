
package com.example.testtaskitunesapi.Model;


import java.util.ArrayList;
import java.util.List;

public class AlbumModel {

    private String collectionName;
    private String artistName;
    private String releaseDate;
    private String artworkUrl;
    private String collectionPrice;
    private String primaryGenreName;
    private List<Track> tracks = new ArrayList<>();
    private ArrayList<String> trackNames = new ArrayList<>();
    private ArrayList<String> previewUrls = new ArrayList<>();
    private ArrayList<String> trackTimeMillis = new ArrayList<>();
    private ArrayList<String> trackPrices = new ArrayList<>();
    private ArrayList<String> currencys = new ArrayList<>();

    private List<Track> listtotracks = new ArrayList<>();

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
        //tracks = new ArrayList<>();
        this.tracks.add(track);
    }

    public int getSize() {
        return tracks.size();
    }

    public String getCollectionPrice() {

        return collectionPrice;
    }

    public void setCollectionPrice(String collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public ArrayList<String> getTrackNames() {
        trackNames = new ArrayList<>();
        for(Track track:tracks){
            trackNames.add(track.getTrackName());
        }
        return trackNames;
    }



    public ArrayList<String> getPreviewUrls() {
        previewUrls = new ArrayList<>();
        for(Track track:tracks){
            previewUrls.add(track.getPreviewUrl());
        }
        return previewUrls;
    }



    public ArrayList<String> getTrackTimeMillis() {
        trackTimeMillis = new ArrayList<>();
        for(Track track:tracks){
            trackTimeMillis.add(String.valueOf(track.getTrackTimeMillis()));
        }
        return trackTimeMillis;
    }


    public ArrayList<String> getTrackPrices() {
        trackPrices = new ArrayList<>();
        for(Track track:tracks){
            trackPrices.add(String.valueOf(track.getTrackPrice()));
        }
        return trackPrices;
    }

    public ArrayList<String> getCurrencys() {
        currencys = new ArrayList<>();
        for(Track track:tracks){
            currencys.add(track.getCurrency());
        }
        return currencys;
    }

    public void setTrackNames(ArrayList<String> trackNames) {
        this.trackNames = trackNames;
    }

    public void setPreviewUrls(ArrayList<String> previewUrls) {
        this.previewUrls = previewUrls;
    }

    public void setTrackTimeMillis(ArrayList<String> trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }

    public void setTrackPrices(ArrayList<String> trackPrices) {
        this.trackPrices = trackPrices;
    }

    public void setCurrencys(ArrayList<String> currencys) {
        this.currencys = currencys;
    }

    public List<Track> getListtotracks() {
        listtotracks= new ArrayList<>();
        for(int i=0; i<=trackNames.size()-1;i++){
            Track track=new Track();
            track.setTrackName(trackNames.get(i));
            track.setPreviewUrl(previewUrls.get(i));
            track.setTrackTimeMillis(Integer.valueOf(trackTimeMillis.get(i)));
            track.setTrackPrice(Double.valueOf(trackPrices.get(i)));
            track.setCurrency(currencys.get(i));
            listtotracks.add(track);
        }
        return listtotracks;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }
}
