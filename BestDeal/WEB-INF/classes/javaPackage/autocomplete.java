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
StringBuffer sb = new StringBuffer();
boolean namesAdded = false;
if (action.equals("complete"))
{
if (!searchId.equals(""))
{ AjaxUtility a=new AjaxUtility();
sb=a.readdata(searchId);
if(sb!=null || !sb.equals(""))
{
namesAdded=true;
}
if (namesAdded)
{
response.setContentType("text/xml");
response.getWriter().write("<products>" + sb.toString() + "</products >");
}
}
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
