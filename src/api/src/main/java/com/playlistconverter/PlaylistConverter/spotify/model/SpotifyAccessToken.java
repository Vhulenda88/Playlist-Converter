package com.playlistconverter.PlaylistConverter.spotify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SpotifyAccessToken(
        String access_token, String token_type, String scope,
        int expires_in, String refresh_token ) { }
