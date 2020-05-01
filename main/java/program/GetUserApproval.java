package program;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import java.awt.*;
import java.net.URI;

public class GetUserApproval {

    private static final String clientId = "<your client ID>"
    private static final String clientSecret = "<your client secret>"
//    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/birthdayplaylist/result");
    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://birthday-playlist.us-east-2.elasticbeanstalk.com/result");

    public static SpotifyApi api = new SpotifyApi.Builder().setClientId(clientId).setClientSecret(clientSecret).setRedirectUri(redirectUri).build();

    public static String mkURI() {
        AuthorizationCodeUriRequest authorizationCodeUriRequest = api.authorizationCodeUri()
                .scope("playlist-modify-public,user-read-private,ugc-image-upload")
                .show_dialog(true)
                .build();
        final URI uri = authorizationCodeUriRequest.execute();
        return uri.toString();
    }

    private static SpotifyApi makeNewCreds(String codeFromURI) {
        AuthorizationCodeRequest authorizationCodeRequest = api.authorizationCode(codeFromURI).build();
        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

            api.setAccessToken(authorizationCodeCredentials.getAccessToken());
            api.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

            return api;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        throw new IllegalArgumentException("Was unable set credentials.");
    }
}
