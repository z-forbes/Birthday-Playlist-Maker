package program.run.getData;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.requests.data.playlists.UploadCustomPlaylistCoverImageRequest;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Base64;

public class AlbumCover {

    private static String savePath = "/tmp/cover.jpg";

    public static void setCover(Date date, SpotifyApi api, String playlistID) {
        String imageURL = getLink(date);
        if (imageURL == null) {
            imageURL = "https://thenypost.files.wordpress.com/2017/12/travolta.jpg?quality=80&strip=all&w=618&h=410&crop=1";
        }

        saveImage(imageURL);
        uploadCover(api, playlistID);
//        deleteImage();
    }

    private static String getLink(Date date) {
        try {
            URL url = new URL("https://www.officialcharts.com/charts/video-chart/" + date.getFormattedDate() + "/12/");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            String inputLine;
            boolean nextLine = false;
            while ((inputLine = in.readLine()) != null) {
                if (nextLine) {
                    nextLine = false;
                    if (!inputLine.contains("img src=\"/img/60x60.gif\"")) {
                        inputLine = inputLine.replace("/small?", "/large?");
                        inputLine = inputLine.substring(inputLine.indexOf("http"));
                        inputLine = inputLine.substring(0, inputLine.indexOf("\""));
                        return inputLine;
                    }
                }
                if (inputLine.contains("class=\"cover\"")) {
                    nextLine = true;
                }
            }
            in.close();
        } catch (Exception e) {
            throw new IllegalArgumentException("Something went wrong");
        }
        return null;
    }

    private static void saveImage(String urlStr) {
        try {
            BufferedImage image = null;

            URL url = new URL(urlStr);
            image = ImageIO.read(url);

            ImageIO.write(image, "jpg", new File(savePath));
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    private static void uploadCover(SpotifyApi api, String playlistId) {
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(savePath));
            String encodedImage = Base64.getEncoder().encodeToString(fileContent);
            encodedImage = encodedImage.substring(encodedImage.indexOf("/9j/"));

            UploadCustomPlaylistCoverImageRequest uploadCustomPlaylistCoverImageRequest = api
                    .uploadCustomPlaylistCoverImage(playlistId)
                    .image_data(encodedImage)
                    .build();

            uploadCustomPlaylistCoverImageRequest.execute();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Got here");
        }
    }

    private static void deleteImage() {
        File toDelete = new File(savePath);
        if (!toDelete.delete()) {
            throw new IllegalArgumentException("Was unable to delete file");
        }
    }
}