package com.playlistconverter.PlaylistConverter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AccessToken(
        String access_token, int expires_in,String token_type,
        String scope, String refresh_token)  { }
