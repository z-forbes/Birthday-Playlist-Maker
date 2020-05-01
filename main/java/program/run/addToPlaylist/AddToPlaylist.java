package program.run.addToPlaylist;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.playlists.AddItemsToPlaylistRequest;
import java.util.ArrayList;

public class AddToPlaylist {

    public static void addToPlaylist(SpotifyApi api, ArrayList<Track> tracksToAdd, String playlistId) {
        String[] uris = new String[tracksToAdd.size()];
        for (int i = 0; i < tracksToAdd.size(); i++) {
            uris[i] = tracksToAdd.get(i).getUri();
        }

        AddItemsToPlaylistRequest addItemsToPlaylistRequest = api.addItemsToPlaylist(playlistId, uris).build();
        try {
            final SnapshotResult snapshotResult = addItemsToPlaylistRequest.execute();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
