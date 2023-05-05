package com.playlistconverter.PlaylistConverter.youtube.model.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PlaylistItem(
        String kind, String etag, String id, Snippet snippet,ContentDetails contentDetails, Status status
) {
}



