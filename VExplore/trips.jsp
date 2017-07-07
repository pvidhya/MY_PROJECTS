<!DOCTYPE html>

<%@ page import ="explorer.*" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="java.util.*" %>
    <% 
       MySQLDataStoreUtilities ms= new MySQLDataStoreUtilities();
int id=ms.userID((String)session.getAttribute("username"));
User user=ms.userDetails(id);
       %>

<html>
  <head>
    <title>Select Iteneraries</title>
      
      <link rel="stylesheet" href="styles.css" type="text/css" />
<link rel="stylesheet" href="input.css" type="text/css" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
      
      <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    
    <!-- Load jQuery JS -->
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <!-- Load jQuery UI Main JS  -->
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    
    <!-- Load SCRIPT.JS which will create datepicker for input field  -->
    <script>
     
		function changeview(url){
			document.getElementById('dispcont').src = url;
		}
 
   $(function(){$('.datepick').datepicker();
               dateFormat : 'yyyy-MM-dd'
               }); 
    </script>
    
      
	<script>
	    var map;
        var markers = [];
        
        function initMap() {
		
		map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: 37.0902, lng: -95.7129},
          zoom: 5
        });
		
		var input = (document.getElementById('pac-input'));
		map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
		
		var autocomplete = new google.maps.places.Autocomplete(input);
        autocomplete.bindTo('bounds', map);
		var infowindow = new google.maps.InfoWindow();
            
        var selectedplaces = [];
          
		autocomplete.addListener('place_changed', function() {
			
			var marker = new google.maps.Marker({ map: map, anchorPoint: new google.maps.Point(0, -29) });
            markers.push(marker);
			place = autocomplete.getPlace();
			map.setCenter(place.geometry.location);
			map.setZoom(10);
			marker.setPosition(place.geometry.location);
			marker.setVisible(true);
			var address = '';
			if(place.address_components){
				if (place.address_components) {
					address = [
					(place.address_components[0] && place.address_components[0].short_name || ''),
					(place.address_components[1] && place.address_components[1].short_name || ''),
					(place.address_components[2] && place.address_components[2].short_name || '')
					].join(' ');
				}
			}
            
			var form = document.getElementById("places");
			var tomakedate = document.getElementById("fordate");
			
            //document.getElementById("inputlat").value=place.geometry.location.lat();
            //document.getElementById("inputlong").value=place.geometry.location.lng();
            
            
			var bounds = new google.maps.LatLngBounds();
            var inputcal='';
			var input_elements = document.forms['places'].getElementsByClassName("new");
            
            var getele = document.getElementById("submit");
         
            for(i=0;i<input_elements.length;i+=1){
				
                    var r = confirm("Do you want to add this place to itinerary?");
                    if (r == true) 
                    {
                        alert("Added to Itinerary");
                        var res = input_elements[i].value;
                        
                        res = place.geometry.location.lat() +','+ place.geometry.location.lng();
                        
                     //   alert("i value"+i.toString());
                        selectedplaces.push(res);
                        
                        //inputcal = document.createElement("input");
                        //inputcal.type = "text";
                      //  inputcal.name = "datepicker" + i.toString();
                      //  inputcal.setAttribute("id","datepicker" + i.toString());
                     //   inputcal.className = "datepick";
                       // tomakedate.appendChild(inputcal);
                            
                        
                      //  alert("selected places are:"+selectedplaces);
                        bounds.extend(markers[i].getPosition());
                    }
                    else 
                    {
                        
                    }
                
            }
			console.log(selectedplaces);
            document.getElementById("inputlatlong").value=selectedplaces.toString();
			map.fitBounds(bounds);
        });
            
            
	  }
    </script>
  </head>
    
    
<div id="container">

       <!--<nav>
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
			</nav>-->
	<div id="body" class="width">
	<div>
	   <form id="places" method="post" action="insertItinerary.jsp">
          <table style="width:0px;">
            <tr>
                <td>Itinerary Desc:</td>
                <td><input type="text" id="itinerary_Desc" name="itinerary_Desc" required="required" /></td>
              
             
            </tr> 
          
          <tr>
                <td>Date:</td>
                <td> <input type="text" id="inputlatlong" name="inputlatlong" hidden/>
             <input type="text" id="datepicker" name="datepicker"  class="datepick"/>
              </td></tr>
            <tr>
                <td>Select place:</td>
                <td><input id="pac-input"type="text" class="new" placeholder="Enter a location" >
	               <section style="width:70%;height:70%;position:fixed">
		           <div id="map" style="height: 90%;width: 90%;overflow: hidden;"></div>
		            
	               </section>
                </td>
            </tr>
              
             
              
              <tr>
                    <td><input type="submit" id="submit" name="submit" value="Submit"/></td>
              </tr>
              
              </table>
              
            
              
              
          
           </form>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBG_lNJHrt_X8sM3IqXhdRFu364g0HSpvg&libraries=places&callback=initMap" async defer></script>
 
 
            
    </div>  
	
                 
    </div>



</div>
</body>
 </html>    
    
    
 