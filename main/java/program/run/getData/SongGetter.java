package program.run.getData;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class SongGetter {
    private ArrayList<String> songs = new ArrayList<String>();
    private ArrayList<String> artists = new ArrayList<String>();
    private Date date;

    public SongGetter(Date date) {
        try{
            getSongs(date);
            this.date = date;
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    public ArrayList<String> returnSongs() {return songs; }
    public ArrayList<String> returnArtists() {return artists; }

    private void getSongs(Date date) throws Exception {
        URL oracle = new URL("https://www.officialcharts.com/charts/uk-top-40-singles-chart/" + date.getFormattedDate() + "/750140/");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String webpage = "";
        String inputLine;
        boolean nextLineTitle = false;
        boolean nextLineArtist = false;
        while ((inputLine = in.readLine()) != null) {
            if (!inputLine.contains(">")) {
                continue;
            }
            if (nextLineTitle) {

                songs.add(inputLine.substring(inputLine.indexOf('>') + 1, inputLine.lastIndexOf('<')).replace("&#39;", "'"));
                nextLineTitle = false;
            }
            if (inputLine.contains("<div class=\"title\">")) {
                nextLineTitle = true;
                continue;
            }

            if (nextLineArtist) {
                artists.add(inputLine.substring(inputLine.indexOf('>') + 1, inputLine.lastIndexOf('<')).replace("&#39;", "'"));
                nextLineArtist = false;
            }
            if (inputLine.contains("<div class=\"artist\">")) {
                nextLineArtist = true;
            }
        }
        in.close();

        assert (songs.size() == artists.size());
        if (songs.size() == 0) {
            throw new IllegalArgumentException();
        }

    }

    public void printData() {
        if (songs.size() == 0) {
            throw new NullPointerException("Object created without data being assigned.");
        }
        System.out.println("program.run.getData.Date: " + date.getPrettyDate() + "\n");
        for (int i = 0; i < songs.size(); i++) {
            System.out.println("Song: " + songs.get(i) + " | Artist: " + artists.get(i));
        }
    }
}