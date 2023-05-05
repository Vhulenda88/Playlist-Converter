package com.playlistconverter.PlaylistConverter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public record AuthResponse( String platform, ReturnToken accessToken) {

}
