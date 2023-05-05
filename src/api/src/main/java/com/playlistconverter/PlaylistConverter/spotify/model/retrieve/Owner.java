package com.playlistconverter.PlaylistConverter.spotify.model.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.playlistconverter.PlaylistConverter.spotify.model.retrieve.ExternalUrls;
import com.playlistconverter.PlaylistConverter.spotify.model.retrieve.Followers;

@JsonIgnoreProperties
public record Owner(
        ExternalUrls external_urls, Followers followers, String href,
        String id, String type, String uri, String display_name
) { }
