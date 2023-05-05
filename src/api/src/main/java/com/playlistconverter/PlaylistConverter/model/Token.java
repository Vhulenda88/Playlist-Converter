package com.playlistconverter.PlaylistConverter.model;

import java.net.URI;
import java.net.URISyntaxException;

public class Token {
    public final String grant_type = "authorization_code";//client_credentials
    public String code;

    public Token(String code) throws URISyntaxException {
        this.code = code;
    }
}
