package com.playlistconverter.PlaylistConverter.youtube.model.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResourceId(
        String kind, String videoId
) { }


