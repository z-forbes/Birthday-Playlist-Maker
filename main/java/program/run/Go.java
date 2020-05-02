package program.run;

import com.sample.ResultServlet;
import com.sample.SharedServlet;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import program.GetImageURLForShare;
import program.GetUserApproval;
import program.run.addToPlaylist.*;
import program.run.getData.*;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.specification.Track;

import java.util.ArrayList;

public class Go {

    public static String go(String givenDate, String code) {
        try {
            Date date = new Date(givenDate);
            SpotifyApi api = mkNewApi(code);

            SongGetter top40 = new SongGetter(date);

            String plName = "Party Like it's " + date.getPrettyDate();
            String plDescription = date.getYear() + "... what a time to be alive. | Make another at bit.ly/bdayplaylist";
            String playlistID = MakeNewPlaylist.newPL(api, plName, plDescription);
            ArrayList<Track> tracksToAdd = TrackFinder.getTracks(api, top40.returnSongs(), top40.returnArtists());
            System.out.println(tracksToAdd.size());
            AddToPlaylist.addToPlaylist(api, tracksToAdd, playlistID);
            AlbumCover.setCover(date, api, playlistID);
            SharedServlet.shareURL = GetImageURLForShare.getImageURL(playlistID);
            return playlistID;
        } catch (Exception e) {
            System.err.println("Something went wrong");
        }
        throw new IllegalArgumentException("Got to here in Go.go " + givenDate);
    }

    private static SpotifyApi mkNewApi(String code) {
        SpotifyApi output = GetUserApproval.api;
        AuthorizationCodeRequest authorizationCodeRequest = output.authorizationCode(code).build();
        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

            output.setAccessToken(authorizationCodeCredentials.getAccessToken());
            output.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

            return output;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        throw new IllegalArgumentException("Was unable set credentials.");
    }
}
