
<%@ page import ="explorer.*" %>
<%MySQLDataStoreUtilities ms= new MySQLDataStoreUtilities();

String req=request.getParameter("Request");
String id1= request.getParameter("id1");
String id= request.getParameter("id");
ms.accept_request(id1,id,req);


response.sendRedirect(request.getContextPath() +"/friend_request.jsp");
%>