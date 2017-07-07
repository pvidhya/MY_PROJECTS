package explorer;
import java.sql.Timestamp;

public class Notification {
	
	public static enum status_c{
		PENDING,
		ACCEPTED,
		DECLINED
	}
	
	public static enum notif_type_c{
		FRND_RQST,
		ITRY_RQST,
		DEFAULT
	}
	
	public int sender;
	public int receiver;
	public notif_type_c notif_type;
	public status_c status;
	public Timestamp timestamp;
	public int notif_frn_id;
	
	public Notification(int sndr,int rcv,int notif_frn_id,String notif,String stats,Timestamp time){
		sender = sndr;
		receiver = rcv;
		timestamp = time;
		this.notif_frn_id = notif_frn_id;
		
		switch(notif){
			case "FRND_RQST":
				notif_type = notif_type_c.FRND_RQST;
				break;
			case "ITRY_RQST":
				notif_type = notif_type_c.ITRY_RQST;
				break;
			default:
				notif_type = notif_type_c.DEFAULT;
				break;
		}
		
		switch(stats){
			case "ACCEPTED":
				status = status_c.ACCEPTED;
				break;
			case "DECLINED":
				status = status_c.DECLINED;
				break;
			default:
				status = status_c.PENDING;
				break;
		}
	}
	
	public Notification(int sndr,int rcv,String notif,String stats,Timestamp time){
		
		sender = sndr;
		receiver = rcv;
		timestamp = time;
		
		switch(notif){
			case "FRND_RQST":
				notif_type = notif_type_c.FRND_RQST;
				break;
			case "ITRY_RQST":
				notif_type = notif_type_c.ITRY_RQST;
				break;
			default:
				notif_type = notif_type_c.DEFAULT;
				break;
		}
		
		switch(stats){
			case "ACCEPTED":
				status = status_c.ACCEPTED;
				break;
			case "DECLINED":
				status = status_c.DECLINED;
				break;
			default:
				status = status_c.PENDING;
				break;
		}
	}
	
	public String getStatus(){
		String ret = "";
		switch(status){
			case ACCEPTED:
				ret = "ACCEPTED";
				break;
			case PENDING:
				ret = "PENDING";
				break;
			case DECLINED:
				ret = "DECLINED";
				break;
		}
		return ret;
	}
	
	public String getNotiftype(){
		String ret = "";
		switch(notif_type){
			case FRND_RQST:
				ret = "FRND_RQST";
				break;
			case ITRY_RQST:
				ret = "ITRY_RQST";
				break;
			default:
				ret = "DEFAULT";
				break;
		}
		return ret;
	}
	
	public Timestamp getTimeStamp(){
		return timestamp;
	}
	
	public String getNotificationText(){
		String ret = "";
		User sender = UserAccounts.getInstance().getUser(this.sender);
		switch(notif_type){
			case FRND_RQST:
				ret = "<a href=\"#\">" + sender.fname + " " + sender.lname + "</a>"+" has sent a friend request. ";
				break;
			case ITRY_RQST:
				ret = "<a href=\"#\">" + sender.fname + " " + sender.lname + "</a>"+" has sent a Itinerary request. ";
				break;
			default:
				notif_type = notif_type_c.DEFAULT;
				ret = sender.fname + " " + sender.lname+" user has sent a Notification. ";
				break;
		}
		
		switch(status){
			case ACCEPTED:
				ret += "Accepted :)";
				break;
			case PENDING:
				ret += "Pending :(";
				ret += "<br/><br/>";
				String qry_str = "sender="+this.sender+"&reciever="+this.receiver;
				if(notif_type == notif_type_c.FRND_RQST)
					ret += "<a href=\"acceptfrdnreq?"+qry_str+"\"> Accept </a> &nbsp" + "<a href=\"rejectfrndreq?"+qry_str+"\"> Reject </a>";
				else if(notif_type == notif_type_c.ITRY_RQST){
					qry_str += "&itinry_id="+notif_frn_id;
					ret += "<a href=\"acceptjoinreq?"+qry_str+"\"> Accept </a> &nbsp" + "<a href=\"rejectjoinreq?"+qry_str+"\"> Reject </a>";
				}
				break;
			case DECLINED:
				ret += "Declined :/";
				break;
		}
		return ret;
	}
}
