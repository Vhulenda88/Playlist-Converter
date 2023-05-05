package com.playlistconverter.PlaylistConverter.model;

public record RemoveSongBody( String platform, String playlistID, String songID, String snapshotID) {}
