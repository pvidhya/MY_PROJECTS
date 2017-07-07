<!doctype html>
<%@ page import = "java.io.*" %>
<%@ page import = "java.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>VEXPLORE</title>
<link rel="stylesheet" href="styles.css" type="text/css" />
<link rel="stylesheet" href="input.css" type="text/css" />

<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
</head>
<body>
<%@ page import ="explorer.*" %>
<%MySQLDataStoreUtilities ms= new MySQLDataStoreUtilities();
int id=ms.userID((String)session.getAttribute("username"));
User user=ms.userDetails(id);

%>
<div id="container">

       <nav>
	<div class="width">
    		<ul>
                <li class="start selected" style="float: left; margin-left: -300px;"><a href="explorer_userHome.jsp">
                <h4 style="color: white;" ><%=user.fname+" "+user.lname%></h4></a></li>
                <li class="selected"><a href="explorer_userHome.jsp">Home</a></li>
        		<li class=""><a href="explorer_Profile.jsp">Profile</a></li>
        	    <li class=""><a href="explorer_Friends.jsp">Friends</a></li>
         	   	<li><a href="explorer_tweet.jsp">Twitter feeds</a></li>
                 <li><a href="explorer_home.jsp">Logout</a></li>
        	</ul>

	</div>
    </nav>
    <div id="body" class="width" style= "margin-left: -1px;">

<aside class="sidebar small-sidebar right-sidebar" style= "width: 30%; padding-left: 20px;">
   <img src= "images/user_icon.jpg" style= "width: 25px; height: 25px; padding-bottom: 30px;">
 
<!--             <ul>    
               <li>
                    <h4>Blocklist</h4> -->
                    <ul class="blocklist" style= "">
                    <h4 style="color: black;" ><%=user.fname+" "+user.lname%></h4></a></li>
                    <h4 style="color: black;" ><%=user.contact %></h4></a></li>
                    <h4 style="color: black; text-transform: lowercase;" ><%=user.email_id %></h4></a></li>

                    </ul>
                </li>
                
            </ul> 
</aside>

</div>



<%ArrayList<String> lists = new ArrayList<String>();
ArrayList<String> tips = new ArrayList<String>();
ArrayList<String> plans = new ArrayList<String>();
try{
FileReader lstf=new FileReader("C:/apache-tomcat-7.0.34/webapps/explorer/python/TravelLists.txt");
FileReader tpf=new FileReader("C:/apache-tomcat-7.0.34/webapps/explorer/python/TravelTips.txt");
FileReader plnf=new FileReader("C:/apache-tomcat-7.0.34/webapps/explorer/python/TravelPlans.txt");
BufferedReader lstbr = new BufferedReader(lstf);
BufferedReader tpbr = new BufferedReader(tpf);
BufferedReader plnbr = new BufferedReader(plnf);
String line;
%>

<h3>Checkout these travel lists from our Twitter Account</h3><br>
<%
while ((line = lstbr.readLine()) != null ) {
String[] parts=line.split("http");
%>

<%=parts[0]%>
<a href=<%="http"+parts[1]%>><%="http"+parts[1]%></a>
<br>
<%
}
%>
<br>
<div  style="margin-left: 400px">
<h3>Checkout these travel tips from our Twitter Account</h3><br>
<%
while ((line = tpbr.readLine()) != null ) {
String[] parts=line.split("http");
%>

<%=parts[0]%>
<a href=<%="http"+parts[1]%>><%="http"+parts[1]%></a>
<br>
<%
}
%>

<br>

<h3>Checkout these destinations from our Twitter Account</h3><br>
<%
while ((line = plnbr.readLine()) != null ) {
String[] parts=line.split("http");
%>

<%=parts[0]%>
<a href=<%="http"+parts[1]%>><%="http"+parts[1]%></a>
<br>
<%
}
%>
</div>
<%
}
catch(Exception e){}
%>
</body>
 </html>
