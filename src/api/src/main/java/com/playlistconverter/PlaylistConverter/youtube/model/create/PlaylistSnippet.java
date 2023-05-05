package com.playlistconverter.PlaylistConverter.youtube.model.create;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.api.client.util.DateTime;
import com.playlistconverter.PlaylistConverter.youtube.model.retrieve.Thumbnails;

@JsonIgnoreProperties
public record PlaylistSnippet(
        DateTime publishedAt, String channelId, String title,
        String description, Thumbnails thumbnails,
        String channelTitle, String defaultLanguage,
        Localized localized
) { }

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
