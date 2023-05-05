package com.playlistconverter.PlaylistConverter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public record Song(
        String id,String songName, String artistName, int Position
) {
}
