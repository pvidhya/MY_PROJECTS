<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>VExplorer</title>
		<link rel="stylesheet" href="styles.css" type="text/css" />
		<link rel="stylesheet" href="input.css" type="text/css" />
		<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
	</head>
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
	<script>
		function changeview(url){
			document.getElementById('dispcont').src = url;
		}
	</script>
	<body style="width:100%;height:100%;">
		<%@ page import ="explorer.*,java.util.*;" %>
		<%
			MySQLDataStoreUtilities ms = new MySQLDataStoreUtilities();
			int id = ms.userID((String)session.getAttribute("username"));
			List<Notification> li = MySQLDataStoreUtilities.getInstance().getFriendReq(id);
			User user = ms.userDetails(id);
			
		%>
		<div id="container">
			<table border= "1px" style= "width: 300px;">
				<% for(int i = 0; i < li.size();i++){ %>
					<form action= "acceptjoinreq" method= "POST">
						<input type = "hidden" name= "sender" value=<%=li.get(i).sender%>>
						<input type = "hidden" name= "reciever" value=<%=li.get(i).receiver%>>
						<input type = "hidden" name= "itinry_id" value=<%=li.get(i).notif_frn_id%>>
						<% User r = ms.userDetails(li.get(i).sender); %>
						<td><h3 style= "text-transform: capitalize; color: black; width: 360px;"><%=ms.getItineraryDesc(li.get(i).notif_frn_id)%></td>
						<td><h3 style= "text-transform: capitalize; color: black; width: 360px;"><%=r.getFullName()%></h3></td>
						<td><input type= "submit" id= "request" name= "Request" value= "Accept" ></td>
						<td><input type= "submit" id= "request" name= "Request" value= "Reject" ></td>
						</h3>
					</form>
				<%}%>
			</table>
		</div>
	</body>
 </html>
