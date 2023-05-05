package com.playlistconverter.PlaylistConverter.spotify.service;

import com.playlistconverter.PlaylistConverter.model.*;
import com.playlistconverter.PlaylistConverter.spotify.model.User;
import com.playlistconverter.PlaylistConverter.spotify.model.create.CreatePlaylistRequestBody;
import com.playlistconverter.PlaylistConverter.spotify.model.insert.InsertSong;
import com.playlistconverter.PlaylistConverter.spotify.model.insert.TrackID;
import com.playlistconverter.PlaylistConverter.spotify.model.retrieve.Playlist;
import com.playlistconverter.PlaylistConverter.spotify.model.constants.ConvertPlaylist;
import com.playlistconverter.PlaylistConverter.spotify.model.remove.RemoveSongBody;
import com.playlistconverter.PlaylistConverter.spotify.model.remove.RemoveTrack;
import com.playlistconverter.PlaylistConverter.spotify.model.remove.RemovedSong;
import com.playlistconverter.PlaylistConverter.spotify.model.search.SearchResponseBody;
import com.playlistconverter.PlaylistConverter.spotify.model.search.Tracks;
import com.playlistconverter.PlaylistConverter.youtube.model.create.YoutubePlaylist;
import com.playlistconverter.PlaylistConverter.youtube.model.retrieve.PlaylistItem;
import com.playlistconverter.PlaylistConverter.youtube.model.retrieve.YoutubePlaylistItemResponse;
import com.playlistconverter.PlaylistConverter.youtube.model.retrieve.YoutubePlaylistsResponse;
import com.playlistconverter.PlaylistConverter.youtube.service.YoutubeService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.playlistconverter.PlaylistConverter.spotify.model.constants.AuthRequests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SpotifyService {

    private RestTemplate restTemplate;

    @Autowired
    private YoutubeService youtubeService;

    private User currentUser;

    public SpotifyService(@NotNull RestTemplateBuilder restBuilder){
        this.restTemplate = restBuilder.build();
    }
    public ResponseEntity<AccessToken> SpotifyAuth(@NotNull Token token ){

        HttpHeaders headers = new HttpHeaders();
       headers.set("Authorization","Basic "+ AuthRequests.AuthToken);//Base64.encodeBase64(authToken.getBytes())
        headers.set("Content-Type","application/x-www-form-urlencoded");

        MultiValueMap<String, String> body= new LinkedMultiValueMap<String, String>();
        body.add("code", token.code);
        body.add("redirect_uri", AuthRequests.RedirectUri );
        body.add("grant_type", token.grant_type);



        HttpEntity<MultiValueMap<String, String>> reqBody = new HttpEntity<MultiValueMap<String, String>>(body, headers);
        return this.restTemplate.exchange(AuthRequests.AuthURL, HttpMethod.POST,reqBody, AccessToken.class);

    }

    public ResponseEntity<User> getUserDetails(String token){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+ token);

        HttpEntity<HttpHeaders> reqBody = new HttpEntity<HttpHeaders>(null,headers);
        return this.restTemplate.exchange(AuthRequests.UserURL, HttpMethod.GET, reqBody, User.class);
    }

    public ResponseEntity<Playlist> retrievePlaylist(@NotNull String playlistID, @NotNull String token ){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+ token);

        Map<String, String> body= new HashMap<String, String>();
        body.put("playlist_id", playlistID);


        HttpEntity<HttpHeaders> reqBody = new HttpEntity<HttpHeaders>(null,headers);
        return this.restTemplate.exchange(ConvertPlaylist.RetrievePlaylistURL, HttpMethod.GET,reqBody,Playlist.class,body);

    }

    public ResponseEntity<SearchResponseBody> searchSong(String songName, String songArtist, String token){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+ token);

        Map<String, String> params= new HashMap<String, String>();
        params.put("q",songName+ " by "+ songArtist );
        params.put("type",ConvertPlaylist.Track);

        HttpEntity<HttpHeaders> reqBody = new HttpEntity<>(null,headers);

        return this.restTemplate.exchange(ConvertPlaylist.SearchSongURL, HttpMethod.GET,reqBody, SearchResponseBody.class,params);
    }

    public ResponseEntity<RemovedSong> removeSongFromPlaylist(String playlistID,String songID, String snapshotID, String token){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+ token);
        headers.set("Accept","application/json");

        Map<String, String> params= new HashMap<String, String>();
        params.put("playlist_id", playlistID);

        RemoveTrack track = new RemoveTrack(songID);
        RemoveTrack[] tracks = new RemoveTrack[1];
        tracks[0] = track;
        RemoveSongBody body = new RemoveSongBody(tracks);
        HttpEntity<RemoveSongBody> reqBody = new HttpEntity<RemoveSongBody>(body,headers);

        return this.restTemplate.exchange(ConvertPlaylist.RemoveSongURL, HttpMethod.DELETE,reqBody, RemovedSong.class,params);

    }

    public ResponseEntity<Playlist> createPlaylist(String title, String token){
        //        {
//            "name": "New Playlist",
//                "description": "New playlist description",
//                "public": false
//        }

        CreatePlaylistRequestBody body = new CreatePlaylistRequestBody(title);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+ token);
        headers.set("Accept","application/json");

        Map<String, String> params= new HashMap<String, String>();
        params.put("user_id", currentUser.id());


        HttpEntity<CreatePlaylistRequestBody> reqBody = new HttpEntity<CreatePlaylistRequestBody>(body,headers);

        return this.restTemplate.exchange(ConvertPlaylist.CreatePlaylistURL,HttpMethod.POST,reqBody,Playlist.class,params);
    }

    public ResponseEntity<TrackID> addSongToPlaylist(String songId, Playlist newPlaylist, String token){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+ token);
        headers.set("Accept","application/json");

        Map<String, String> params= new HashMap<String, String>();
        params.put("playlist_id", newPlaylist.id());

        String[] uris = new String[1];
        uris[0] = ConvertPlaylist.TrackURI + songId;
        InsertSong song = new InsertSong(uris);

        HttpEntity<InsertSong> reqBody = new HttpEntity<InsertSong>(song,headers);


        return this.restTemplate.exchange(ConvertPlaylist.InsertSongURL,HttpMethod.POST,reqBody,TrackID.class,params);
    }

    /****************************HELPER FUNCTIONS******************************/

    public ConvertedPlaylist convertToSpotifyPlaylist(@NotNull YoutubePlaylistItemResponse playlist, String token, String youtubeToken ){

        currentUser = this.getUserDetails(token).getBody();
        // put each song name and artist in the playlist into an array
        List<Song> songsAdded = new ArrayList<>();

        //get playlist name
        String id = playlist.items()[0].snippet().channelId();
        String playlistId = playlist.items()[0].snippet().playlistId();
        YoutubePlaylistsResponse res = this.youtubeService.getPlaylists(playlistId, youtubeToken).getBody();
        String playlistName = "New Playlist created by the converter";
        for (YoutubePlaylist item: res.items()) {
            if (item.id() == playlistId){
                playlistName = item.snippet().localized().title();
            }
        }


        //create a Spotify playlist
        Playlist newPlaylist = this.createPlaylist(playlistName,token).getBody();

        for (PlaylistItem item : playlist.items()) {
            //Search for each song
            ResponseEntity<SearchResponseBody> response = this.searchSong(item.snippet().title(),item.snippet().videoOwnerChannelTitle(),token);
            SearchResponseBody body = response.getBody();
            // add the found song into the playlist
            if (body.tracks().items() != null){
                TrackID trackID = this.addSongToPlaylist(body.tracks().items()[0].id(), newPlaylist, token).getBody();
                Song song = new Song(body.tracks().items()[0].uri(),body.tracks().items()[0].name(),body.tracks().items()[0].artists()[0].name(),0);
                songsAdded.add(song);
            }
        }

        return new ConvertedPlaylist(newPlaylist.id(), newPlaylist.snapshot_id(), songsAdded);
    }
}