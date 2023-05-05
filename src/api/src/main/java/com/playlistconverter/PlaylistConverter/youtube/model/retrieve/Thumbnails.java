package com.playlistconverter.PlaylistConverter.youtube.model.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.playlistconverter.PlaylistConverter.youtube.model.retrieve.Key;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Thumbnails(Key key) {
}