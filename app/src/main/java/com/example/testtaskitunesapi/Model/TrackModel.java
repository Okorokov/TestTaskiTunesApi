
package com.example.testtaskitunesapi.Model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class TrackModel {
    private static final String TAG = "MainActivity";

    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private List<Track> tracks = null;


    private Set<String> collectionName=new LinkedHashSet<String>();
    private List<AlbumModel> albumModels=new ArrayList<>();

    public TrackModel() {
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<Track> getTracks() {
        Collections.sort(tracks, Track.NameComparator);
        return tracks;
    }

    public void setTracks(List<Track> tracks) {

        this.tracks = tracks;
    }

    public int getSize() {
        return tracks.size();
    }



   public Set<String> getCollectionName() {

        for(Track tr:tracks){
            collectionName.add(tr.getCollectionName());
        }
        return collectionName;
    }

    public List<AlbumModel> getAlbumModels() {
       //albumModels=new ArrayList<>();
        collectionName=new LinkedHashSet<String>();
        for(Track tr:tracks){
            collectionName.add(tr.getCollectionName());
        }
        Iterator<String> itr = collectionName.iterator();

        while (itr.hasNext()) {
            AlbumModel albumModel=new AlbumModel();
            albumModel.setCollectionName(itr.next());
            for(Track track:tracks){
                if(albumModel.getCollectionName().equals(track.getCollectionName())){
                    albumModel.setArtistName(track.getArtistName());
                    albumModel.setArtworkUrl(track.getArtworkUrl100());
                    albumModel.setReleaseDate(track.getReleaseDate());
                    albumModel.setCollectionPrice(String.valueOf(track.getCollectionPrice()));
                    albumModel.setPrimaryGenreName(track.getPrimaryGenreName());
                    albumModel.setTrackCount(String.valueOf(track.getTrackCount()));
                    albumModel.setTracks(track);

                }
                //Log.d(TAG, "track:  " + track.getArtistName());
            }
            albumModels.add(albumModel);
        }
        return albumModels;
    }
}
