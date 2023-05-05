package com.playlistconverter.PlaylistConverter.youtube.model.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.api.client.util.DateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Snippet(
        DateTime publishedAt, String channelId, String title,
        String description, Thumbnails thumbnails,
        String channelTitle, String videoOwnerChannelTitle,
        String videoOwnerChannelId, String playlistId, int position,
        ResourceId resourceId
) {}
