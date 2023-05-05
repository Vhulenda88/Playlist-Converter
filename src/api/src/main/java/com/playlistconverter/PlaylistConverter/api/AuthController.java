package com.playlistconverter.PlaylistConverter.api;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.playlistconverter.PlaylistConverter.model.*;
import com.playlistconverter.PlaylistConverter.spotify.model.Song;
import com.playlistconverter.PlaylistConverter.spotify.model.constants.AuthRequests;
import com.playlistconverter.PlaylistConverter.spotify.service.SpotifyService;
import com.playlistconverter.PlaylistConverter.youtube.service.YoutubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;

@RestController
public class AuthController {


    @Autowired
    private SpotifyService spotifyService;
    @Autowired
    private YoutubeService youtubeService;





    /**
     addPlaylistAcc function:
     login to user account
     create new playlist
     add each song
     request body:
     credentials: users credentials
     playlist: playlist list
     platform: platform that the user wants to add playlist to
     return:
     boolean or number 200 if successful or something
     * */
    @PostMapping("/addPlaylist")
    public void addPlaylistAcc(){

    }

    /**
     spotifyUserAuthorization function:
     stores the users code in the db
     @params:
     @return: {@link  int}
     void
      * */

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/authorize", produces = "text/html")
    public ResponseEntity<?> spotifyUserAuthorization(@RequestBody AuthorizeBody reqBody) throws IOException, GoogleJsonResponseException, URISyntaxException, GeneralSecurityException {

        ResponseEntity<AccessToken> response;
        Token token = new Token(reqBody.code);
        AuthResponse body = null;
        ReturnToken returnToken = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        switch (reqBody.platform) {
            case "spotify":
                response = spotifyService.SpotifyAuth(token);
                returnToken = new ReturnToken(response.getBody().access_token(),response.getBody().expires_in());
                System.out.print(response.getStatusCode());
                body = new AuthResponse(reqBody.platform, returnToken );

                if (response.getStatusCode() != HttpStatus.OK) {
                    return new ResponseEntity<>(response.getStatusCode());
                }
                break;

            case "youtube":
                response = youtubeService.YoutubeAuth(token);
                returnToken = new ReturnToken(response.getBody().access_token(),response.getBody().expires_in());
                body = new AuthResponse(reqBody.platform, returnToken );
                System.out.print(response.getStatusCode());
                if (response.getStatusCode() != HttpStatus.OK) {
                    return new ResponseEntity<>(response.getStatusCode());
                }
                break;

        }


        return new ResponseEntity<>(body,headers,HttpStatus.OK);

    }


    /****************************HELPER FUNCTIONS******************************/

    public boolean addSongToPlaylist(Song newSong){
        return true;
    }





}
