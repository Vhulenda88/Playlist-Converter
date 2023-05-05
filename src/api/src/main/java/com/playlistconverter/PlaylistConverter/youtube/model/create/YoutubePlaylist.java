package com.playlistconverter.PlaylistConverter.youtube.model.create;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.playlistconverter.PlaylistConverter.youtube.model.retrieve.Status;

@JsonIgnoreProperties
public record YoutubePlaylist(
        String kind, String etag, String id, PlaylistSnippet snippet, Status status,
        PlaylistContentDetails contentDetails, Player player, Localizations localizations
) { }
