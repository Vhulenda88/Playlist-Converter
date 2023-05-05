package com.playlistconverter.PlaylistConverter.spotify.model.retrieve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.playlistconverter.PlaylistConverter.spotify.model.retrieve.*;

@JsonIgnoreProperties
public record TrackObject(
        Album album, Artist[] artists, String[] available_markets,
        int disc_number, int duration_ms, boolean explicit, ExternalIDs external_ids,
        ExternalUrls external_urls, String href, String id, boolean is_playable,
        Restrictions restrictions, String name, int popularity, String preview_url,
        int track_number, String type, String uri, boolean is_local
) { }
