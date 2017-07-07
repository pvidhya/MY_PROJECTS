


import java.util.ArrayList;
import java.util.List;


public class Console {
    String retailer;
    String retailername;
    String retailercity;
    String retailerzip;
    String retailerstate;
    String productonsale;
    String manufacturerrebate;
    String manufacturername;
    String type;
    String name;
    String id;
    String image;
    String condition;
    int price;
    List<String> accessories;
    public Console(){
        accessories=new ArrayList<String>();
    }

void setId(String id) {
    this.id = id;
}
void setRetailer(String retailer) {
    this.retailer = retailer;
}
void setRetailerName(String retailername) {
    this.retailername = retailername;
}
void setRetailerCity(String retailercity) {
    this.retailercity = retailercity;
}
void setRetailerState(String retailerstate) {
    this.retailerstate = retailerstate;
}
void setRetailerZip(String retailerzip) {
    this.retailerzip = retailerzip;
}
void setManufacturerRebate(String manufacturerrebate) {
    this.manufacturerrebate = manufacturerrebate;
}
void setManufacturerName(String manufacturername) {
    this.manufacturername = manufacturername;
}
void setProductOnSale(String productonsale) {
    this.productonsale = productonsale;
}
void setImage(String image) {
    this.image = image;
}
void setCondition(String condition) {
    this.condition = condition;
}
void setPrice(int price) {
    this.price = price;
}
void setType(String type) {
    this.type = type;
}
List getAccessories() {
    return accessories;
}


void setName(String name) {
    this.name = name;
}





}