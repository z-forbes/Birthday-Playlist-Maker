package program.run.addToPlaylist;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.User;
import com.wrapper.spotify.requests.data.playlists.CreatePlaylistRequest;
import com.wrapper.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;

public class MakeNewPlaylist {

    public static String newPL(SpotifyApi api, String name, String decription) {
        CreatePlaylistRequest createPlaylistRequest = api.createPlaylist(getUserID(api), name)
                .collaborative(false)
                .public_(true)
                .description(decription)
                .build();

        try {
            final Playlist playlist = createPlaylistRequest.execute();
            return playlist.getId();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    private static String getUserID(SpotifyApi api) {
        GetCurrentUsersProfileRequest getCurrentUsersProfileRequest = api.getCurrentUsersProfile().build();
        try {
            User user = getCurrentUsersProfileRequest.execute();
            return user.getId();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}