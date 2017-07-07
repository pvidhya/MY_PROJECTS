package explorer;

import java.util.HashMap;
import java.util.List;

public class UserAccounts {
	HashMap <Integer,User> users;
	private static UserAccounts self;
	
	public static UserAccounts getInstance(){
		if(self == null){
			self = new UserAccounts();
		}
		return self;
	}
	
	private UserAccounts(){
		users = new HashMap<Integer,User>();
		load_users();
	}
	
	public User getUser(int userid){
		return users.get(userid);
	}
	
	public void load_users(){
		List<User> usrs = MySQLDataStoreUtilities.getInstance().getAllUsers();
		for(int i = 0; i < usrs.size();i++){
			User u = usrs.get(i);
			users.put(u.user_id, u);
		}
	}
	
}
