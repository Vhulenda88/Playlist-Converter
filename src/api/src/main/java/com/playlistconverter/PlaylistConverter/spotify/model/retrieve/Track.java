package com.playlistconverter.PlaylistConverter.spotify.model.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.playlistconverter.PlaylistConverter.spotify.model.retrieve.Item;

@JsonIgnoreProperties
public record Track(
        String href, int limit, String next, int offset,
        String previous, int total, Item[] items
) { }
