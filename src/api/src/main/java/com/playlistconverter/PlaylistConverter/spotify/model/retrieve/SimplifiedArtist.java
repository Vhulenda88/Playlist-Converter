package com.playlistconverter.PlaylistConverter.spotify.model.retrieve;

import com.playlistconverter.PlaylistConverter.spotify.model.retrieve.ExternalUrls;

public record SimplifiedArtist(
        ExternalUrls external_urls, String href, String id, String name,
        String type, String uri
) { }
