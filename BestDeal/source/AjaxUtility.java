import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.sql.*; 
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AjaxUtility
{

public static HashMap<String,Product> getData()
{ 
	HashMap<String,Product> hm=new HashMap<String,Product>();
try
{ 
//getConnection();
	System.out.println("In ajax");	
Class.forName("com.mysql.jdbc.Driver").newInstance();
Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/example","root","root");
Statement stmt=con.createStatement();
String selectCustomerQuery="select * from products";
ResultSet rs = stmt.executeQuery(selectCustomerQuery);
while(rs.next())
{
Product p = new Product (rs.getString("productName"),rs.getString("productID"),rs.getDouble("productPrice"),rs.getString("productType"));
hm.put(rs.getString("productID"), p);
}
}
catch(Exception e){
		e.printStackTrace();}

return hm;
}

public StringBuffer readdata(String searchId)
{
HashMap<String,Product> data;
StringBuffer sb = new StringBuffer();

data = getData();
Iterator it = data.entrySet().iterator();
while (it.hasNext())
{
Map.Entry pi = (Map.Entry)it.next();
Product p=(Product)pi.getValue();
if (p.getName().toLowerCase().startsWith(searchId))
{
sb.append("<product>");
sb.append("<id>" + p.getId() + "</id>");
sb.append("<productName>" + p.getName() + "</productName>");
sb.append("</product>");
}

System.out.println(p.getId());
}
return sb;
}

}
