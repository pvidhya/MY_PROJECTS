import java.util.*;

import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.BasicDBObject;
import com.mongodb.ServerAddress;

public class MongoDBDataStoreUtilities
{
	static DBCollection myReviews;
	public static void getConnection()
	{
		 try{
         MongoClient mongoClient = new MongoClient("localhost" , 27017);
         DB db = mongoClient.getDB("CustomerReviews");
		 myReviews= db.getCollection("myReviews");
         System.out.println("Connect to database successfully");
      }catch(Exception e){
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      }
	}

	public static HashMap<String, ArrayList<Review>> selectReview()
	{
		getConnection();
		HashMap<String, ArrayList<Review>> reviewHashmap=new HashMap<String, ArrayList<Review>>();
		DBCursor cursor = myReviews.find();
		while (cursor.hasNext())
		{
		System.out.println("has reviews");
		BasicDBObject obj = (BasicDBObject) cursor.next();
		if(! reviewHashmap.containsKey(obj.getString("name")))
		{
		System.out.println("Insise if mongodatastore");
		ArrayList<Review> arr = new ArrayList<Review>();
		reviewHashmap.put(obj.getString("name"), arr);
		}
		ArrayList<Review> listReview = reviewHashmap.get(obj.getString("name"));
		Review review =new Review(obj.getString("name"),obj.getString("type"),obj.getString("price"), obj.getString("retailername"), obj.getString("retailerzip"),  obj.getString("retailercity"), obj.getString("retailerstate"),obj.getString("productonsale"), obj.getString("manufacturername"),obj.getString("manufacturerrebate"), obj.getString("userid"),obj.getString("age"),obj.getString("gender"),obj.getString("occupation"), obj.getString("reviewRrating"),obj.getString("reviewdate"),obj.getString("reviewtext"));
		listReview.add(review);
		System.out.println("Name of product"+obj.getString("name"));
		System.out.println("Price of product"+obj.getString("price"));
		System.out.println("Date"+obj.getString("reviewdate"));

		// System.out.println("Price of product"+review.getprice());
		}
		return reviewHashmap;
	}
	public static void insertReview(String name,String type, String price, String retailername,  String retailerzip, String retailercity, String retailerstate, String productonsale, String manufacturername, String manufacturerrebate, String userid, String age, String gender, String occupation, String reviewrating, String reviewdate, String reviewtext)
	{
		getConnection();
		BasicDBObject doc = new BasicDBObject("title", "myReviews").
				append("name", name).
				append("price", price).
				append("type", type).
				append("retailername", retailername).
				append("retailerzip", retailerzip).
				append("retailercity", retailercity).
				append("retailerstate", retailerstate).
				append("productonsale", productonsale).
				append("manufacturername", manufacturername).
				append("manufacturerrebate", manufacturerrebate).
				append("userid",userid).
				append("age",age).
				append("gender", gender).
				append("occupation", occupation).
				append("reviewRrating", reviewrating).
				append("reviewdate",reviewdate).
				append("reviewtext", reviewtext);
				myReviews.insert(doc);
		
	}

		public static ArrayList<Review> sorting()  {
  		getConnection();
  		BasicDBObject sort = new BasicDBObject();
		sort.put("reviewRrating", -1);
 		DBCursor cursor = myReviews.find().sort(sort);
		ArrayList<Review> trend = new ArrayList<Review>();

        while (cursor.hasNext())
 		
 		{
 		BasicDBObject obj = (BasicDBObject) cursor.next();

 		Review review =new Review(obj.getString("name"),obj.getString("type"),obj.getString("price"), obj.getString("retailername"), obj.getString("retailerzip"),  obj.getString("retailercity"), obj.getString("retailerstate"),obj.getString("productonsale"), obj.getString("manufacturername"),obj.getString("manufacturerrebate"), obj.getString("userid"),obj.getString("age"),obj.getString("gender"),obj.getString("occupation"), obj.getString("reviewrating"),obj.getString("reviewdate"),obj.getString("reviewtext"));
		trend.add(review);
		}
		return trend;
		}

	}