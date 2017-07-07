package explorer;

import java.util.Date;

public class Itinerary {
	
	public int id;
	public String desc;
	public double latd;
	public double longd;
	public int owner_id;
	public Date created;
	public Date visited;
	
	public Itinerary(String description, double latitude, double longitude, int owner_id){
		
		id = (int) ((new Date().getTime()) % Integer.MAX_VALUE);
		desc = description;
		latd = latitude;
		longd = longitude;
		this.owner_id = owner_id;
		created = new Date();
	}

	public Itinerary(int id,String description, double latitude, double longitude, int owner_id, Date ucreated, Date uvisited){
		this.id = id;
		desc = description;
		latd = latitude;
		longd = longitude;
		this.owner_id = owner_id;
		created = ucreated;
		visited = uvisited;
	}
	
}
