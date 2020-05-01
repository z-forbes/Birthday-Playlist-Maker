package program.run.addToPlaylist;

import com.wrapper.spotify.model_objects.specification.Track;
import java.util.ArrayList;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
public class TrackFinder {
    public static ArrayList<Track> getTracks(SpotifyApi api, ArrayList<String> songs, ArrayList<String> artists) {
        ArrayList<Track> output = new ArrayList<Track>();
        for (int i=0; i<songs.size(); i++) {
            Track currentTrack = searchForTrack(api, songs.get(i) + " " + artists.get(i));
            if (currentTrack != null) {
                output.add(currentTrack);
            }
        }
        return output;
    }

    public static Track searchForTrack(SpotifyApi api, String searchTerm) {
        SearchTracksRequest searchTracksRequest = api.searchTracks(searchTerm).limit(1).build();
        try {
            final Paging<Track> trackPaging = searchTracksRequest.execute();
            return trackPaging.getItems()[0];
        } catch (Exception e) { }
        return null;
    }
}
