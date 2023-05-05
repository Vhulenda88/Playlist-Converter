package com.playlistconverter.PlaylistConverter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public record ReturnToken(String access_token, int expires_in) {
}
