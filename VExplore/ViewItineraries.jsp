<!doctype html>
<%@ page import="explorer.*,java.util.*" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>VEXPLORE</title>
		<style>
			div.showity { width:500px; background-color:#677;}
			table { background-color:#677; }
			Label { font-family:Georgia,serif,Times; font-size:20px; }
			ul { list-style:none; }
			ul li { font-family:Georgia,serif,Times; font-size:18px; }
			ul li a { display:block; width:300px; height:28px; background-color:#333; border-left:5px solid #222; border-right:5px solid #222; 
					  padding-left:10px;text-decoration:none; color:#777; }
			ul li a:hover { -moz-transform:rotate(-5deg); -moz-box-shadow:10px 10px 20px #000000;
							-webkit-transform:rotate(-5deg); -webkit-box-shadow:10px 10px 20px #000000;
							transform:rotate(-5deg); box-shadow:10px 10px 20px #000000; color:#777;}
			
			/* Always set the map height explicitly to define the size of the div
		   * element that contains the map. */
		  #map {
			height: 100%;
		  }
		  /* Optional: Makes the sample page fill the window. */
		  html, body {
			height: 100%;
			margin: 0;
			padding: 0;
		  }
		</style>
		<!--<link rel="stylesheet" href="styles.css" type="text/css" />-->
		<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
	</head>
	<script>
		var map;
		var marker;
		
		function showLocation(lat,longd){
			marker = new google.maps.Marker({
			  position: { lat: lat,lng: longd },
			  map: map,
			});
			var bounds = new google.maps.LatLngBounds();
			bounds.extend(new google.maps.LatLng(lat, longd));
			map.fitBounds(bounds);
		}
		
		function sendrequest(itinry_id,senderid,recvid){
			var url = "http://localhost/Explorer/joinitinry?itinryid="+itinry_id+"&sender="+senderid+"&recvr="+recvid;
			var wnd = window.open(url,"Message Sent","width=200,height=100");
		}
		
		function initMap() {
			map = new google.maps.Map(document.getElementById('map'), {
			  center: {lat: 37.0902, lng: -95.7129},
			  zoom: 4
			});
		}
	</script>
	<body>
		<div style="position:fixed;width:500px">
			<ul>
			<% 
				MySQLDataStoreUtilities ms = new MySQLDataStoreUtilities();
				int id = ms.userID((String)session.getAttribute("username"));
				List<Itinerary> usr_its = MySQLDataStoreUtilities.getInstance().getRelatedItineraries(id);
				for(int i = 0; i < usr_its.size(); i++){
					Itinerary it = usr_its.get(i);
					User u = UserAccounts.getInstance().getUser(it.owner_id);
			%>
				<li>
					<div class="showity">
						<table>
							<tr>
								<td><Label><%= it.desc %></Label></td>
								<td><a href="#" onclick="showLocation(<%=it.latd%>,<%=it.longd%>)" >View Location</a></td>
							</tr>
							<tr>
								<td><a href="#" onclick="window.open('/Explorer/ViewUserDetails.jsp?id=<%=u.user_id%>')"><%= u.getFullName() %></a></td>
								<td><a href="#" onclick="sendrequest(<%=it.id%>,<%=id%>,<%=u.user_id%>)">Request to Join</a></td>
							</tr>
						</table>
					</div>
				</li>
				<br />
			<%
				}
			%>
			</ul>
		</div>
		<div style="position:fixed;width:1000px;height:600px;left:750px;top:10px" id="map"></div>
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD0tDyqcCigxxT12E00VCatJ3Aqglm4PpY&libraries=places&callback=initMap" async defer></script>
	</body>
</html>