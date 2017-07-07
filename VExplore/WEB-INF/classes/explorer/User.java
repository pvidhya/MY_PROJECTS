package explorer;
public class User{
	public String username;
	public String pass;
	public String fname;
	public String lname;
	public String email_id;
	public String contact;
	public int user_id;
	
	public User(){
		
	}
	
	public  User (int user_id,String username,String pass, String fname,String lname,String email_id,String contact){
	  this.username=username;
	  this.pass=pass;
	  this.fname=fname;
	  this.lname=lname;
	  this.email_id=email_id;
	  this.contact=contact;
	  this.user_id=user_id;
	}
	
	public String getFullName(){
		return fname+" "+lname;
	}
	
	public  User (int user_id,String fname,String lname,String email_id,String contact){
		  this.fname=fname;
		  this.lname=lname;
		  this.email_id=email_id;
		  this.contact=contact;
		  this.user_id=user_id;
	}
}