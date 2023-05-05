package com.playlistconverter.PlaylistConverter.youtube.model.create;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public record PlaylistContentDetails(
        int itemCount
) {
}
