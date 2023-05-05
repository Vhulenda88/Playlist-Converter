package com.playlistconverter.PlaylistConverter.model;


import com.playlistconverter.PlaylistConverter.spotify.model.retrieve.Playlist;

public class ConvertRequestBody {
    public String convertTo;
    public String convertFrom;
    public String playlistID;
}
