package com.playlistconverter.PlaylistConverter.youtube.model.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public record VideoItem(
        String kind, String etag, VideoID id
) { }

//{
//                "kind": "youtube#searchResult",
//                "etag": etag,
//

//            }