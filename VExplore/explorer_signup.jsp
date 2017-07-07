<%@ page import ="explorer.*" %>
<%@ page import = "java.util.Date" %>

<%

String fname=request.getParameter("fname");
String lname=request.getParameter("lname");
String username=request.getParameter("username");
String contact=request.getParameter("contact");
String email_id=request.getParameter("email_id");
String pass=request.getParameter("pass");
String cpass=request.getParameter("cpass");
String errorMsg="";
MySQLDataStoreUtilities ms= new MySQLDataStoreUtilities();


User user = new User(ms.userID(),username,pass,fname,lname,email_id,contact);


if (!(pass.equals(cpass)))
{
 errorMsg="Password doesn't Match.";
}
else
{

	if (ms.userValidation(username))
	{
		ms.UserUpdate(user);
		errorMsg="Username added";
	}
	else
	{
		errorMsg="Username already exists";
	}

}
session.setAttribute("errorMsg", errorMsg);
response.sendRedirect(request.getContextPath() + "/explorer_signup1.jsp");



%>
