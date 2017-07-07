import java.util.ArrayList;
import java.util.List;


public class OrderPayment {

    String userName;    
    int orderId;
    String orderName;
    String productName;
    String orderPrice;
    String userAddress;
    String creditCardNo;
        
public OrderPayment( String userName, int orderId,String productName, String orderName, String orderPrice, String userAddress, String creditCardNo)
{
    
    this.userName= userName;
    this.orderId= orderId;
    this.orderName= orderName;
    this.orderPrice= orderPrice;
    this.userAddress = userAddress;
    this.creditCardNo = creditCardNo;
}

}