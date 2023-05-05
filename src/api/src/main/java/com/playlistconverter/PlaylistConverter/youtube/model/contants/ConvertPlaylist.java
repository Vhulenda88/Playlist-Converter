package com.playlistconverter.PlaylistConverter.youtube.model.contants;

public class ConvertPlaylist {

    public static String APIKey = "AIzaSyC10lnkF8ynenHJ6KgIUi0PwFS4emDm-Kk";
    public static String Part = "snippet";

    public static String PartSC = "snippet,contentDetails";

    public static String IdPart = "id";

    public static  String MixedPart = "snippet,id";

    public static String VideoKind = "youtube#video";

    public static String PlaylistKind = "youtube#playlist";

    public static String PlaylistItemKind = "youtube#playlistItem";
    public static String RetrievePlaylistURL = "https://youtube.googleapis.com/youtube/v3/playlistItems?part={part}&maxResults=25&playlistId={playlistId}&key={key}";

    public static  String CreatePlaylistURL = "https://youtube.googleapis.com/youtube/v3/playlists?part={part}&key={key}";

    public static String SearchSongURL = "https://youtube.googleapis.com/youtube/v3/search?part={part}&q={keyword}&key={key}";

    public static String InsertSongURL = "https://youtube.googleapis.com/youtube/v3/playlistItems?part={part}&key={key}";

    public static String RemoveSongURL = "https://youtube.googleapis.com/youtube/v3/playlistItems?id={id}&key={key}";

    public static String SearchPlaylistsURL = "https://youtube.googleapis.com/youtube/v3/playlists?part={part}&id={id}&key={key}";

}
