package com.playlistconverter.PlaylistConverter.spotify.model.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public record Artist(
        ExternalUrls external_urls, Followers followers,
        String[] genres, String href, String id, Image[] images,
        String name, int popularity, String type, String uri
) { }
