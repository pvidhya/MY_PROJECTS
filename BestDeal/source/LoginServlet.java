import java.io.*;
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginServlet extends HttpServlet {
   
  HashMap<String, String> hm = new HashMap<String, String>();
  String TOMCAT_HOME = "C:\\apache-tomcat-7.0.34";
  String error_msg = "";

  protected void doPost(
    HttpServletRequest request, 
     HttpServletResponse response
     ) throws ServletException, IOException {
    String username = request.getParameter("username");
    //String email = request.getParameter("email");
    String password = request.getParameter("password");
    String user_type = request.getParameter("user_type");
  try
  {
  FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\csj\\source\\UserDetails.ser"));
  ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

  hm= (HashMap)objectInputStream.readObject();
  }
  catch(Exception io) {
      //throw new ServletException(e);
  }

        if(username != null && username.length() != 0) {
            username = username.trim();
        }
        if(password != null && password.length() != 0) {
            password = password.trim();
        }
        if(username != null && password != null) {
                String realpassword = (String)hm.get(username);
                if(realpassword != null && realpassword.equals(password)) {
                    if(user_type != "Salesmen" && user_type != "Customer"){
                    showPage(response, "Customer Login Success!");
                    }
                    else {
                    response.sendRedirect(request.getContextPath()+"/sales_index.html"); 
                    //showPage(response, "Salesmen Login Success!");
                    }
      
                } else {
                    showPage(response, "Login Failure! Username or password is incorrect");
                }
        }
        else {
            showPage(response, "Login Failure!  You must supply a username and password");
        }
    } 

    protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
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
        out.println("        <h2>admit it..you need one</h2>");
        out.println("    </header>");
        out.println("    <nav>");
        out.println("    <ul>");
        out.println("        <li class=\"start selected\"><a href=\"index.html\">Home</a></li>");
        out.println("            <li>");
        out.println("            <div class=\"dropdown\">");
        out.println("            <a href=\"#\">Products</a>");
        out.println("            <div class=\"dropdown-content\">");
        out.println("                <a href=\"smartphone.html\">Smart Phones</a>");
        out.println("                <a href=\"tablet.html\">Tablets</a>");
        out.println("                <a href=\"laptop.html\">Laptops</a>");
        out.println("                <a href=\"tv.html\">TV</a>");
        out.println("            </div>");
        out.println("            </div></li>");
        out.println("            <!-- <li><a href=\"#\">Services</a></li> -->");
        out.println("            <li class=\"\"><a href=\"deals.html\">Deals</a></li>");
        out.println("            <li class=\"sign_up\"><a href=\"signup.html\">logout</a></li>");
       // out.println("             <li> <h2> + user_type+ </h2>");
        out.println("            <li class=\"sign_up\"><a href=\"signup.html\">Create account</a></li>");
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
        out.println("");
        out.println("");
        out.println("</body>");
        out.println("");
        out.println("<img class=\"header-image\" src=\"images/smartphones.png\" style=\"width: 24%\" />");
        out.println("<img class=\"header-image\" src=\"images/laptop.png\" style=\"width: 24%\"/>");
        out.println("<img class=\"header-image\" src=\"images/tablet.png\" style=\"width: 24%\"/>");
        out.println("<img class=\"header-image\" src=\"images/tv.jpg\" style=\"width: 24%\"/>");
        out.println("");
        out.println("    <div id=\"body\">");
        out.println("");
        out.println(" ");
        out.println("");
        out.println(" <section id=\"content\">");
        out.println("");
        out.println("     <article>");
        out.println(" ");
        out.println(" ");
        out.println(" <h2 style=\"margin-top: 40px\">SHOP THE NEW IPHONE 7</h2>");
        out.println(" ");
        out.println("                <img class=\"image\" src=\"images/smartphone/iphone7.jpg\" style=\"width: 34%; margin: 20px;\" />");
        out.println("        ");
        out.println("");
        out.println(" <a href=\"#\" class=\"button\">shop now</a>");
        out.println(" ");
        out.println(" </article>");
        out.println(" ");
        out.println(" <article class=\"expanded\">");
        out.println("");
        out.println("            <h2>ABOUT US</h2>");
        out.println("            <p>Here at Best Deal, we are a group of creative, energetic people inspired by the dream of bringing you the most advanced technical appliance at your door-steps. Our mission is to bring the peple around the world closer by eleminating the distances through our devices. To know more about us, check out our <a href=\"\" title=\"template license\">Terms of Service.</a></p>");
        out.println("<!-- ");
        out.println(" <a href=\"#\" class=\"button\">Read more</a>");
        out.println(" <a href=\"#\" class=\"button\">Comments</a>");
        out.println(" </article> -->");
        out.println("        </section>");
        out.println("        ");
        out.println("        <aside class=\"sidebar\">");
        out.println(" ");
        out.println("            <ul> ");
        out.println("               <li>");
        out.println("                    <h4>Categories</h4>");
        out.println("                    <ul>");
        out.println("                        <li><a href=\"#\" style=\"text-decoration: none\">Accessories</a></li>");
        out.println("                        <li><a href=\"#\" style=\"text-decoration: none\">Shop by Brand</a></li>");
        out.println("                        <li><a href=\"#\" style=\"text-decoration: none\">Shop by Product</a></li>");
        out.println("                    </ul>");
        out.println("                </li>");
        out.println("                ");
        out.println("                <li>");
        out.println("                    <h4>About us</h4>");
        out.println("                    <ul>");
        out.println("                        <li class=\"text\">");
        out.println("                        <p style=\"margin: 0;\">Our mission is to bring the peple around the world closer by eleminating the distances through our devices.</p>.<a href=\"#\" class=\"readmore\">Read More &raquo;</a></p>");
        out.println("                        </li>");
        out.println("                    </ul>");
        out.println("                </li>");
        out.println("                ");
        out.println("               <!--  <li>");
        out.println("                <h4>Search site</h4>");
        out.println("                    <ul>");
        out.println("                    <li class=\"text\">");
        out.println("                            <form method=\"get\" class=\"searchform\" action=\"#\" >");
        out.println("                                <p>");
        out.println("                                    <input type=\"text\" size=\"32\" value=\"\" name=\"s\" class=\"s\" />");
        out.println("                                    ");
        out.println("                                </p>");
        out.println("                            </form>  ");
        out.println(" </li>");
        out.println(" </ul>");
        out.println("                </li> -->");
        out.println("                <!-- ");
        out.println("                <li>");
        out.println("                    <h4>Helpful Links</h4>");
        out.println("                    <ul>");
        out.println("                        <li><a href=\"http://www.themeforest.net/?ref=spykawg\" title=\"premium templates\">Premium HTML web templates from $10</a></li>");
        out.println("                        <li><a href=\"http://www.dreamhost.com/r.cgi?259541\" title=\"web hosting\">Cheap web hosting from Dreamhost</a></li>");
        out.println("                        <li><a href=\"http://tuxthemes.com\" title=\"Tux Themes\">Premium WordPress themes</a></li>");
        out.println("                    </ul>");
        out.println("                </li>");
        out.println(" -->                ");
        out.println("            </ul>");
        out.println(" ");
        out.println("        </aside>");
        out.println("    <div class=\"clear\"></div>");
        out.println("    </div>");
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
    
}