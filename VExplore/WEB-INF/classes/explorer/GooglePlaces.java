package explorer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;


public class GooglePlaces {
	
	    private final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
	    private final String TYPE_SEARCH = "/textsearch";
	    public String INTEREST = "";
	    private final String OUT_JSON = "/json";
	    private final String API_KEY = "AIzaSyA2mkF1QlZicQ3-hbVgZXWrK1aY84gAwNY";//"AIzaSyD0tDyqcCigxxT12E00VCatJ3Aqglm4PpY";

	    public ArrayList<Place> getResultPlaces(int intUserId) {
	        ArrayList<Place> resultList = null;
	        HashMap<Integer,ArrayList<String>> hm = new HashMap<Integer,ArrayList<String>>();
	        HttpURLConnection conn = null; 
	        StringBuilder jsonResults = new StringBuilder();
	        //int intUserId = Integer.parseInt(userid);
			ArrayList<String> hobbies = null; String[] arr = null; 
	        hm = MySQLDataStoreUtilities.selectInterest(intUserId);
			try{
	      if(hm.size()!=0)
	        {
	        	hobbies = hm.get(intUserId);	        
		        for(String s : hobbies)
		        {
		        	arr = s.split(" ");
		        }
		        for(int i =0; i< arr.length; i++)
		        {
		        	INTEREST = "+"+arr[i];    
		        }
	        }
	        else
	        {
	        	INTEREST = "";
	        }
			}
			catch(Exception e)
			{
				
			}
	        System.out.println(INTEREST);
	        try {
	            StringBuilder sb = new StringBuilder(PLACES_API_BASE);
	            sb.append(TYPE_SEARCH);
	            sb.append(OUT_JSON);
	            sb.append("?query=top");
	            sb.append(INTEREST);
	            sb.append("&key=" + API_KEY);
	            //sb.append("&input=" + URLEncoder.encode(input, "utf8"));
	            URL url = new URL(sb.toString());
	            conn = (HttpURLConnection) url.openConnection();
	            InputStreamReader in = new InputStreamReader(conn.getInputStream());

	            int read;
	            char[] buff = new char[1024];
	            while ((read = in.read(buff)) != -1) {
	                jsonResults.append(buff, 0, read);
	            }
	        } catch (MalformedURLException e) {
	            System.out.println("Error processing Places API URL");
	            return resultList;
	        } catch (IOException e) {
	        	System.out.println("Error connecting to Places API");
	            return resultList;
	        } finally {
	            if (conn != null) {
	                conn.disconnect();
	            }
	        }

	        try {
	           
	            JSONObject jsonObj = new JSONObject(jsonResults.toString());
	            JSONArray resJsonArray = jsonObj.getJSONArray("results");

	            resultList = new ArrayList<Place>(resJsonArray.length());
	            for (int i = 0; i < resJsonArray.length(); i++) {
	                Place place = new Place();
	                place.setName(resJsonArray.getJSONObject(i).getString("name"));
	                place.setId(resJsonArray.getJSONObject(i).getString("place_id"));
	                place.setAddress(resJsonArray.getJSONObject(i).getString("formatted_address"));
	                jsonObj = resJsonArray.getJSONObject(i); 
	                place.setLatitude(jsonObj.getJSONObject("geometry").getJSONObject("location").getDouble("lat"));
	                place.setLongitude(jsonObj.getJSONObject("geometry").getJSONObject("location").getDouble("lng"));
	                resultList.add(place);
	            }
	        } catch (JSONException e) {
	        	System.out.println("Error processing JSON results");
	        }

	        return resultList;
	    }

	   
	    
}