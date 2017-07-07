import java.util.ArrayList;
import java.util.List;


public class Product {
    String retailer;
    String name;
    String id;
    String image;
    String condition;
    Double price;
    String type;
    List<String> accessories;
    public Product(){
        accessories=new ArrayList<String>();
    }
    public Product(String name,String id, Double price,String type)
    {
        this.name = name;
        this.id = id;
        this.price = price;
        this.type = type;
    }

 void setId(String id) {
    this.id = id;
 }

void setRetailer(String retailer) {
    this.retailer = retailer;
}

public String getType()
{
    return type;
}
public void setType(String type)
{
    this.type = type;
}
public String getRetailer() {
    return retailer;
}

public String getName() {
    return name;
}

public String getId() {
    return id;
}

public String getImage() {
    return image;
}

public String getCondition() {
    return condition;
}

public Double getPrice() {
    return price;
}

public void setAccessories(List<String> accessories) {
    this.accessories = accessories;
}

void setImage(String image) {
    this.image = image;
}

void setCondition(String condition) {
    this.condition = condition;
}

void setPrice(Double price) {
    this.price = price;
}

List getAccessories() {
    return accessories;
}


void setName(String name) {
    this.name = name;
}


}