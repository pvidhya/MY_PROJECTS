package javaPackage;

import java.util.ArrayList;
import java.util.List;


public class Review {
    
    public String name;
    public String type;
    public String price;
    public String retailername;
    public String retailerzip;
    public String retailercity;
    public String retailerstate;
    public String productonsale;
    public String manufacturername;
    public String manufacturerrebate;
    public String userid;
    public String age;   
    public String gender;
    public String occupation;
    public String reviewRrating;
    public String reviewdate;
    public String reviewtext;
    
public Review(String name,String type, String price, String retailername,  String retailerzip, String retailercity, String retailerstate, String productonsale, String manufacturername, String manufacturerrebate, String userid, String age, String gender, String occupation, String reviewRrating, String reviewdate, String reviewtext)
{

    this.name= name;
    this.type= type;
    this.price= price;
    this.retailername = retailername;
    this.retailerzip = retailerzip;
    this.retailercity = retailercity;
    this.retailerstate = retailerstate;
    this.productonsale = productonsale;
    this.manufacturername = manufacturername;
    this.manufacturerrebate = manufacturerrebate;
    this.userid = userid;
    this.age = age;
    this.gender = gender;
    this.occupation = occupation;
    this.reviewRrating = reviewRrating;
    this.reviewdate = reviewdate;
    this.reviewtext = reviewtext;
}

}