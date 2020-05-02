package program;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsItemsRequest;

public class GetImageURLForShare {

    private static SpotifyApi api = GetUserApproval.api;

    public static String getImageURL(String playlistID) {
        try {
            Track firstTrack = getFirstTrack(playlistID);
            return firstTrack.getAlbum().getImages()[0].getUrl();
        } catch (Exception e) { }
        return null;
    }

    private static Track getFirstTrack(String playlistID) {
        GetPlaylistsItemsRequest getPlaylistsItemsRequest = api.getPlaylistsItems(playlistID).build();

        try {
            final Paging<PlaylistTrack> playlistTrackPaging = getPlaylistsItemsRequest.execute();

            return (Track) playlistTrackPaging.getItems()[0].getTrack();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
