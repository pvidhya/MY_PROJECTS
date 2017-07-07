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

public static HashMap<String,Console> getData()
{ 
	HashMap<String,Console> hm=new HashMap<String,Console>();
try
{ 
//getConnection();
 Class.forName("com.mysql.jdbc.Driver").newInstance();
 Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/example","root","root");
 Statement stmt=con.createStatement();
String selectCustomerQuery="select * from product";
ResultSet rs = stmt.executeQuery(selectCustomerQuery);
while(rs.next())
{
Console p = new Console ();
hm.put(rs.getString("consoleId"), p);
}
}
catch(Exception e){
		}

return hm;
}
public StringBuffer readdata(String searchId)
{
HashMap<String,Console> data;
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
sb.append("<productName>" + p.getName() + "</ productName >");
sb.append("</ product >");
}
}
return sb;
}

}