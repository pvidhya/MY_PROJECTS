import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.sql.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 



public class Trending1 extends HttpServlet {
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
     {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HashMap<String, String> toprate= new HashMap<String, String>();
            
         MongoDBDataStoreUtilities mongoClient = new MongoDBDataStoreUtilities();
         int rate= 0;    
            
        ArrayList<Review> rating = mongoClient.sorting();

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
        out.println("            <li class=\"sign_up\"><a href=\"index\">logout</a></li>");
        // out.println("            <li class=\"sign_up\"><a href=\"Update_Orders\">Update Orders</a></li>");
        out.println("            </li>");
        out.println("        </ul>");
        out.println("    </nav>");
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
        out.println("");
        out.println("    <div id=\"body\">");
        out.println("");
        out.println(" ");
        out.println("");
        out.println(" <section id=\"content\">");
        out.println("");
        out.println(" <div class= 'Trending' style= 'padding-left: 300px'>");
        out.println(" <h2>Following are the top 5 most liked products:</h2> </br><br/>");
         
        for (Review product : rating)
        {
        if(rate<5){
        if(!toprate.containsKey(product.name)){
         
        out.println("<table border= '1px'>");                  
            out.println(" <tr><td>ProductModelName: </td><td><input type='text' name='name' value="+ product.name+" readonly><br/></td></tr>");
            rate++;
            toprate.put(product.name, "Sorted");
        }
    }
}
        out.println("</table>");
      try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/example","root","root");  
            PreparedStatement pst = conn.prepareStatement(" select main.* from (select count(*) ct,userAddress ad from customerorderdetails group by userAddress) main order by main.ct desc");
            
            ResultSet rs = pst.executeQuery();
            out.println(" </br></br><h2>Following are the top five zip-codes where maximum number of products were sold :</h2></br><br/>");
           
        int count=0;
        while (rs.next()) {
        count= count+1;
        if(count<6){
           
            
        out.println("<table border= '1px'> ");
        out.println("            <tr>                 ");
        out.println("            <th>Zipcode: </th>");
        out.println("            <th>Count: </th>");
        out.println("            </tr>");
        out.println("            <tr>");
        out.println("            <td><input type=\"text\" name='name' value="+rs.getString("ad")+ "><br/></td>");
        out.println("            <td><input type=\"text\" name='count' value="+ rs.getString("ct")+ "><br/></td>");
        out.println("            </tr");
        out.println("</table>");

            }//out.println("</table>");
}out.println("</table>");
}
catch(Exception e)
{}
      try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/example","root","root");  
            PreparedStatement pst = conn.prepareStatement("select main.productname,main.ct from (select productname,count(productName) ct from customerorderdetails group by productname) main where main.productname is not null order by main.ct desc");
            
            ResultSet rs = pst.executeQuery();
            out.println(" </br></br><h2>Following are the top five Products that are sold  :</h2> </br><br/>");

            while (rs.next()) {
            out.println("<table border= '1px'> ");
            out.println("            <tr>                 ");
            out.println("            <th>Product: </th>");
            out.println("            <th>Count: </th>");
            out.println("            </tr>");
            out.println("            <tr>");
            out.println("            <td><input type=\"text\" name='name' value="+ rs.getString("productname")+ "><br/></td>");
            out.println("            <td><input type=\"text\" name='count' value="+ rs.getString("ct") +"><br/></td>");
            out.println("            </tr");
            //out.println(" </table>");
            }out.println("</table>");

            out.println(" </section>");
            out.println(" </div>");
            out.println("</body>");
        out.println("</html");
        out.close();  
}
catch(Exception e)
{}

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
