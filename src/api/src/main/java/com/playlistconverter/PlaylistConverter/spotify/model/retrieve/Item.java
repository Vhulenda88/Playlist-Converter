package com.playlistconverter.PlaylistConverter.spotify.model.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = false)
public record Item(
        String added_at, AddedBy added_by, boolean is_local, TrackObject track
) { }


