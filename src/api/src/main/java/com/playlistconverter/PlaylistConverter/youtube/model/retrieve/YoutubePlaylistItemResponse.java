package com.playlistconverter.PlaylistConverter.youtube.model.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.playlistconverter.PlaylistConverter.youtube.model.retrieve.PageInfo;
import com.playlistconverter.PlaylistConverter.youtube.model.retrieve.PlaylistItem;

@JsonIgnoreProperties(ignoreUnknown = true)
public record YoutubePlaylistItemResponse(
        String kind, String etag, String nextPageToken,
        String prevPageToken, PageInfo pageInfo, PlaylistItem[] items)  { }


