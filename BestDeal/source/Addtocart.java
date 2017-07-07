import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Addtocart extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        Cart shoppingCart;
        shoppingCart = (Cart) session.getAttribute("cart");
        if(shoppingCart == null){
          shoppingCart = new Cart();
          session.setAttribute("cart", shoppingCart);
        }
        double total = 0.0;      

        String name = request.getParameter("consolename");
        Double price = Double.parseDouble(request.getParameter("consoleprice"));
        shoppingCart.addToCart(name, price);
        session.setAttribute("cart", shoppingCart);
        try (PrintWriter out = response.getWriter()) {
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
        out.println("        <li><a href=\"index.html\">Home</a></li>");
        out.println("            <li>");
        out.println("            <div class=\"dropdown\">");
        out.println("            <a href=\"#\">Products</a>");
        out.println("            <div class=\"dropdown-content\">");
        out.println("                <a href=\"ServletProduct\"  class=\"start selected\">Smart Phones</a>");
        out.println("                <a href=\"ServletTablet\">Tablets</a>");
        out.println("                <a href=\"ServletLaptop\">Laptops</a>");
        out.println("                <a href=\"ServletTV\">TV</a>");
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
        out.println("    <div id=\"body\">");
        out.println("");
        out.println(" ");
        out.println("");
        out.println(" <section id=\"content\">");
        out.println("");
        out.println("     <article>");
        out.println("</br>" );
        out.println("</br>" );
            // out.println("<h1>Item successfully added to cart</h1>");
            // out.println("<form action='shop.html'>Add more pizza item<input type='submit' value='go'></form>");
            // out.println("<hr>");
            out.println("<h2>Order Total</h2>");
            HashMap<String, Double> consoles =(HashMap<String, Double>) shoppingCart.getItems();

            out.println("<table border='1px'>");
             
            for(String key: consoles.keySet())
            {
                total  = total +consoles.get(key);
                out.println("<tr><td>"+key+" </td><td>"+"$"+consoles.get(key)+"</td></tr>");
            }     
            // out.println("<tr><td>Total Price: </td><td>$"+total+"</td></tr>");        
            out.println("</table>");
            out.println("<form action=\"Placeorder\" method=\"post\">  ");
            out.println("<input type='hidden' name= 'productName value="+name+"/><br/> <br/>  ");
            
            out.print("Order Total: <input type=\"text\" name='orderPrice' value= "+total+" readonly><br/><br/> ");
            out.println("Name:    <input type=\"text\" name=\"userName\"/><br/> <br/>  ");
            // out.println("Userame:    <input type=\"text\" name=\"userName\"/><br/>  ");
            out.println("   ");
            out.println("Address:    <input type=\"text\" name=\"userAddress\"/><br/> <br/>  ");
            out.println("CreditCard number:  <input type=\"text\" name=\"creditCardNo\"/><br/><br/>  ");
            out.print("<input type = 'Submit' class='button' name ='submit' value = 'Placeorder'>");
            out.print("</form>");
            out.println("</body>");
            out.println("</html>");
             
        }
    }   protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
                processRequest(request,response);
                
                
            } 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
                processRequest(request,response);
            }

    }