import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
 
 
public class Update_Orders extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Statement stmt = null;
        String userName = request.getParameter("userName");
        // out.println(n);     
        String query = "SELECT * from customerorderdetails where userName= userName ";  
        // String p = request.getParameter("userPass");
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
        out.println("        <h2>admit it..you need one</h2>");
         out.println("<body onload=\"init()\" style= \" margin-top: 20px; \">");
out.println("<!-- <script type=\"text/javascript\" src=\"javascript.js\"></script> -->");
out.println("<div name=\"autofillform\" style=\"margin-bottom: 10px; float: right; margin-right: 20px;\">");
out.println("<input type=\"text\" name=\"searchId\" value=\"\" class=\"input\" id= \"searchId\" onkeyup=\"doCompletion()\" placeholder=\"search here..\" style=\"padding: 5px; font-size: 16px;\" />");
out.println("<div id=\"auto-row\">");
out.println("<table id=\"complete-table\" class=\"gridtable\" style=\"position: absolute; width:220px; background-color: black; text-color: white; \"></table>");
out.println("</div>");
out.println("</div>");
out.println("</body>");
out.println("<script>");
out.println("function init() {");
out.println("completeField = document.getElementById(\"searchId\");");
out.println("completeTable = document.getElementById(\"complete-table\");");
out.println("autoRow = document.getElementById(\"auto-row\");");
out.println("console.log(\"hello1\");");
out.println("}");
out.println("function appendProduct(productName,productId)");
out.println("{");
out.println("  row=document.createElement(\"tr\");");
out.println("  cell = document.createElement(\"td\");");
out.println("  textnode=document.createTextNode(productName);");
out.println("  cell.appendChild(textnode);");
out.println("  row.appendChild(cell);");
out.println("  completeTable.appendChild(row);");
out.println("");
out.println("linkElement = document.createElement(\"a\");");
out.println("linkElement.className = \"popupItem\";");
out.println("linkElement.setAttribute(\"href\", \"autocomplete?action=lookup&searchId=\" + productId);");
out.println("linkElement.appendChild(document.createTextNode(productName));");
out.println("cell.appendChild(linkElement);");
out.println("}");
out.println("");
out.println("function doCompletion() {");
out.println("var url = \"autocomplete?action=complete&searchId=\" + searchId.value;");
out.println("req = initRequest();");
out.println("req.open(\"GET\", url, true);");
out.println("req.setRequestHeader('Content-Type', 'text/xml');");
out.println("req.onreadystatechange = callback;");
out.println("req.send();");
out.println("}");
out.println("");
out.println("function initRequest() {");
out.println("if (window.XMLHttpRequest) {");
out.println("if (navigator.userAgent.indexOf('MSIE') != -1) {");
out.println("isIE = true;");
out.println("}");
out.println("return new XMLHttpRequest();");
out.println("} else if (window.ActiveXObject) {");
out.println("isIE = true;");
out.println("return new ActiveXObject(\"Microsoft.XMLHTTP\");");
out.println("}");
out.println("}");
out.println("");
out.println("// function appendProduct(productName,productId) {");
out.println("// var row;");
out.println("// var cell;");
out.println("// var linkElement;");
out.println("// if (isIE) {");
out.println("// completeTable.style.display = 'block';");
out.println("// row = completeTable.insertRow(completeTable.rows.length);");
out.println("// cell = row.insertCell(0);");
out.println("// } else {");
out.println("// completeTable.style.display = 'table';");
out.println("// row = document.createElement(\"tr\");");
out.println("// cell = document.createElement(\"td\");");
out.println("// row.appendChild(cell);");
out.println("// completeTable.appendChild(row);");
out.println("// }");
out.println("// cell.className = \"popupCell\";");
out.println("// linkElement = document.createElement(\"a\");");
out.println("// linkElement.className = \"popupItem\";");
out.println("// linkElement.setAttribute(\"href\", \"autocomplete?action=lookup&searchId=\" + productId);");
out.println("// linkElement.appendChild(document.createTextNode(productName));");
out.println("// cell.appendChild(linkElement);");
out.println("// console.log(\"hello4\");");
out.println("// }");
out.println("");
out.println("function parseMessages(responseXML) {");
out.println("if (responseXML == null) {");
out.println("return false;");
out.println("} else {");
out.println("var products = responseXML.getElementsByTagName(\"products\")[0];");
out.println("if (products.childNodes.length > 0) {");
out.println("completeTable.setAttribute(\"bordercolor\", \"black\");");
out.println("completeTable.setAttribute(\"border\", \"1\");");
out.println("for (loop = 0; loop < products.childNodes.length; loop++) {");
out.println("var product = products.childNodes[loop];");
out.println("var productName = product.getElementsByTagName(\"productName\")[0];");
out.println("var productId = product.getElementsByTagName(\"id\")[0];");
out.println("");
out.println("// console.log(productName.childNodes[0].nodeValue);");
out.println("// test(productName.childNodes[0].nodeValue,productId.childNodes[0].nodeValue);");
out.println("appendProduct(productName.childNodes[0].nodeValue, productId.childNodes[0].nodeValue);");
out.println("}");
out.println("}");
out.println("}");
out.println("}");
out.println("");
out.println("function callback() {");
out.println("clearTable();");
out.println("if (req.readyState == 4) {");
out.println("    console.log(\"in ready state\");");
out.println("    // if (req.status == 200) {");
out.println("        console.log(\"in status state\");");
out.println("        // alert(\"in status state\");");
out.println("parseMessages(req.responseXML);");
out.println("}");
out.println("}");
out.println("");
out.println("function clearTable() {");
out.println("if (completeTable.getElementsByTagName(\"tr\").length > 0) {");
out.println("completeTable.style.display = 'none';");
out.println("for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {");
out.println("completeTable.removeChild(completeTable.childNodes[loop]);");
out.println("}");
out.println("}");
out.println("}");
out.println("");
out.println("</script");
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
        // out.println("            <li class=\"sign_up\"><a href=\"Update_Orders\">Update Orders</a></li>");
        
        // out.println("            <li class=\"sign_up\"><a href=\"Add_Products.html\">Add Products</a></li>");
        // out.println("            <li class=\"sign_up\"><a href=\"Delete_Products.html\">Delete Products</a></li>"); 
        // out.println("             <li class= \"nav_bar_cart\">");
        // out.println("             <a href= \"Cart_Orders\"><img src= \"images/cart.png\" style=\"width: 20px; height: 13px\">");
        // out.println("                </a>");
        // out.println("            </li>");
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
         out.println(" <table border= 1px> ");
          while (rs.next()) {

                String n = rs.getString("userName");
                String p = rs.getString("orderPrice");
                String on = rs.getString("orderName");
                // out.println("Your Orders.");
                
                out.println("<tr><td>"+n + "</td>");
                      
                out.println("<td>"+on + "</td>");

                out.println("<td>"+p + "</td>");

                //out.println("<td><form action='Cancel_Orders' method= 'post'><input type='hidden' value=" +on+"name= 'orderName'><input type='submit' value='Cancel' name= 'cancel'></form></td></tr>");
                out.println("<td><form method= 'post' action= 'Cancel_Orders'>");
                out.println("<input type= 'hidden' name= 'orderName' value= "+on+">" );
                out.println("<input type= 'submit' name= 'cancel' value= 'Cancel'></td></tr>");
                out.println("</form>");
                }
                out.println("</table>");

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
        catch (Exception e) {
        }
    }
}