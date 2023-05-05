package com.playlistconverter.PlaylistConverter.spotify.model.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public record AddedBy(
        ExternalUrls external_urls, Followers followers, String href,
        String id, String type, String uri
) { }
