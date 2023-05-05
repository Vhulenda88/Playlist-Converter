package com.playlistconverter.PlaylistConverter.spotify.model.constants;

public class ConvertPlaylist {

    public  static String Remaster = "remaster";
    public  static String RefineTrackSearch = "track:";
    public  static String RefineArtistSearch = "artist:";

    public  static String Track = "track";
    public  static String TrackURI = "spotify:track:";

    public static String RetrievePlaylistURL =
             "https://api.spotify.com/v1/playlists/{playlist_id}";

    public static String CreatePlaylistURL =
            "https://api.spotify.com/v1/users/{user_id}/playlists";

    public static String RemoveSongURL = "https://api.spotify.com/v1/playlists/{playlist_id}/tracks";


    public static String SearchSongURL = "https://api.spotify.com/v1/search?q={q}&type={type}";

    public static String InsertSongURL = "https://api.spotify.com/v1/playlists/{playlist_id}/tracks";
}
