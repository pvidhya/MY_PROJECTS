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
     	response.setContentType("text/html");
try
{
response.setContentType("text/html");
System.out.println("In auto complete");	
StringBuffer sb = new StringBuffer();
String searchId= request.getParameter("searchId");
String action= request.getParameter("action");
boolean namesAdded = false;
// System.out.println("Hello");

if (action.equals("complete"))
{
if (!searchId.equals(""))
{ 
AjaxUtility a=new AjaxUtility();
sb=a.readdata(searchId);
if(sb!=null || !sb.equals(""))
{
namesAdded=true;
}
if (namesAdded)
{
	System.out.println(sb.toString());	
response.setContentType("text/xml");
response.getWriter().write("<products>" + sb.toString() + "</products>");
}
}
}
if(action.equals("lookup")){
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	String product = request.getParameter("searchId");
        // out.println(n);     
	Statement stmt = null;

        String query = "SELECT * from products where productID=\""+ product+"\"";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/example","root","root");  
            stmt = conn.createStatement();
           
            ResultSet rs = stmt.executeQuery(query);


		out.println("<!doctype html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
        out.println("<title>bestdeal</title>");
        out.println("<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />");
        out.println("");
        out.println("</head>");
        out.println("<body>");
        out.println("<div id=\"container\">");
        out.println("    <header>");
        out.println("    <h1><a href=\"/\">Best<span>Deal</span></a></h1>");
        out.println("<h2>"+product+"</h2>");
        out.println("        <h2>admit it..you need one</h2>");
        out.println("    </header>");
        out.println("    <nav>");
        out.println("    <ul>");
        out.println("        <li class=\"start selected\"><a href=\"index\">Home</a></li>");
        out.println("            <li>");
        out.println("            <div class=\"dropdown\">");        
        out.println("            <a href=\"#\">Products</a>");
        out.println("            <div class=\"dropdown-content\">");
        out.println("                <a href=\"ServletProduct\">Smart Phones</a>");
        out.println("                <a href=\"ServletTablet\">Tablets</a>");
        out.println("                <a href=\"ServletLaptop\">Laptops</a>");
        out.println("                <a href=\"ServletTV\">TV</a>");
        out.println("            </div>");
        out.println("            </div></li>");
        out.println("            <!-- <li><a href=\"#\">Services</a></li> -->");
        out.println("            <li class=\"\"><a href=\"deals.html\">Deals</a></li>");
        out.println("            <li class=\"\"><a href=\"deals.html\">Hello!</a></li>");
        out.println("            <li class=\"sign_up\"><a href=\"index\">logout</a></li>");
        out.println("            <li class=\"sign_up\"><a href=\"signup.html\">Create Customer</a></li>");
        out.println("            <li class=\"sign_up\"><a href=\"Update_Orders\">Update Orders</a></li>");
        
        // out.println("            <li class=\"sign_up\"><a href=\"Add_Products.html\">Add Products</a></li>");
        // out.println("            <li class=\"sign_up\"><a href=\"Delete_Products.html\">Delete Products</a></li>"); 
        out.println("             <li class= \"nav_bar_cart\">");
        out.println("             <a href= \"Cart_Orders\"><img src= \"images/cart.png\" style=\"width: 20px; height: 13px\">");
        out.println("                </a>");
        out.println("            </li>");
        out.println("        </ul>");
        out.println("    </nav>");
        out.println("<head>");
        out.println("<style>");
        out.println(".dropbtn {");
        out.println("    background-color: #4CAF50;");
        out.println("    color: white;");
        out.println("    padding: 16px;");
        out.println("    font-size: 16px;");
        out.println("    border: none;");
        out.println("    cursor: pointer;");
        out.println("}");
        out.println("");
        out.println(".dropdown {");
        out.println("    position: relative;");
        out.println("    display: inline-block;");
        out.println("}");
        out.println("");
        out.println(".dropdown-content {");
        out.println("    display: none;");
        out.println("    position: absolute;");
        out.println("    background-color: #f9f9f9;");
        out.println("    min-width: 160px;");
        out.println("    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);");
        out.println("}");
        out.println("");
        out.println(".dropdown-content a {");
        out.println("    color: black;");
        out.println("    padding: 12px 16px;");
        out.println("    text-decoration: none;");
        out.println("    display: block;");
        out.println("}");
        out.println("");
        out.println(".dropdown-content a:hover {background-color: #f1f1f1}");
        out.println("");
        out.println(".dropdown:hover .dropdown-content {");
        out.println("    display: block;");
        out.println("}");
        out.println("");
        out.println(".dropdown:hover .dropbtn {");
        out.println("    background-color: #3e8e41;");
        out.println("}");
        out.println("");
        out.println(".sidebar .a{");
        out.println("    text-decoration: none;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println(" <table border= 1px style= 'margin-top: 50px;'> ");
        while (rs.next()) {

        		String i = rs.getString("productID");
                String n = rs.getString("productName");
                String t = rs.getString("productType");
                String p = rs.getString("productPrice");
                // out.println("Your Orders.");
                out.println("<tr><th>ProductID</th><th>ProductName</th><th>ProductType</th><th>ProductPrice</th></tr>");

                out.println("<tr><td>"+i + "</td>");
                
                out.println("<td>"+n + "</td>");

                out.println("<td>"+t + "</td>");

                out.println("<td>"+p + "</td></tr>");

                //out.println("<td><form action='Cancel_Orders' method= 'post'><input type='hidden' value=" +on+"name= 'orderName'><input type='submit' value='Cancel' name= 'cancel'></form></td></tr>");
                
                }
                out.println(" </table> ");
        
        out.println("</body>");
        out.println("</html");
        out.close();      

		}
		catch(Exception e){
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
