<!doctype html>
<%@ page import ="explorer.*" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="java.util.*" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>monopoly - Free CSS Template by ZyPOP</title>
<link rel="stylesheet" href="styles.css" type="text/css" />
<link rel="stylesheet" href="input.css" type="text/css" />

<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
</head>
<body>
<%@ page import ="explorer.*" %>
<%MySQLDataStoreUtilities ms= new MySQLDataStoreUtilities();
int id=ms.userID((String)session.getAttribute("username"));
User user=ms.userDetails(id);

ResultSet rs=ms.visitedTrips(id);
ResultSet rs1=ms.plannedTrips(id);


%>


<div id="container">

       <nav>
	<div class="width">
    		<ul>

            <li class="start selected" style="float: left; margin-left: -300px;"><a href="explorer_profile.jsp">
            <h4 style="color: white;" ><%=user.fname+" "+user.lname%></h4></a></li>
        		<li class=""><a href="explorer_userHome.jsp">Home</a></li>
                <li class="selected"><a href="explorer_Profile.jsp">Profile</a></li>
        	    <li class=""><a href="explorer_Friends.jsp">Friends</a></li>
         	   	<!--<li><a href="explorer_Message.jsp">Messages</a></li>-->
                <li><a href="explorer_home.jsp">Logout</a></li>
        	</ul>
	</div>
    </nav>

    <div id="body" class="width" style= "margin-left: -1px;">
                 

<aside class="sidebar small-sidebar right-sidebar" style= "width: 30%; padding-left: 20px;">
   <img src= "images/user_icon.png" style= "width: 25px; height: 25px; padding-bottom: 30px;">
 
<!--             <ul>    
               <li>
                    <h4>Blocklist</h4> -->
                    <ul class="blocklist" style= "">
                    <h4 style="color: black;" ><%=user.fname+" "+user.lname%></h4></a></li>
                    <h4 style="color: black;" ><%=user.contact %></h4></a></li>
                    <h4 style="color: black; text-transform: lowercase;" ><%=user.email_id %></h4></a></li>
                        <form method= "Profile_update.jsp" action= "post">
                            <input type= "submit" id= "update" value = "Update">
                        </form>
            
                    <!-- <li><h4><a class="selected" href="explorer_userHome.jsp">Feeds</a></h4></li>
                        <li><h4><a href="trips.jsp">Trips</a></h4></li> -->
                    </ul>
                </li>
                
            </ul> 
</aside>
</div>
<style>
    #update{
font-family:  sans-serif;
text-transform: uppercase;
outline: 0;
margin-top: 30px;
background: #3882D6;
width: 50%;
border: 0;
padding: 10px;
color: #FFFFFF;
font-size: 14px;
-webkit-transition: all 0.3 ease;
transition: all 0.3 ease;
cursor: pointer;
    
}

</style>
</div>

 

<div class="trips" style= "margin-top: 10px;">
<h3 style= "text-decoration: underline; color: black;">Trips Taken</h3>

<h4 style= "text-transform: lowercase;">
<table border= "1px" style= "width: 300px; margin-top: -60px;">
<%
while(rs.next()){

%>
<tr><td><%=rs.getString("itinerary_desc")%>
<%=rs.getDate("visited_date")%></td></tr></br>

<%}%>
</table>
</h4>

</h4>
<h3 style= "padding-left: 400px;text-decoration: underline; color: black; margin-top: 30px;">Trips Planned</h3>
<h4 style= "padding-left: 400px;text-transform: lowercase;">

<table border= "1px" style= "width: 300px;">
<%
while(rs1.next()){

%>
<tr><td><%=rs1.getString("itinerary_desc")%>
<%=rs1.getDate("visited_date")%></td></tr></br>

<%}%>
</table>
</h4>
</div>


</body>
 </html>
