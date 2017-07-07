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
			int iti_id = Integer.parseInt(request.getParameter("itineraryid"));
			List<String> li = MySQLDataStoreUtilities.getInstance().getUserNamesIti(iti_id);
			
		%>
		<div id="container">
			<table border= "1px" style= "width: 300px;">
				<% for(int i = 0; i < li.size();i++){ %>
					<tr><td><h3 style= "text-transform: capitalize; color: black; width: 360px;"><%=li.get(i)%></td>
					</h3></tr>
				<%}%>
			</table>
		</div>
	</body>
 </html>
