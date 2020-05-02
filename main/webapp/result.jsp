<%@ page import ="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="stylesheet.css">
<title>Birthday Playlist</title>
</head>
<body>
<center>
<h1>
    <a href="http://birthday-playlist.us-east-2.elasticbeanstalk.com/" style="color:inherit">Check your Spotify!</a>
</h1>
<%
String playlistID = (String) request.getAttribute("playlistID");

String embedSrc = "https://open.spotify.com/embed/playlist/" + playlistID;
String shareURL = "https://www.facebook.com/plugins/share_button.php?href=http%3A%2F%2Fbirthday-playlist.us-east-2.elasticbeanstalk.com%2Fshared%3Fid%3D" + playlistID + "&layout=button&size=large&width=77&height=28&appId";
String fancyShareURL = "https://www.facebook.com/sharer/sharer.php?u=http%3A%2F%2Fbirthday-playlist.us-east-2.elasticbeanstalk.com%2Fshared%3Fid%3D" + playlistID + "&amp;src=sdkpreparse";

%>
<h3>Your playlist has already been added to your library, here's a preview though...<h3>
<iframe src="<%= embedSrc %>" width="350" height="400" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>

<br><br>
<p>Good? Bad? Let the world know!<p>

<iframe src="<%=shareURL %>" width="77" height="28" style="border:none;overflow:hidden" scrolling="no" frameborder="0" allowTransparency="true" allow="encrypted-media"></iframe>

<br><br>
<a href="http://birthday-playlist.us-east-2.elasticbeanstalk.com/"> <h2>Make Another</h2></a>

<br>
 <div>
        <p>Made by <a href="https://lewisforbes.com/">Lewis Forbes</a></p>
        <p>Code available on <a href="https://github.com/lewisforbes/Birthday-Playlist-Maker">GitHub</a></p>
    </div>
</body>
</html>