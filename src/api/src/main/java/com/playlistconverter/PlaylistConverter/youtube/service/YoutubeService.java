package com.playlistconverter.PlaylistConverter.youtube.service;

import com.playlistconverter.PlaylistConverter.model.*;
import com.playlistconverter.PlaylistConverter.model.AccessToken;
import com.playlistconverter.PlaylistConverter.spotify.model.retrieve.Item;
import com.playlistconverter.PlaylistConverter.spotify.model.retrieve.Playlist;
import com.playlistconverter.PlaylistConverter.youtube.model.create.PlaylistSnippet;
import com.playlistconverter.PlaylistConverter.youtube.model.create.YoutubePlaylist;
import com.playlistconverter.PlaylistConverter.youtube.model.retrieve.*;
import com.playlistconverter.PlaylistConverter.youtube.model.contants.AuthRequests;
import com.playlistconverter.PlaylistConverter.youtube.model.contants.ConvertPlaylist;
import com.playlistconverter.PlaylistConverter.youtube.model.search.Video;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YoutubeService {


    private RestTemplate restTemplate;
    public YoutubeService(@NotNull RestTemplateBuilder restBuilder){
        this.restTemplate = restBuilder.build();
    }


    public ResponseEntity<AccessToken> YoutubeAuth(Token token ){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Host","oauth2.googleapis.com");
        headers.set("Content-Type","application/x-www-form-urlencoded");

        MultiValueMap<String, String> body= new LinkedMultiValueMap<String, String>();
        body.add("code", token.code);
        body.add("client_id", AuthRequests.ClientID);
        body.add("client_secret", AuthRequests.ClientSecret);
        body.add("redirect_uri", AuthRequests.RedirectUri);
        body.add("grant_type", token.grant_type);


        HttpEntity<MultiValueMap<String, String>> reqBody = new HttpEntity<MultiValueMap<String, String>>(body, headers);
        return this.restTemplate.exchange(AuthRequests.AuthURL, HttpMethod.POST,reqBody,AccessToken.class);

    }

    public ResponseEntity<YoutubePlaylistItemResponse> retrievePlaylist(String playlistID,@NotNull String token){
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization","Bearer "+ token);
        headers.set("Content-Type","application/json");

        Map<String, String> params= new HashMap<String, String>();
        params.put("part", ConvertPlaylist.MixedPart);
        System.out.print("test"+playlistID+ "test");
        params.put("playlistId", playlistID);
        params.put("key", ConvertPlaylist.APIKey);

        HttpEntity<HttpHeaders> reqBody = new HttpEntity<HttpHeaders>(headers);

        return this.restTemplate.exchange(ConvertPlaylist.RetrievePlaylistURL, HttpMethod.GET,reqBody,YoutubePlaylistItemResponse.class,params);
    }

    public ResponseEntity<Video> searchSong(String songName, String songArtist,@NotNull String token){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+ token);
        headers.set("Accept","application/json");

        Map<String, String> params= new HashMap<String, String>();
        params.put("part", ConvertPlaylist.IdPart);
        params.put("keyword", songName + " by " + songArtist);
        params.put("key", ConvertPlaylist.APIKey);

        HttpEntity<?> reqBody = new HttpEntity<>(null,headers);

        return this.restTemplate.exchange(ConvertPlaylist.SearchSongURL, HttpMethod.GET,reqBody,Video.class,params);
    }

    public ResponseEntity<YoutubePlaylist> createPlaylist(String title,@NotNull String token){
        PlaylistSnippet snippet =
                new PlaylistSnippet(null,"",title,"",null,"","",null);
        YoutubePlaylist newplaylist = new YoutubePlaylist(
                ConvertPlaylist.PlaylistKind,"","",snippet,null,null,null,null
        );

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+ token);
        headers.set("Accept","application/json");
        headers.set("Content-Type","application/json");

        Map<String, String> params= new HashMap<String, String>();
        params.put("part", ConvertPlaylist.PartSC);
        params.put("key", ConvertPlaylist.APIKey);

        HttpEntity<YoutubePlaylist> reqBody = new HttpEntity<YoutubePlaylist>(newplaylist,headers);

        return this.restTemplate.exchange(ConvertPlaylist.CreatePlaylistURL,HttpMethod.POST,reqBody,YoutubePlaylist.class,params);
    }

    public ResponseEntity<YoutubePlaylistsResponse> getPlaylists(String id, @NotNull String token){
        System.out.print("chanelID:"+token);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+ token);
        headers.set("Accept","application/json");

        Map<String, String> params= new HashMap<String, String>();
        params.put("part", ConvertPlaylist.Part);
        params.put("id", id);
        params.put("key", ConvertPlaylist.APIKey);

        HttpEntity<?> reqBody = new HttpEntity<>(null,headers);

        return this.restTemplate.exchange(ConvertPlaylist.SearchPlaylistsURL,HttpMethod.GET,reqBody,YoutubePlaylistsResponse.class,params);
    }


    public ResponseEntity<PlaylistItem> addSongToPlaylist(String songId, YoutubePlaylist newPlaylist, @NotNull String token){
        ResourceId resourceId = new ResourceId(ConvertPlaylist.VideoKind,songId);
        Snippet snippet =
                new Snippet(null,newPlaylist.snippet().channelId(),"",""
                        ,null,"","", "",
                        newPlaylist.id(), newPlaylist.contentDetails().itemCount(), resourceId);
        PlaylistItem newPlaylistItem = new PlaylistItem(
                ConvertPlaylist.PlaylistItemKind,"","",snippet,null,null);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+ token);
        headers.set("Accept","application/json");
        headers.set("Content-Type","application/json");

        Map<String, String> params= new HashMap<String, String>();
        params.put("part", ConvertPlaylist.MixedPart);
        params.put("key", ConvertPlaylist.APIKey);

        HttpEntity<PlaylistItem> reqBody = new HttpEntity<PlaylistItem>(newPlaylistItem,headers);


        return this.restTemplate.exchange(ConvertPlaylist.InsertSongURL,HttpMethod.POST,reqBody,PlaylistItem.class,params);
    }

    public ResponseEntity<?> removeSongFromPlaylist(String songID, String token){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+ token);
        headers.set("Accept","application/json");

        Map<String, String> params= new HashMap<String, String>();
        params.put("id", songID);
        params.put("key", ConvertPlaylist.APIKey);

        HttpEntity<?> reqBody = new HttpEntity<>(null,headers);

        return this.restTemplate.exchange(ConvertPlaylist.RemoveSongURL, HttpMethod.DELETE,reqBody,Video.class,params);

    }

    /****************************HELPER FUNCTIONS******************************/

    public ConvertedPlaylist convertToYoutbePlaylist(Playlist spotifyPlaylist, @NotNull String token ){
        // put each song name and artist in the playlist into an array
        List<Song> songsAdded = new ArrayList<>();
        //create a YouTube playlist
        YoutubePlaylist newPlaylist = this.createPlaylist(spotifyPlaylist.name(), token).getBody();

        for (Item item : spotifyPlaylist.tracks().items()) {
            //Search for each song
            ResponseEntity<Video> response = this.searchSong(item.track().name(),item.track().artists()[0].name(),token);
            Video video = response.getBody();
            // add the found song into the playlist
            PlaylistItem playlistItem = this.addSongToPlaylist(video.items()[0].id().videoId(), newPlaylist, token).getBody();
            System.out.print(playlistItem.id()+"\n");
            Song song = new Song(playlistItem.id(),playlistItem.snippet().title(),"",playlistItem.snippet().position());
            songsAdded.add(song);
        }

        return new ConvertedPlaylist(newPlaylist.id(),"",songsAdded);
    }


}
