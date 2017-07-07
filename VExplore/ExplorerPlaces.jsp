<!DOCTYPE html>
<html>
<head>
<title>VExplorer</title>
<link rel="stylesheet" href="styleRecomm.css" type="text/css" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<meta charset="utf-8">
<style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
       #map {
        height: 60%;	
		width: 50%;
		margin-top: 1em;
		border-radius: 1em;
		float:right;
      }
      
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      .controls {
        background-color: #fff;
        border-radius: 2px;
        border: 1px solid transparent;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
        box-sizing: border-box;
        font-family: Roboto;
        font-size: 15px;
        font-weight: 300;
        height: 29px;
        margin-left: 17px;
        margin-top: 10px;
        outline: none;
        padding: 0 11px 0 13px;
        text-overflow: ellipsis;
        width: 400px;
      }

      .controls:focus {
        border-color: #4d90fe;
      }
      
	input[type=submit] {
	padding: 17px 30px;
	color: #fff;
	font-family: 'Oxygen', sans-serif;
	background: #012B39;
	border: 1px solid #40A46F;
	cursor: pointer;
	font-size: 18px;
	transition: all 0.5s ease-out;
	-webkit-transition: all 0.5s ease-out;
	-moz-transition: all 0.5s ease-out;
	-ms-transition: all 0.5s ease-out;
	-o-transition: all 0.5s ease-out;
	outline:none;
	width: 50%;
	-webkit-appearance: none;
	border-radius: 1em;
	margin-top: 1em;
	}
	.submit{
	padding:5px 4px;
	text-align: center;
	}
	#floating-panel {
	  position: absolute;
	  top: 10px;
	  left: 25%;
	  z-index: 5;
	  background-color: #fff;
	  padding: 5px;
	  border: 1px solid #999;
	  text-align: center;
	  font-family: 'Roboto','sans-serif';
	  line-height: 30px;
	  padding-left: 10px;
	}
	.login-form input[type=text]{
	font-family: sans-serif;
	outline: 0;
	background: #f2f2f2;
	width: 90%;
	border: 0;
	margin: 0 0 15px;
	padding: 15px;
	box-sizing: border-box;
	font-size: 14px;
	margin-left: 1em;
	}
	.login-form input[type=radio]{
	font-family: sans-serif;
	outline: 0;
	background: #f2f2f2;
	width: 100%;
	border: 0;
	margin: 0 0 15px;
	padding: 15px;
	box-sizing: border-box;
	font-size: 14px;
	margin-left: 1em;
	}
	#View_Profile{
	font-family:  sans-serif;
	text-transform: uppercase;
	outline: 0;
	background: #3882D6;
	width: 100%;
	border: 0;
	padding: 15px;
	color: #FFFFFF;
	font-size: 14px;
	-webkit-transition: all 0.3 ease;
	transition: all 0.3 ease;
	cursor: pointer;
		
	}

	.right-sidebar {
		float: left;
		width: 200px;
		padding-right: 50px;
	}

	.small-sidebar {
	   width: 45%;
	}
	
    </style>
<script type="text/javascript">
var map;
var service;
var infowindow;
var myLatLng = {lat: -25.363, lng: 131.044};
var ltlng = [];
var latarray = new Array(); var longarray = new Array();
var flag = [];
var addArray = [];
var resArray = [];
function initialize() {
	
	map = new google.maps.Map(document.getElementById('map'), {
         zoom: 3,
         center: myLatLng
       });

	 var service = new google.maps.places.PlacesService(map);
	 var inputs = document.getElementsByTagName('input');
	 var infowindow2 = new google.maps.InfoWindow();
	
	 //alert(inputs.length);
	 for (i=0; i<inputs.length; i+=2){
	     	if (inputs[i].type == 'hidden'){
	     		ltlng.push(new google.maps.LatLng(inputs[i].value, inputs[i+1].value));
	     		flag.push(0);
	     	}    	
	 }
	 //alert(myLatLng);
	 
	//ltlng.push(new google.maps.LatLng(13.5, 79.2));    
   	//ltlng.push(new google.maps.LatLng(15.24, 77.16));

     map.setCenter(ltlng[0]);
     for (var i = 0; i <= ltlng.length; i++) {
			var index = 0;
			var delay = 100;
         marker = new google.maps.Marker({
             map: map,
             position: ltlng[i]
         });
         //geocodeLatLng(map,ltlng);
//          var latlngStr = ltlng[i].split(',', 2);
//        	 var latlng = {lat: latlngStr[0], lng: latlngStr[1]};

		var geocoder = new google.maps.Geocoder;
   	 	geocoder.geocode({'location': ltlng[i]}, function(results, status) {
   	  	    if (status === 'OK') {
   	  	      if (results[0]) {
   	  	        //map.setZoom(11);
   	  	        resArray.push(results[0].formatted_address);
   	  	   		//alert(resArray[i]);
   	  	      } else {
   	  	        //alert('No results found');
   	  	      }
   	  	    } else {
   	  	      //alert('Geocoder failed due to: ' + status);
				setTimeout(30000);
   	  	    }
   	  	  });
		         	 
         (function (i, marker) {

             google.maps.event.addListener(marker, 'click', function () {

                 if (!infowindow) {
                     infowindow = new google.maps.InfoWindow();
                 }

                 if(flag[i]==1)
                 {
                infowindow.setContent("Removed");
                flag[i]=0;
                if(addArray.length>0)
                		{ addArray.splice(i,1);}   
                   // alert("REmoved");
                  }
                 else
                 {
                 infowindow.setContent("Added");
                flag[i]=1;
                addArray.push(ltlng[i]);
                //alert("Added");
                 }

                 infowindow.open(map, marker);

             });
             
             google.maps.event.addListener(marker, 'mouseover', function(event) {
	  	        	infowindow2.setContent('<div><strong>' + 
	  	        			resArray[i] + '</div>');
	     					 infowindow2.open(map,marker);
	 						});
				    google.maps.event.addListener(marker, 'mouseout', function(event) {
				        infowindow2.close();
				    });
             
         })(i, marker);      
         
     }
                     
}
	function showPlaces() {
		  for(j =0 ; j< addArray.length; j++)
			{
		  alert(addArray[j]);
		}
	}
	
	function clearAll() {
		  for(j =0 ; j< ltlng.length; j++)
			{
		  addArray = [];
		}
	}
//window.onload = initialize;

</script>
</head>
<body onload="initialize()">
<%@ page session="true" %>
<%@ page import="java.util.*,explorer.*,java.io.*" %>

<%
	ArrayList<Place> pList = (ArrayList<Place>)request.getAttribute("placesList");
	session.setAttribute("placesList",pList);
	int length = pList.size(); int i = 0;
	MySQLDataStoreUtilities ms = new MySQLDataStoreUtilities();
	int id = ms.userID((String)session.getAttribute("username"));
	User user = ms.userDetails(id);
%>
	<div id="container">
			<nav>
				<div>
					<ul>
						<li class="start selected" style="float: left; margin-left: -400px;"><a href="explorer_profile.jsp"><h4 style="color: white;" ><%=user.fname+" "+user.lname%></h4></a></li>
						<li class="selected"><a href="explorer_userHome.jsp">Home</a></li>
						<li class=""><a href="explorer_Profile.jsp">Profile</a></li>
						<li class=""><a href="explorer_Friends.jsp">Friends</a></li>
						<!--<li><a href="explorer_Message.jsp">Messages</a></li>-->
						<li><a href="explorer_home.jsp">Logout</a></li>
					</ul>
				</div>
			</nav>
			<nav>
				<div style="position:fixed;left:0px">
					<ul>
						<li><a class="selected" href="#" onclick="changeview('ViewItineraries.jsp')">Feeds</a></li>
						<li><a href="#" onclick="changeview('ViewUserItinerary.jsp')">Trips</a></li>
						<li><a href="#" onclick="changeview('CreateItenirary.html')">Create Trips</a></li>
						<li><a href="#" onclick="changeview('Explorer')">View Recommendations</a></li>
					</ul>
				</div>
			</nav>
		</div>
	<div id="map" ></div>
		
	<div id = "data">
	<table>
	<% for (Place p : pList )
	{
	Double lati = new Double(p.getLatitude());
	String latitde = lati.toString(); 
	Double longi = new Double(p.getLongitude());
	String longitde = longi.toString(); 
	String pid = p.getId();
	%>		
	<tr>
	<td><%p.getName() ;%></td>
	<td><input type = "hidden" class="controls"  name = "latlngs1" id = "<%= latitde %>" value ="<%= latitde %>"/></td>
	<td><input type = "hidden" class="controls" name = "latlngs" id = "<%= longitde %>" value ="<%= longitde %>"/></td>
	</tr>
	<% } 
	%>
	</table></div>
	<div class="login-form" style="float: left;width: 50%;height: 60%;margin-top: 1em;">
	 <form class="login-form" method= "get" action="/Explorer/InterestUpdate" style="float:left;width: 50%;height: 60%;">
                    <input type="text" id="fname" name="fname" required="required" value= "<%=user.fname%>"  />
                    <input type="text" id="lname" name="lname" required="required" value= "<%=user.lname%>" />
                    <h4> Choose interest:</h4>

                    <div class= "interest"  style= "float:right;">
                          Beach<input type="radio"  value="Beach" name="interest">
                          Hiking<input type="radio" value="Hiking" name="interest">
                          Casinos<input type="radio" value="Casinos" name="interest">
                          Historical<input type="radio" value="Historical" name="interest">   
                     </div>  
                    <input type="Submit" value="Add interest" />

    </form>
	</div>
	<div class = "submit">
	<form id="myForm" method="post" action ="/Explorer/explorer_userHome.jsp">
	<input type = "Submit"  value= "Go back to previous page" style="float: right;">
	</form>	
	</div>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD0tDyqcCigxxT12E00VCatJ3Aqglm4PpY&libraries=places&callback=initialize">
</script>

</body>
</html>