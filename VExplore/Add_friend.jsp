
<%@ page import ="explorer.*" %>
<%@ page import ="java.sql.*" %>
<%@ page import ="java.util.*" %>


<%@ page import ="explorer.*" %>
<%MySQLDataStoreUtilities ms= new MySQLDataStoreUtilities();
int id1=ms.userID((String)session.getAttribute("username"));

String f= request.getParameter("id");

int id=Integer.parseInt(f);



ms.add_friend(id1, id);

// int notif_id= rs.getParameter(notif_id1);
response.sendRedirect(request.getContextPath() +"/explorer_userProfile.jsp?origin=Search&id="+id);
%>

