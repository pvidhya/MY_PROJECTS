
import java.util.ArrayList;
import java.util.List;


public class OrderPayment {

    String userName;    
    int orderId;
    String productName; 
    int orderName;
    String orderPrice;
    String userAddress;
    String creditCardNo;
        
public OrderPayment( int orderName, String userName, String orderPrice, String userAddress, String creditCardNo, int orderId, String productName)
{
    
    this.userName= userName;
    this.orderId= orderId;
    this.productName= productName; 
    this.orderName= orderName;
    this.orderPrice= orderPrice;
    this.userAddress = userAddress;
    this.creditCardNo = creditCardNo;
}

}