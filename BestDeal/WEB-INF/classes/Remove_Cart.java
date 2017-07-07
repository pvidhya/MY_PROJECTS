import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
 
 
public class Remove_Cart extends HttpServlet {
 
    private static final long serialVersionUID = 1L;
 
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Statement stmt = null;
        // String n = request.getParameter("userName");
        // out.println(n);     
        String query = "DELETE * from customerorderdetails where userName= 'Vishnu'";
  
        // String p = request.getParameter("userPass");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/example","root","root");  
            stmt = conn.createStatement();
            // PreparedStatement pst = conn.prepareStatement("Select * from registeruser where USERNAME=?");
            // pst.setString(1, n);
            // pst.setString(2, p);
            ResultSet rs = stmt.executeQuery(query);

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
        out.println("        <h2>admit it..you need one</h2>");
        out.println("    </header>");
        out.println("    <nav>");
        out.println("    <ul>");
        out.println("        <li class=\"start selected\"><a href=\"index.html\">Home</a></li>");
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
        out.println("            <li class=\"sign_up\"><a href=\"signup.html\">logout</a></li>");
       // out.println("             <li> <h2> + user_type+ </h2>");
        // out.println("            <li class=\"sign_up\"><a href=\"signup.html\">Create account</a></li>");
        out.println("             <li class= \"nav_bar_cart\">");
        out.println("             <a href= \"cart.html\"><img src= \"images/cart.png\" style=\"width: 20px; height: 13px\">");
        out.println("                </a>");
        out.println("            </li>");
        out.println("        </ul>");
        out.println("    </nav>");
        out.println("");
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
        out.println("<body>");
        out.println("");
        out.println("    <div id=\"body\">");
        out.println("");
        out.println(" ");
        out.println("");
        out.println(" <section id=\"content\">");
        out.println("");
        out.println("     <article>");
        // out.println("Login successful!"); 






            while (rs.next()) {

                String n = rs.getString("userName");
                String p = rs.getString("orderPrice");
                // out.println("Your Orders.");
                
                out.println(n + "::");
                out.println(p + "::");
              }
        out.println("    <footer>");
        out.println("        <div class=\"footer-content\">");
        out.println("            <ul>");
        out.println("            <li><h4>Company info</h4></li>");
        out.println("                <li><a href=\"#\" style=\"text-decoration: none\">About us</a></li>");
        out.println("                <li><a href=\"#\" style=\"text-decoration: none\">Contat us</a></li>");
        out.println("                <li><a href=\"#\" style=\"text-decoration: none\">FAQs</a></li>");
        out.println("                <li><a href=\"#\" style=\"text-decoration: none\">Blogs</a></li>");
        out.println("            </ul>");
        out.println("            ");
        out.println("            <ul>");
        out.println("            <li><h4>Customer service</h4></li>");
        out.println("                <li><a href=\"#\" style=\"text-decoration: none\">Terms of Service</a></li>");
        out.println("                <li><a href=\"#\" style=\"text-decoration: none\">Online Return Policy </a></li>");
        out.println("                <li><a href=\"#\" style=\"text-decoration: none\">Privacy Policy</a></li>");
        out.println("                <li><a href=\"#\" style=\"text-decoration: none\">Product Warranty</a></li>");
        out.println("            </ul>");
        out.println("            ");
        out.println("           <!--  <ul class=\"endfooter\">");
        out.println("            <li><h4>Suspendisse</h4></li>");
        out.println("                <li><a href=\"#\">Morbi hendrerit libero </a></li>");
        out.println("                <li><a href=\"#\">Proin placerat accumsan</a></li>");
        out.println("                <li><a href=\"#\">Rutrum nulla a ultrices</a></li>");
        out.println("                <li><a href=\"#\">Curabitur sit amet tellus</a></li>");
        out.println("                <li><a href=\"#\">Donec in ligula nisl.</a></li>");
        out.println("            </ul>");
        out.println(" -->            ");
        out.println("            <div class=\"clear\"></div>");
        out.println("        </div>");
        out.println("        <div class=\"footer-bottom\">");
        out.println("            <p>&copy; BestDeal 2016");
        out.println("                     </div>");
        out.println("    </footer>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html");
        out.close();      
            }
            //  // out.println("    <footer>");
       //  // out.println("        <div class=\"footer-content\">");
       //  // out.println("            <ul>");
       //  // out.println("            <li><h4>Company info</h4></li>");
       //  // out.println("                <li><a href=\"#\" style=\"text-decoration: none\">About us</a></li>");
       //  // out.println("                <li><a href=\"#\" style=\"text-decoration: none\">Contat us</a></li>");
       //  // out.println("                <li><a href=\"#\" style=\"text-decoration: none\">FAQs</a></li>");
       //  // out.println("                <li><a href=\"#\" style=\"text-decoration: none\">Blogs</a></li>");
       //  // out.println("            </ul>");
       //  // out.println("            ");
       //  // out.println("            <ul>");
       //  // out.println("            <li><h4>Customer service</h4></li>");
       //  // out.println("                <li><a href=\"#\" style=\"text-decoration: none\">Terms of Service</a></li>");
       //  // out.println("                <li><a href=\"#\" style=\"text-decoration: none\">Online Return Policy </a></li>");
       //  // out.println("                <li><a href=\"#\" style=\"text-decoration: none\">Privacy Policy</a></li>");
       //  // out.println("                <li><a href=\"#\" style=\"text-decoration: none\">Product Warranty</a></li>");
       //  // out.println("            </ul>");
       //  // out.println("            ");
       //  // out.println("           <!--  <ul class=\"endfooter\">");
       //  // out.println("            <li><h4>Suspendisse</h4></li>");
       //  // out.println("                <li><a href=\"#\">Morbi hendrerit libero </a></li>");
       //  // out.println("                <li><a href=\"#\">Proin placerat accumsan</a></li>");
       //  // out.println("                <li><a href=\"#\">Rutrum nulla a ultrices</a></li>");
       //  // out.println("                <li><a href=\"#\">Curabitur sit amet tellus</a></li>");
       //  // out.println("                <li><a href=\"#\">Donec in ligula nisl.</a></li>");
       //  // out.println("            </ul>");
       //  // out.println(" -->            ");
       //  // out.println("            <div class=\"clear\"></div>");
       //  // out.println("        </div>");
       //  // out.println("        <div class=\"footer-bottom\">");
       //  // out.println("            <p>&copy; BestDeal 2016");
       //  // out.println("                     </div>");
       //  // out.println("    </footer>");
       //  // out.println("</div>");
       //  out.println("</body>");
       //  out.println("</html");
       //  out.close();      
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}