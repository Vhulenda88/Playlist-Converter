package com.playlistconverter.PlaylistConverter.spotify.model.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = false)
public record Followers(
        String href, int total
) { }
