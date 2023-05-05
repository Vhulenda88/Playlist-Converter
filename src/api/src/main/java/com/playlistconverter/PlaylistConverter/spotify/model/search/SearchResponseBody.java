package com.playlistconverter.PlaylistConverter.spotify.model.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public record SearchResponseBody(
        Tracks tracks
) {
}
