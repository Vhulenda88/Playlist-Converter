package com.playlistconverter.PlaylistConverter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties
public record ConvertedPlaylist(
       String playlistID, String snapshotID,List<Song> songList
) { }
