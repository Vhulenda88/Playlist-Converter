package com.playlistconverter.PlaylistConverter.youtube.model.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.api.client.util.DateTime;
import com.playlistconverter.PlaylistConverter.youtube.model.retrieve.Thumbnails;

@JsonIgnoreProperties
public record VideoID(
        String kind, String videoId
) { }
