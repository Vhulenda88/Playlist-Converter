package com.playlistconverter.PlaylistConverter.spotify.model.retrieve;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * the playlist object, recieved and passed back
 */

@JsonIgnoreProperties
public record Playlist(
        boolean collaborative, String description, ExternalUrls external_urls,
        Followers followers, String href, String id, Image[] images, String name,
        Owner owner, boolean isPublic, String snapshot_id, Track tracks,
        String type, String uri
)  { }
