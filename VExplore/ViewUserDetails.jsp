<!doctype html>
<%@ page import="explorer.*,java.util.*" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>VEXPLORE</title>
		<link rel="stylesheet" href="styles.css" type="text/css" />
		<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
	</head>
	<body>
		<div>
			<% 
				int id = Integer.parseInt(request.getParameter("id"));
				User u = UserAccounts.getInstance().getUser(id);
			%>
			<table style="width:500px;border:solid">
				<tr>
					<td style="border:none" rowspan="3"><img style="width:50px;height:50px" src="/Explorer/person.png" />
				</tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td><label>First Name: </label></td>
					<td><label><%=u.fname%></label></td>
				<tr>
				<tr>
					<td><label>Last Name: </label></td>
					<td><label><%=u.lname%></label></td>
				<tr>
				<tr>
					<td><label>Email ID: </label></td>
					<td><label><%=u.email_id%></label></td>
				<tr>
				<tr>
					<td><label>Contact: </label></td>
					<td><label><%=u.contact%></label></td>
				<tr>
			</table>
		</div>
	</body>
</html>