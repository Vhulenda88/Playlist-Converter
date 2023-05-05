package com.playlistconverter.PlaylistConverter.youtube.model.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public record PageInfo(
        int totalResults, int resultsPerPage
) {
}
