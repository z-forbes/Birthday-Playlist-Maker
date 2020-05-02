<%@ page import ="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<%
String shareURL = (String) request.getAttribute("shareURL");
%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta property="og:type"          content="website" />
  <meta property="og:title"         content="My Birthday Playlist" />
  <meta property="og:description"   content="Look at what was popular when I was born!" />
  <meta property="og:image"         content="<%= shareURL %>" />
<link rel="stylesheet" href="stylesheet.css">
<title>Birthday Playlist</title>
</head>
<body>
<center>
<h1>
    <a href="http://birthday-playlist.us-east-2.elasticbeanstalk.com/" style="color:inherit">Party Like it's Your Birthday</a>
</h1>
<%
String playlistID = (String) request.getAttribute("playlistID");

String embedSrc = "https://open.spotify.com/embed/playlist/" + playlistID;

%>
<h3>Here are the top songs from my birthday, what do you think?<h3>
<iframe src="<%= embedSrc %>" width="350" height="400" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
<div>
<p>I wonder what yours would look like...</p>
<a href="http://birthday-playlist.us-east-2.elasticbeanstalk.com/"> <h2>Make Your Own</h2></a>
</div>
<br>
 <div>
        <p>Made by <a href="https://lewisforbes.com/">Lewis Forbes</a></p>
        <p>Code available on <a href="https://github.com/lewisforbes/Birthday-Playlist-Maker">GitHub</a></p>
    </div>
</body>
</html>