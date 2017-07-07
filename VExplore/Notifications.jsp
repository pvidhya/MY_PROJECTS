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
	<%
		MySQLDataStoreUtilities ms = new MySQLDataStoreUtilities();
		int id = ms.userID((String)session.getAttribute("username"));
	
		User user = ms.userDetails(id);
	%>
		<div id="container">
			<nav>
				<div style="position:fixed;left:0px">
					<ul>
						<li><a href="#" onclick="changeview('notifications')">Requests</a></li>
						<li><a href="#" onclick="changeview('dnotifications')">Notifications</a></li>
					</ul>
				</div>
			</nav>
			<div id="body" class="width">
				<div>
					<iframe scrolling="yes" id="dispcont" style="position:fixed;left:0px;width:1800px;height:800px" src="notifications" ></iframe>
				</div>
			</div>
		</div>
	</body>
 </html>
