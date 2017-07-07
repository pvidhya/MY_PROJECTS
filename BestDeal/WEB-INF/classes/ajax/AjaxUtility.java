import java.util.*;
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  


public static HashMap<String,Product> getData()
{ 
	HashMap<String,Product> hm=new HashMap<String,Product>();
try
{ 
getConnection();
Statement stmt=conn.createStatement();
String selectCustomerQuery="select * from product";
ResultSet rs = stmt.executeQuery(selectCustomerQuery);
while(rs.next())
{
Product p = new Product(rs.getString("productId"), rs.getString("productName"));
hm.put(rs.getString("productId"), p);
}
}
return hm;
}

public StringBuffer readdata(String searchId)
{
HashMap<String,Product> data;
data=getData();
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