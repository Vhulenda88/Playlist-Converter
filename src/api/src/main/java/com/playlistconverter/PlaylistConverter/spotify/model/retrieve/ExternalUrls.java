package com.playlistconverter.PlaylistConverter.spotify.model.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public record ExternalUrls(
        String spotify
) { }
