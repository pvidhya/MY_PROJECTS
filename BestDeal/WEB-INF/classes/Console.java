
import java.util.ArrayList;
import java.util.List;


public class Console {
    public String retailer;
    public String retailername;
    public String retailercity;
    public String retailerzip;
    public String retailerstate;
    public String productonsale;
    public String manufacturerrebate;
    public String manufacturername;
    public String type;
    public String name;
    public String id;
    public String image;
    public String condition;
    public int price;
    public List<String> accessories;
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