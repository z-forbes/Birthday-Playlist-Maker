<%@ page import ="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="stylesheet.css">
<title>Birthday Playlist</title>
</head>
<body>
<center>
<h1>
    Check your Spotify!
</h1>
<%
String playlistID = (String) request.getAttribute("playlistID");

String embedSrc = "https://open.spotify.com/embed/playlist/" + playlistID;
String shareURL = "http://localhost:8080/BirthdayPlaylistWA/shared?id=" + playlistID;
String fancyShareURL = "https://www.facebook.com/sharer/sharer.php?u=http%3A%2F%2Flocalhost%3A8080%2FBirthdayPlaylistWA%2Fshared%3Fid%" + playlistID + "&amp;src=sdkpreparse";

%>
<h3>Your playlist has already been added to your library, here's a preview though...<h3>
<iframe src="<%= embedSrc %>" width="350" height="400" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
<div>
<p>Good? Bad? Let the world know:</p>
<div>
<p>Have another try:</p>
<a href="http://birthday-playlist.us-east-2.elasticbeanstalk.com/"> <button type="button">Make another</button></a>
</div>
        <br><br>
        <p>Made by <a href="https://lewisforbes.com/"> Lewis Forbes</a></p>
    </div>
</body>
</html>