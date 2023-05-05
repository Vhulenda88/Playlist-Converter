package com.playlistconverter.PlaylistConverter.api;

import com.playlistconverter.PlaylistConverter.model.ConvertRequestBody;
import com.playlistconverter.PlaylistConverter.model.ConvertedPlaylist;
import com.playlistconverter.PlaylistConverter.model.RemoveSongBody;
import com.playlistconverter.PlaylistConverter.model.constants.General;
import com.playlistconverter.PlaylistConverter.spotify.model.remove.RemovedSong;
import com.playlistconverter.PlaylistConverter.spotify.model.retrieve.Playlist;
import com.playlistconverter.PlaylistConverter.spotify.service.SpotifyService;
import com.playlistconverter.PlaylistConverter.youtube.model.retrieve.YoutubePlaylistItemResponse;
import com.playlistconverter.PlaylistConverter.youtube.service.YoutubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConvertController {

    @Autowired
    private YoutubeService youtubeService;

    @Autowired
    private SpotifyService spotifyService;

    /**
     * calls the api to:
     * log in
     * retrieve playlist
     * login to second platform
     * create the new playlist
     * get each song
     * add each song to new playlist
     * add playlist to account
     * @params {String, String[]}
     * @return {List<Playlist>} converted playlist
     * */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/convert")
    public ResponseEntity<?> converter(@RequestHeader("user-token") String token, @RequestBody ConvertRequestBody reqBody){
            //if converting from YouTube to spotify specify a name for playlist
            ConvertedPlaylist convertedPlaylist = null;

            String[] tokens = token.split(" ");

            switch (reqBody.convertFrom) {
                case "spotify":
                    ResponseEntity<Playlist> spotifyResponse = spotifyService.retrievePlaylist(reqBody.playlistID,tokens[0]);
                    Playlist spotifyPlaylist = spotifyResponse.getBody();
                    convertedPlaylist = youtubeService.convertToYoutbePlaylist(spotifyPlaylist,tokens[1]);
                    if (spotifyResponse.getStatusCode() != HttpStatus.OK) {
                        return new ResponseEntity<>(spotifyResponse.getStatusCode());
                    }
                    break;

                case "youtube":

                    ResponseEntity<YoutubePlaylistItemResponse> response = youtubeService.retrievePlaylist(reqBody.playlistID, tokens[0]);
                    YoutubePlaylistItemResponse playlist = response.getBody();
                    convertedPlaylist = spotifyService.convertToSpotifyPlaylist(playlist, tokens[1],tokens[0]);
                    if (response.getStatusCode() != HttpStatus.OK) {
                        return new ResponseEntity<>(response.getStatusCode());
                    }
                    break;

            }
            return new ResponseEntity<>(convertedPlaylist, HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/removeSong")
    public ResponseEntity<String> removeSong(@RequestHeader("user-token") String token, @RequestBody RemoveSongBody reqBody){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        switch (reqBody.platform()) {
            case "spotify":
                ResponseEntity<RemovedSong> spotifyResponse = spotifyService.removeSongFromPlaylist(reqBody.playlistID(),reqBody.songID(),reqBody.snapshotID(),token);
                if (spotifyResponse.getStatusCode() != HttpStatus.OK) {
                    return new ResponseEntity<>(spotifyResponse.getStatusCode());
                }
                break;

            case "youtube":

                ResponseEntity<?> response = youtubeService.removeSongFromPlaylist(reqBody.songID(), token);
                if (response.getStatusCode() != HttpStatus.NO_CONTENT) {
                    return new ResponseEntity<>(response.getStatusCode());
                }
                break;
        }
        return new ResponseEntity<>("Song removed successfully",headers, HttpStatus.NO_CONTENT);
    }


    /****************************HELPER FUNCTIONS******************************/

}
