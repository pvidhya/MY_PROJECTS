<html>
<%@ page import ="explorer.*" %>


<%

String username=request.getParameter("username");
String pass=request.getParameter("pass");
MySQLDataStoreUtilities ms= new MySQLDataStoreUtilities();
String errorMsg="";

String path="";
if(ms.userLoginValidation(username,pass)!=0)
{
path="/explorer_userHome.jsp";
session.setAttribute("username", username);
}
else
{
	errorMsg="Check username and password";
  path="/explorer_login1.jsp";

}
session.setAttribute("errorMsg", errorMsg);
response.sendRedirect(request.getContextPath() + path);


%>

</html>
