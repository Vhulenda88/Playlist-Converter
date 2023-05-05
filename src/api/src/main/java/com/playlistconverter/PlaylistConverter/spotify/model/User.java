package com.playlistconverter.PlaylistConverter.spotify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.playlistconverter.PlaylistConverter.spotify.model.retrieve.ExternalUrls;
import com.playlistconverter.PlaylistConverter.spotify.model.retrieve.Followers;
import com.playlistconverter.PlaylistConverter.spotify.model.retrieve.Image;

@JsonIgnoreProperties
public record User(
    String country, String display_name, String email,
    ExplicitContent explicit_content, ExternalUrls external_urls,
    Followers followers, String href, String id, Image[] images,
    String product, String type, String uri
) {}
