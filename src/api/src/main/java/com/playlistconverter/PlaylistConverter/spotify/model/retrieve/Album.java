package com.playlistconverter.PlaylistConverter.spotify.model.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public record Album(
        String album_type, int total_tracks, String[] available_markets,
        ExternalUrls external_urls, String href, String id, Image[] images,
        String name, String release_date, String release_date_precision,
        Restrictions restrictions, String type, String uri, Copyright[] copyrights,
        ExternalIDs external_ids, String[] genres, String label, int popularity,
        String album_group, SimplifiedArtist[] artists
) { }
