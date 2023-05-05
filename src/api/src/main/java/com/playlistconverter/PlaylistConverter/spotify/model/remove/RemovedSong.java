package com.playlistconverter.PlaylistConverter.spotify.model.remove;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public record RemovedSong(
        String snapshot_id
) {
}
