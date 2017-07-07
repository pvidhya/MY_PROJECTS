import java.util.*;
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  


public class MySqlDataStoreUtilities
{
public static void insertOrder(String userName, int orderId,String productName, String orderName, String orderPrice, String userAddress, String creditCardNo)
	{ 
	try
		{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/example","root","root");    
		String insertIntoCustomerOrderQuery = "INSERT INTO customerOrderDetails(orderName,orderId,productName,userName,orderPrice,userAddress,creditCardNo)" + "VALUES (?,?,?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(insertIntoCustomerOrderQuery);
			pst.setString(1,userName);
			pst.setInt(2,orderId);
			pst.setString(3,productName);
			pst.setString(4,orderName);
			pst.setString(5,orderPrice);
			pst.setString(6,userAddress);
			pst.setString(7,creditCardNo);
			pst.execute();
		}
	catch(Exception e){
		}
	}

public static void deleteProducts()
	{ 
	try
		{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/example","root","root");    
		String query = "DELETE from Products";
		PreparedStatement pst = con.prepareStatement(query);
			// pst.setString(1,productID);
			// pst.setString(2,productType);
			// pst.setInt(3,productPrice);
			// pst.setString(4,productName);
			pst.execute();
		}
	catch(Exception e){
		}
	}



public static void insertProducts(String productID, String productType, int productPrice, String productName)
	{ 
	try
		{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/example","root","root");    
		String insertIntoCustomerOrderQuery = "INSERT INTO Products(productID, productType, productPrice, productName)" + "VALUES (?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(insertIntoCustomerOrderQuery);
			pst.setString(1,productID);
			pst.setString(2,productType);
			pst.setInt(3,productPrice);
			pst.setString(4,productName);
			pst.execute();
		}
	catch(Exception e){
		}
	}

	public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder()
	{
	HashMap<Integer,ArrayList<OrderPayment>> orderPayments=new HashMap<Integer,ArrayList<OrderPayment>>();
	try
	{

	Class.forName("com.mysql.jdbc.Driver").newInstance();
	Connection con=DriverManager.getConnection(  
	"jdbc:mysql://localhost:3306/example","root","root");
	String selectOrderQuery ="select * from customerOrderDetails";
	PreparedStatement pst = con.prepareStatement(selectOrderQuery);
	ResultSet rs = pst.executeQuery();
	ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
	while(rs.next())
	{
		if(!orderPayments.containsKey(rs.getDouble("orderId")))
			{
			ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
			orderPayments.put(rs.getInt("orderId"), arr);}
			ArrayList<OrderPayment> listOrderPayment =orderPayments.get(rs.getInt("orderId"));
			OrderPayment order= new OrderPayment(rs.getString("userName"), rs.getInt("orderId"), rs.getString("productName"),rs.getString("orderName"), rs.getString("orderPrice"), rs.getString("userAddress"), rs.getString("creditCardNo"));
			listOrderPayment.add(order);
			}
	}
	catch(Exception e){}
	return orderPayments;

}
 // protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 //        doGet(request, response);
 //    }


}