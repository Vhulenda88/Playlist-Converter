package com.playlistconverter.PlaylistConverter.youtube.model.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.playlistconverter.PlaylistConverter.youtube.model.create.YoutubePlaylist;

@JsonIgnoreProperties
public record YoutubePlaylistsResponse(
        String kind, String etag, String nextPageToken,
        String prevPageToken, PageInfo pageInfo, YoutubePlaylist[] items
) {
}
