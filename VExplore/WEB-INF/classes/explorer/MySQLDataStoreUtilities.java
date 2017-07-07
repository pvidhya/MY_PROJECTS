package explorer;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.math.*;

public class MySQLDataStoreUtilities
{
	
	private static MySQLDataStoreUtilities self;
	public Connection sql_connection;
	
	public static MySQLDataStoreUtilities getInstance(){
		if(self == null){
			self = new MySQLDataStoreUtilities();
		}
		return self;
	}
	
	public MySQLDataStoreUtilities(){
		sql_connection = getConnection(); 
	}
	
	public void acceptFrndReq(int sender,int reciever){
		
		String sql_qry = "update ur_notifications set status = 'ACCEPTED' where user_id_sender = "+sender+" and user_id_reciever = "+reciever+";";
		try{
			
			PreparedStatement ps_qry = sql_connection.prepareStatement(sql_qry);
			ps_qry.executeUpdate();
			ps_qry.close();
			
		}catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		sql_qry = "insert into usr_friends(user_id,friend_id) values("+reciever+","+sender+");";
		try{
			
			PreparedStatement ps_qry = sql_connection.prepareStatement(sql_qry);
			ps_qry.executeUpdate();
			ps_qry.close();
			
		}catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static boolean friendCheck(int id,int id2){
		ResultSet rs=null;
		MySQLDataStoreUtilities.getInstance();
		int flg=0;
		try{
			

			rs = self.sql_connection.createStatement().executeQuery("select idt.id id from ((select main.id from ((select user_id_sender id from ur_notifications where notif_type='FRND_RQST' and status='ACCEPTED' and (user_id_sender="+id+" or user_id_reciever="+id+"))union(select user_id_reciever id from ur_notifications where notif_type='FRND_RQST' and status='ACCEPTED' and (user_id_sender="+id+" or user_id_reciever="+id+"))) main where main.id !="+id+") idt left outer join usr_acc uc on uc.user_id=idt.id)");
		while(rs.next()){
			if (rs.getInt("id")==id2){
				flg=1;
			}
		}
		}catch (Exception e){
			e.printStackTrace();
		}
		if (flg==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static void  add_friend(int id1, int id){
		ResultSet rs=null;
		MySQLDataStoreUtilities.getInstance();
		int notifid=0;
	
		try{
			rs = self.sql_connection.createStatement().executeQuery("select max(notif_id)+1 id from ur_notifications");
			while(rs.next())
			{
				notifid=rs.getInt("id");
			}

			self.sql_connection.createStatement().executeUpdate("insert into ur_notifications values("+id1+","+ id+","+ notifid+",\"FRND_RQST\",\"REQUEST\",now(),NULL)");
		
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void  accept_request(String id1, String id, String status){
		String stat= "";
		MySQLDataStoreUtilities.getInstance();
		if (status.equals("Accept-Request")){
			stat= "ACCEPTED";
		}
		else
		{
			stat= "REJECTED";
		}
		try{
			self.sql_connection.createStatement().executeUpdate("update ur_notifications set status=\""+stat+"\" where user_id_sender="+id+" and user_id_reciever="+id1 +" and notif_type= \"FRND_RQST\"");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static ResultSet  friend_request( int id1){
		ResultSet rs=null;
		MySQLDataStoreUtilities.getInstance();
		
		try{
			rs = self.sql_connection.createStatement().executeQuery("select sub.fname,sub.lname,sub.id  from (select user_id_sender id from ur_notifications where user_id_reciever="+ id1+" and status=\"REQUEST\" and notif_type= \"FRND_RQST\") master left outer join (select fname,lname , user_id id from usr_acc) sub on sub.id=master.id");
		}catch (Exception e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public static boolean friendReqCheck(int id,int id2){
		ResultSet rs=null;
		MySQLDataStoreUtilities.getInstance();
		int flg=0;
		try{

			rs = self.sql_connection.createStatement().executeQuery("select idt.id id from ((select main.id from ((select user_id_sender id from ur_notifications where notif_type='FRND_RQST' and status='REQUEST' and (user_id_sender="+id+" or user_id_reciever="+id+"))union(select user_id_reciever id from ur_notifications where notif_type='FRND_RQST' and status='REQUEST' and (user_id_sender="+id+" or user_id_reciever="+id+"))) main where main.id !="+id+") idt left outer join usr_acc uc on uc.user_id=idt.id)");
			while(rs.next()){
				if (rs.getInt("id")==id2)
				{
					flg=1;
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		if (flg==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public void JoinItinerary(int itinryid,int sender,int reciever){
		
		int id = (int) ((new java.util.Date().getTime()) % Integer.MAX_VALUE);
		
		String sql_qry = "insert into ur_notifications(user_id_sender,user_id_reciever,notif_id,notif_type,status,timestamp,notif_frn_id) "
						 + "values("+sender+","+reciever+","+id+","+"'ITRY_RQST'"+","+"'PENDING'"+",now(),"+itinryid+");";
		try{
			
			PreparedStatement ps_qry = sql_connection.prepareStatement(sql_qry);
			ps_qry.executeUpdate();
			ps_qry.close();
			
		}catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void rejectItinryReq(int sender,int reciever,int itinryid){
		
		String sql_qry = "update ur_notifications set status = 'REJECTED' where user_id_sender = "+sender+" and user_id_reciever = "+reciever+" and notif_type='ITRY_RQST';";
		try{			
			PreparedStatement ps_qry = sql_connection.prepareStatement(sql_qry);
			ps_qry.executeUpdate();
			ps_qry.close();
		}catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public List<String> getUserNamesIti(int itinerary){
		List<String> names = new ArrayList<String>();
		ResultSet rs=null;
		String sql_qry = "select user_id from usr_itinerary_grp where itinerary_id = "+itinerary+";";
		String usr_qry;
		PreparedStatement user_ps;
		try{			
			PreparedStatement ps_qry = sql_connection.prepareStatement(sql_qry);
			rs = ps_qry.executeQuery();
			while(rs.next()){
				usr_qry = "select fname,lname from usr_acc where user_id = "+rs.getInt(1)+";";
				user_ps = sql_connection.prepareStatement(usr_qry);
				ResultSet r = user_ps.executeQuery();
				String name = "";
				while(r.next()){
					name = r.getString(1) + " " + r.getString(2);
					break;
				}
				names.add(name);
			}
			ps_qry.close();
		}catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return names;
	}
	
	public void acceptItinryReq(int sender,int reciever,int itinryid){
		
		String sql_qry = "update ur_notifications set status = 'ACCEPTED' where user_id_sender = "+sender+" and user_id_reciever = "+reciever+" and notif_type='ITRY_RQST';";
		try{			
			PreparedStatement ps_qry = sql_connection.prepareStatement(sql_qry);
			ps_qry.executeUpdate();
			ps_qry.close();
		}catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		sql_qry = "insert into usr_itinerary_grp(user_id,itinerary_id) values("+sender+","+itinryid+");";
		try{
			
			PreparedStatement ps_qry = sql_connection.prepareStatement(sql_qry);
			ps_qry.executeUpdate();
			ps_qry.close();
			
		}catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public String getItineraryDesc(int itineraryid){
		
		String qry = "select itinerary_desc from usr_itinerary where itinerary_id = "+itineraryid+";";
		String desc = "";
		
		try{	
		
			PreparedStatement ps_itineraries = sql_connection.prepareStatement(qry);
			ResultSet res = ps_itineraries.executeQuery();
			while(res.next()){
				desc = res.getString(1);
				break;
			}
			ps_itineraries.close();
		
		} catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return desc;
	}
	
	public List<Notification> getFriendReq(int userid){
		
		List<Notification> notifs = new ArrayList<Notification>();
		String sql_qry = "select user_id_sender,user_id_reciever,notif_id,notif_type,status,timestamp,notif_frn_id "
				+ "from ur_notifications where user_id_reciever="+userid+" and notif_type = 'ITRY_RQST' and status = 'PENDING';";
		try{	
			PreparedStatement ps_itineraries = sql_connection.prepareStatement(sql_qry);
			ResultSet res = ps_itineraries.executeQuery();
			while(res.next()){
				int sendr_id = res.getInt(1);
				int rcv_id = res.getInt(2);
				String notif_type = res.getString(4);
				String status = res.getString(5);
				Timestamp time = res.getTimestamp(6);
				notifs.add(new Notification(sendr_id, rcv_id, res.getInt(7),notif_type, status, time));
			}
			ps_itineraries.close();
		} catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return notifs;
	}
	
	public List<Notification> getUserNotification(int userid){
		
		List<Notification> notifs = new ArrayList<Notification>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String d = df.format(new Date()) + " 00:00:00";
		String sql_qry = "select user_id_sender,user_id_reciever,notif_id,notif_type,status,timestamp "
				+ "from ur_notifications where user_id_reciever="+userid+" and timestamp > '"+ d +"';";
		try{	
			PreparedStatement ps_itineraries = sql_connection.prepareStatement(sql_qry);
			ResultSet res = ps_itineraries.executeQuery();
			while(res.next()){
				int sendr_id = res.getInt(1);
				int rcv_id = res.getInt(2);
				String notif_type = res.getString(4);
				String status = res.getString(5);
				Timestamp time = res.getTimestamp(6);
				notifs.add(new Notification(sendr_id, rcv_id, notif_type, status, time));
			}
			ps_itineraries.close();
		} catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return notifs;
	}
	
	public void insertItenirary(List<Itinerary> its){
		
		String sql_itineraries = "insert into usr_itinerary ( itinerary_id , itinerary_desc, latd, longd, owner_id, creation_date, visited_date ) values (?,?,?,?,?,?,?);";
		try{
		
			PreparedStatement ps_itineraries = sql_connection.prepareStatement(sql_itineraries);
			
			for(int i = 0; i < its.size(); i++){
				Itinerary iti = its.get(i);
				ps_itineraries.setInt(1, iti.id);
				ps_itineraries.setString(2, iti.desc);
				ps_itineraries.setDouble(3, iti.latd);
				ps_itineraries.setDouble(4, iti.longd);
				ps_itineraries.setInt(5, iti.owner_id);
				ps_itineraries.setDate(6, new java.sql.Date(iti.created.getTime()));
				ps_itineraries.setDate(7, null);
				if(iti.visited != null)
					ps_itineraries.setDate(7, new java.sql.Date(iti.visited.getTime()));
				ps_itineraries.executeUpdate();
			}
			ps_itineraries.close();
		} catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public List<User> getAllUsers(){
		
		List<User> users = new ArrayList<User>();
		String sql_qry = "select user_id,fname,lname,contact,email_id from usr_acc;";
		try{	
			PreparedStatement ps_itineraries = sql_connection.prepareStatement(sql_qry);
			ResultSet res = ps_itineraries.executeQuery();
			while(res.next()){
				int user_id = res.getInt(1);
				String fname = res.getString(2);
				String lname = res.getString(3);
				String contact = res.getString(4);
				String email_id = res.getString(5);
				users.add(new User(user_id,fname,lname,contact,email_id));
			}
			ps_itineraries.close();
		} catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return users;
	}
	
	public List<Itinerary> getUserItineraries(int user_id){
		List<Itinerary> itineraries = new ArrayList<Itinerary>();
		
		String sql_qry = "select itinerary_id , itinerary_desc, latd, longd, owner_id, creation_date, visited_date "
				   + "from usr_itinerary where owner_id= "+user_id+";";
		try{	
			PreparedStatement ps_itineraries = sql_connection.prepareStatement(sql_qry);
			ResultSet res = ps_itineraries.executeQuery();
			while(res.next()){
				int iti_id = res.getInt(1);
				String iti_desc = res.getString(2);
				double iti_latd = res.getDouble(3);
				double iti_long = res.getDouble(4);
				int iti_ownerid = res.getInt(5);
				java.util.Date iti_cdate = res.getDate(6);
				java.util.Date iti_vdate = res.getDate(7);
				itineraries.add(new Itinerary(iti_id,iti_desc,iti_latd,iti_long,iti_ownerid,iti_cdate,iti_vdate));
			}
			ps_itineraries.close();
		} catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return itineraries;
	}
	
	public List<Itinerary> getRelatedItineraries(int user_id){
		List<Itinerary> itineraries = new ArrayList<Itinerary>();
		
		String sql_qry = "select itinerary_id , itinerary_desc, latd, longd, owner_id, creation_date, visited_date "
				   + "from usr_itinerary as iti, usr_acc as acc "
				   + "where acc.user_id in (select friend_id from usr_friends where user_id = "+ user_id +") and acc.user_id = iti.owner_id;";
		try{	
			PreparedStatement ps_itineraries = sql_connection.prepareStatement(sql_qry);
			ResultSet res = ps_itineraries.executeQuery();
			while(res.next()){
				int iti_id = res.getInt(1);
				String iti_desc = res.getString(2);
				double iti_latd = res.getDouble(3);
				double iti_long = res.getDouble(4);
				int iti_ownerid = res.getInt(5);
				java.util.Date iti_cdate = res.getDate(6);
				java.util.Date iti_vdate = res.getDate(7);
				itineraries.add(new Itinerary(iti_id,iti_desc,iti_latd,iti_long,iti_ownerid,iti_cdate,iti_vdate));
			}
			ps_itineraries.close();
		} catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return itineraries;
	}
	
	public static Connection getConnection(){
		
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/explorer?useSSL=true","root","root");
		}catch (Exception e){
			e.printStackTrace();
		 }
		return conn;
	}


	public static void UserUpdate(User user){
		MySQLDataStoreUtilities.getInstance();
		try{
			self.sql_connection.createStatement().executeUpdate("Insert into usr_acc values("+user.user_id+",\""+user.fname+"\",\""+user.lname+"\",\""+user.contact+"\",\""+user.email_id+"\")");
			self.sql_connection.createStatement().executeUpdate("Insert into usr_regist values("+user.user_id+",\""+user.username+"\",\""+user.pass+"\")");

		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static boolean userValidation(String username){
		ResultSet rs=null;
		boolean ret=false;
		MySQLDataStoreUtilities.getInstance();
		try{
			int cnt=0;
			rs = self.sql_connection.createStatement().executeQuery("select username from usr_regist where username=\""+username+"\"" );
			while((rs.next())&&(cnt==0)){
				cnt++;
			}
			if (cnt==0)
				ret=true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return ret;
	}
	
	public static int userID(){
		/*ResultSet rs=null;
		int id=0;
		try{
			Connection conn = getConnection();
			rs = conn.createStatement().executeQuery("select max(user_id) user_id from usr_regist" );
			while(rs.next()){
				id=rs.getInt("user_id");
			}
			id++;
		}catch (Exception e){
			e.printStackTrace();
		}*/
		return (int) ((new Date().getTime()) % Integer.MAX_VALUE);
	}

	public static int userLoginValidation(String username,String pass){
		ResultSet rs = null;
		int cnt = -1;
		MySQLDataStoreUtilities.getInstance();
		try{
			rs = self.sql_connection.createStatement().executeQuery("select user_id from usr_regist where username=\""+username+"\" and pass=\""+pass+"\"");
			while(rs.next()){
				cnt = rs.getInt("user_id");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return cnt;
	}
	
	public static int userID(String username){
		ResultSet rs=null;
		MySQLDataStoreUtilities.getInstance();
		int cnt=0;
		try{
			
			String qry = "select user_id from usr_regist where username=\""+username+"\";";
			PreparedStatement ps = self.sql_connection.prepareStatement(qry);
			rs = ps.executeQuery();
			while(rs.next())
			{
				cnt=rs.getInt("user_id");

			}

		}catch (Exception e){
			e.printStackTrace();
		}
		return cnt;
	}

	public static User userDetails(int userid){
		
		ResultSet rs=null;
		User user=new User();
		MySQLDataStoreUtilities.getInstance();
		try{
			rs = self.sql_connection.createStatement().executeQuery("select * from usr_acc where user_id="+userid);
			while(rs.next()){
				user=new User(userid,"","",rs.getString("fname"),rs.getString("lname"),rs.getString("email_id"),rs.getString("contact"));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return user;
	}

	public static ResultSet visitedTrips(int id){
		
		ResultSet rs=null;
		MySQLDataStoreUtilities.getInstance();
		try{
			rs = self.sql_connection.createStatement().executeQuery("select it.itinerary_desc,it.visited_date from (select distinct it.itinerary_id from( select itinerary_id from usr_itinerary_grp where user_id="+id+" union all select itinerary_id from usr_itinerary where owner_id="+id+") it) main left outer join usr_itinerary it on it.itinerary_id=main.itinerary_id where it.visited_date <=current_timestamp order by it.visited_date");
		}catch (Exception e){
			e.printStackTrace();
		}
		return rs;
	}

	public static ResultSet plannedTrips(int id){
		ResultSet rs=null;
		MySQLDataStoreUtilities.getInstance();
		try{
			rs = self.sql_connection.createStatement().executeQuery("select it.itinerary_desc,it.visited_date from (select distinct it.itinerary_id from( select itinerary_id from usr_itinerary_grp where user_id="+id+" union all select itinerary_id from usr_itinerary where owner_id="+id+") it) main left outer join usr_itinerary it on it.itinerary_id=main.itinerary_id where it.visited_date >current_timestamp order by it.visited_date");
		}catch (Exception e){
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet friendList(int id){
		
		ResultSet rs=null;
		try{
			String qry = "select idt.id,uc.fname,uc.lname from ((select main.id from ((select user_id_sender id from ur_notifications where notif_type='FRND_RQST' and status='ACCEPTED' and (user_id_sender="+id+" or user_id_reciever="+id+"))union(select user_id_reciever id from ur_notifications where notif_type='FRND_RQST' and status='ACCEPTED' and (user_id_sender="+id+" or user_id_reciever="+id+"))) main where main.id !="+id+") idt left outer join usr_acc uc on uc.user_id=idt.id);";
			PreparedStatement ps = sql_connection.prepareStatement(qry);
			rs = ps.executeQuery();

		}catch (Exception e){
			e.printStackTrace();
		}
		return rs;
	}

	public static void insertHobby(String userid,String hobby){
	
		MySQLDataStoreUtilities.getInstance();
		try{			
			Connection conn = self.sql_connection;
			if (conn != null) {
				String insertIntoCustomerRegisterQuery = "INSERT INTO usr_interest(user_id,hobbies) VALUES (?,?);";
				PreparedStatement pst = (PreparedStatement) conn.prepareStatement(insertIntoCustomerRegisterQuery);
				pst.setString(1,userid);
				pst.setString(2,hobby);
				pst.execute();
				System.out.println("executed insert query");
			} 
		}catch(Exception e){
				e.printStackTrace();
		}
	}
				
	public static void insertHobby(int userid,String hobby){
		MySQLDataStoreUtilities.getInstance();
		try{
			Connection conn = self.sql_connection;
			if (conn != null) {
				String insertIntoCustomerRegisterQuery = "INSERT INTO usr_interest(user_id,hobbies) VALUES (?,?);";
				PreparedStatement pst = (PreparedStatement) conn.prepareStatement(insertIntoCustomerRegisterQuery);
				pst.setInt(1,userid);
				pst.setString(2,hobby);
				pst.execute();
				System.out.println("executed insert query");
			} 	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static int insertItinerary(int userid, String it_Desc, String latd, String longd, String currentdate,String visitdate, int n){

		int cnt=0;
		try{ 
		
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy"); 
			java.util.Date date = (java.util.Date)formatter.parse(visitdate);
			SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
			String finalString = newFormat.format(date);
			java.util.Date parsed = newFormat.parse(finalString);
			java.sql.Date sqldate2 = new java.sql.Date(parsed.getTime());
			PreparedStatement pst = self.sql_connection.prepareStatement("INSERT INTO explorer.usr_itinerary(itinerary_desc, latd, longd, owner_id, creation_date, visited_date,it_grp_name) VALUES (?,?,?,?,?,?,?)");
	 
			pst.setString(1,it_Desc);
			pst.setBigDecimal(2,new BigDecimal(latd));
			pst.setBigDecimal(3,new BigDecimal(longd));
			pst.setInt(4,userid);
			pst.setDate(5, new java.sql.Date(System.currentTimeMillis()));
			pst.setDate(6,sqldate2);
			pst.setString(7,Integer.toString(n));
			cnt = pst.executeUpdate();
			
			
		}catch (Exception e){
				e.printStackTrace();
		}
		return cnt;
	}
	
	public static int insertNotification(int senderid, String receiverid, String Notification_type, String Notification_status){

		int cnt=0;
		try{ 
			PreparedStatement pst = self.sql_connection.prepareStatement("INSERT INTO explorer.ur_notifications(user_id_sender, user_id_reciever, notif_type, status,timestamp) VALUES (?,?,?,?,?)");
			java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	 
			pst.setInt(1,senderid);
			pst.setInt(2,java.lang.Integer.parseInt(receiverid));
			pst.setString(3,Notification_type);
			pst.setString(4,Notification_status);
			pst.setDate(5,new java.sql.Date(System.currentTimeMillis()));
			cnt = pst.executeUpdate();
			System.out.println("status from sql try"+cnt);
		
		}
		catch (Exception e){
			e.printStackTrace();
			 System.out.println("status from sql catch"+cnt);
		}
		return cnt;
	}
	
	public static HashMap<Integer,ArrayList<String>> selectInterest(int userid){
		
		MySQLDataStoreUtilities.getInstance();
		System.out.println("Inside Select");
		HashMap<Integer,ArrayList<String>> hm = new HashMap<Integer,ArrayList<String>>();
		try{
			Connection conn = self.sql_connection;
			String selectUserQuery = "select hobbies from usr_interest where user_id = ?;";
			PreparedStatement pst = (PreparedStatement) conn.prepareStatement(selectUserQuery);
			pst.setInt(1, userid);
			ResultSet rs  = pst.executeQuery();
			ArrayList<String> hobbies = new ArrayList<String>();
			while(rs.next()){
					hobbies.add(rs.getString("hobbies"));		
					hm.put(userid, hobbies);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return hm;
	}
}
