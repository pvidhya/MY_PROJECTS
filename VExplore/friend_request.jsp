<%@ page import ="explorer.*" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="java.util.*" %>
<%@ page import ="explorer.*" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>VEXPLORE</title>
		<link rel="stylesheet" href="styles.css" type="text/css" />
		<link rel="stylesheet" href="input.css" type="text/css" />
		<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
	</head>
	
	<body>
<!--<div id="container">

       <nav>
    <div class="width">
            <ul>

		<%@ page import ="explorer.*" %>
		<%MySQLDataStoreUtilities ms= new MySQLDataStoreUtilities();
		int id1=ms.userID((String)session.getAttribute("username"));
		User user2=ms.userDetails(id1);

		ResultSet rs= ms.friend_request(id1);

		%>

		<li class="start selected" style="float: left; margin-left: -400px;"><a href="explorer_userHome.jsp">
			<h4 style="color: white;" ><%=user2.fname+" "+user2.lname%></h4></a></li>
			<li class=""><a href="explorer_userHome.jsp">Home</a></li>
			<li class="selected"><a href="explorer_Profile.jsp">Profile</a></li>
			<li class=""><a href="explorer_Friends.jsp">Friends</a></li>
			<li><a href="explorer_tweet.jsp">Twitter feeds</li>
			<li><a href="explorer_home.jsp">Logout</a></li>
			
		</ul>
    </div>
    </nav>-->

    <!--<div id="body" class="width" style= "margin-left: -1px;">
                 

<aside class="sidebar small-sidebar right-sidebar" style= "width: 30%; padding-left: 20px;">
 
            <ul>    
               <li>
                    <h4>Blocklist</h4> 
                       <ul class="blocklist" style= "width: %;">
                    <li><h4><a href="explorer_userHome.jsp">Feeds</a></h4></li>
                        <li><h4><a href="trips.jsp">Trips</a></h4></li>
                        <li><h4><a  class="selected" href="friend_request.jsp">Friend requets</a></h4></li>
                    </ul>
                </li>
</aside>
</div>
</div>-->
		<div class="trips" style= "margin-top: 10px;">
			<h3 style= "text-decoration: none; color: black;">Pending Friend Requests</h3>
			<h4 style= "padding-left: 400px; text-transform: lowercase;">

			<table border= "1px" style= "width: 300px;">
			<%
			while(rs.next()){
			String fnm=rs.getString("fname");
			String lnm=rs.getString("lname");
			int id =rs.getInt("id");
			%>

			<form action= "request_accept.jsp" method= "get">
			<input type = "hidden" name= "id" value= <%=id%>>
			<input type = "hidden" name= "id1" value= <%=id1%>>
			<!-- <tr><td align= "justify"><img src= "images/user_icon.jpg" style= "width: 25px; height: 25px; padding-bottom: 3px;padding-right: 3px;"> -->
			<td><h3 style= "text-transform: capitalize; color: black; width: 360px;"><%=fnm%>
			<%=lnm%></td>
			<td><input type= "submit" id= "request" name= "Request" value= "Accept-Request" ></td>
			<td><input type= "submit" id= "request" name= "Request" value= "Reject-Request" ></td>
			</h3>
			</form>
			<%}%>
			</table>
		<style>
			/*tr{
				text-align: left;
			}*/
			/*"  padding-bottom: 5px; padding-right: 5px; padding-left: 5px;"*/
			#request{
			/*margin-left: 20px;*/
			padding-top: 8px;
			font-family:  sans-serif;
			text-transform: uppercase;
			outline: 0;
			background: #3882D6;
			width: 150px;
			border: 0;
			padding: 15px;
			color: #FFFFFF;
			font-size: 14px;
			-webkit-transition: all 0.3 ease;
			transition: all 0.3 ease;
			cursor: pointer;
				
			}    	
		</style>
</div>
</body>
 </html>
