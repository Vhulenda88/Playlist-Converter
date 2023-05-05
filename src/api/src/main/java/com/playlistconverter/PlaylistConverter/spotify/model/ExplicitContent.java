package com.playlistconverter.PlaylistConverter.spotify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public record ExplicitContent(
        boolean filter_enabled, boolean filter_locked
) {
}
