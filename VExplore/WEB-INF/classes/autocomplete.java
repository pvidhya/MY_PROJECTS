//package explorer;
import java.io.*;
import java.io.IOException;
import java.util.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

public class autocomplete extends HttpServlet {
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
     {

try
{
response.setContentType("text/html");
//System.out.println("In auto complete");	
StringBuffer sb = new StringBuffer();
String searchId= request.getParameter("searchId");
String action= request.getParameter("action");
boolean namesAdded = false;
PrintWriter out = response.getWriter();

//response.getWriter().write("<user>" +"test" + "</user>");
// System.out.println("Hello");
if (action.equals("complete"))
{
if (!searchId.equals(""))
{ 
Class.forName("com.mysql.jdbc.Driver").newInstance();
Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/explorer","root","root");
Statement stmt=con.createStatement();
String selectCustomerQuery="select * from usr_regist";

String xml="";
ResultSet rs = stmt.executeQuery(selectCustomerQuery);
while(rs.next())
{
    String uname=rs.getString("username");
    int uid=rs.getInt("user_id");
    
    //out.println(rs.getString("username"));
    if(uname.toLowerCase().startsWith(searchId))
    {
        xml=xml+"<user>";
        xml=xml+"<user_id>" + uid + "</user_id>";
        xml=xml+"<username>" +uname+ "</username>";
        xml=xml+"</user>";
        
    }
}
if(xml!=null || !xml.equals(""))
{
response.setContentType("text/xml");
response.getWriter().write("<users>" + xml + "</users>");
}
//AjaxUtility a=new AjaxUtility();
//sb=a.readdata(searchId);
/*if(sb!=null || !sb.equals(""))
{
namesAdded=true;
}
if (namesAdded)
{
	//System.out.println(sb.toString());	
response.setContentType("text/xml");

out.println(sb.toString());
//response.getWriter().write("<user>" + sb.toString() + "</user>");
}*/
}
}
if(action.equals("lookup")){
	response.setContentType("text/html");
	//PrintWriter out = response.getWriter();
	String id = request.getParameter("searchId");
    //int id= Integer.parseInt(user_id);
    //response.setAttribute("id", id);
 //out.println(id);
String path="/explorer_userProfile.jsp";

response.sendRedirect(request.getContextPath() + path+"?origin=Search&id="+id);
	//Statement stmt = null;

        //String query = "SELECT * from usr_regist where user_id=\""+ user_id+"\"";

}
}
catch(Exception e){
		}
}
protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
                processRequest(request,response);
                
                
            } 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
                processRequest(request,response);
            }
}
		