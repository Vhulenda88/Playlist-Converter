package com.playlistconverter.PlaylistConverter.youtube.model.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.api.client.util.DateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ContentDetails(
        String videoId, String note, DateTime videoPublishedAt
) {}
