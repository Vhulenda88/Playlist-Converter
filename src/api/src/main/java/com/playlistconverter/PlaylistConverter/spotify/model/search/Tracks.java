package com.playlistconverter.PlaylistConverter.spotify.model.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.playlistconverter.PlaylistConverter.spotify.model.retrieve.TrackObject;

@JsonIgnoreProperties
public record Tracks(
         String href,TrackObject[] items, int limit, String next,
         int offset, String previous, int total

) {
}
