package com.playlistconverter.PlaylistConverter.youtube.model.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.playlistconverter.PlaylistConverter.spotify.model.retrieve.Item;
import com.playlistconverter.PlaylistConverter.youtube.model.retrieve.PageInfo;

@JsonIgnoreProperties
public record Video(
        String kind, String etag, String nextPageToken,
        String prevPageToken, String regionCode, PageInfo pageInfo,
        VideoItem[] items

) { }

//{
//        "kind": "youtube#searchListResponse",
//        "etag": etag,
//        "": string,
//        "prevPageToken": string,
//        "regionCode": string,
//        "pageInfo": {
//            "totalResults": integer,
//            "resultsPerPage": integer
//        },
//        "items": [
//
//            {
//                "kind": "youtube#searchResult",
//                "etag": etag,
//                "id": {
//                    "kind": string,
//                    "videoId": string,
//                    "channelId": string,
//                    "playlistId": string
//                },
//                "snippet": {
//                    "publishedAt": datetime,
//                    "channelId": string,
//                    "title": string,
//                    "description": string,
//                    "thumbnails": {
//                    (key): {
//                    "url": string,
//                    "width": unsigned integer,
//                    "height": unsigned integer
//                    }
//                    },
//                    "channelTitle": string,
//                    "liveBroadcastContent": string
//                }
//            }
//        ]
//}
