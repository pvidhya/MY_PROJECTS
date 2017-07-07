<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>VExplorer</title>
		<link rel="stylesheet" href="styles.css" type="text/css" />
		<link rel="stylesheet" href="input.css" type="text/css" />
		<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
	</head>
	<script>
		function changeview(url){
			document.getElementById('dispcont').src = url;
		}
	</script>
	<body style="width:100%;height:100%;">
	<%@ page import ="explorer.*" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="java.util.*" %>
<%@ page import ="java.util.Arrays" %>
<%@ page import ="java.text.DateFormat" %>
<%@ page import ="java.text.SimpleDateFormat" %>
<%@ page import ="java.util.Calendar" %>
	<%
		MySQLDataStoreUtilities ms = new MySQLDataStoreUtilities();
		int id = ms.userID((String)session.getAttribute("username"));
      
		User user = ms.userDetails(id);
       
  

String datei;
String desc;
String inputlatlongofmap;
   
  
   datei=request.getParameter("datepicker");
   desc=request.getParameter("itinerary_Desc");
   inputlatlongofmap=request.getParameter("inputlatlong");
   String[] values;
   values = inputlatlongofmap.split(",");
 
   DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
   Calendar calobj = Calendar.getInstance();
   String currentdate;
   currentdate = df.format(calobj.getTime());
   System.out.println(df.format(calobj.getTime()));
   System.out.println("dte selected"+datei);
   
   Random rand = new Random();

   int  n = rand.nextInt(50) + 1;
   
  
   
    String first;
    String second;
    for(int i = 0; i < values.length; i += 2) {
        
            first = values[i];
            second = null;
            if(values.length > i + 1){
                second = values[i+1];
            }
            System.out.println("First [" + first + "] - Second [" + second + "]");
    
            int status = ms.insertItinerary(id, desc, first, second, currentdate, datei, n); 
    
            if(status != 0){
                System.out.println("added from jsp");
            }else{
                 System.out.println("NOT added from jsp");
            }
                                      
    }
    
    

    
   
   System.out.println("lattitude"+inputlatlongofmap);
   
   System.out.println("datei"+datei);
   System.out.println("desc"+desc);
   
   
   
   
  
   

%>
		<div id="container">
			<nav>
				<div>
					<ul>
						<li class="start selected" style="float: left; margin-left: -400px;"><a href="explorer_profile.jsp"><h4 style="color: white;" ><%=user.fname+" "+user.lname%></h4></a></li>
						<li class="selected"><a href="explorer_userHome.jsp">Home</a></li>
						<li class=""><a href="explorer_Profile.jsp">Profile</a></li>
						<li class=""><a href="explorer_Friends.jsp">Friends</a></li>
						<li><a href="explorer_Message.jsp">Messages</a></li>
						<li><a href="explorer_home.jsp">Logout</a></li>
					</ul>
				</div>
			</nav>
			<nav>
				<div style="position:fixed;left:0px">
					<ul>
						<li><a class="selected" href="#" onclick="changeview('ViewItineraries.jsp')">Feeds</a></li>
						<li><a href="#" onclick="changeview('ViewUserItinerary.jsp')">Trips</a></li>
						<li><a href="trips.jsp">Create Trips</a></li>
						<li><a href="Explorer">View Recommendations</a></li>
						<li><a href="#" onclick="changeview('ItineraryReq.jsp')">Itinerary Requests</a></li>
						<li><a href="#" onclick="changeview('friend_request.jsp')">Friend Requests</a></li>
					</ul>
				</div>
			</nav>
			<div id="body" class="width">
                <div>      
        
          <table style="width:0px;">
            <tr>
                <td>Itinerary has been added!</td>
            </tr> 
            
          </table>
              
            
              
              
          
    
 
            
    </div>  
				<!--<aside class="sidebar small-sidebar left-sidebar" style= "width: 30%; padding-left: 10px;">
					<ul>
					   <li>
							<!-- <h4>Blocklist</h4> -->
							<!--<ul class = "blocklist"  style="width: %">
								<li><h4><a class="selected" href="explorer_userHome.jsp">Feeds</a></h4></li>
								<li><h4><a href="trips.jsp">Trips</a></h4></li>
							</ul>
						</li>
					</ul>
				</aside>-->
				<div>
					<iframe scrolling="yes" id="dispcont" style="position:fixed;left:0px;width:1800px;height:800px" src="ViewItineraries.jsp" ></iframe>
				</div>
			</div>
		</div>
	</body>
 </html>





















