package com.playlistconverter.PlaylistConverter.youtube.model.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Status(String privacyStatus) {
}
