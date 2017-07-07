import java.io.*;
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ser_sign
 */
@WebServlet("/ser_sign")
public class ser_sign extends HttpServlet {
	
	HashMap<String, String> hm = new HashMap<String, String>();
	String TOMCAT_HOME = "C:\\apache-tomcat-7.0.34";
	String error_msg = "";
	protected void doPost(
		HttpServletRequest request, 
	   HttpServletResponse response
	   ) throws ServletException, IOException {
		String username = request.getParameter("username");
		//String email = request.getParameter("email");
		String password = request.getParameter("password");
		String user_type = request.getParameter("user_type");
	try
	{
	FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\csj\\source\\UserDetails.ser"));
	ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

	hm= (HashMap)objectInputStream.readObject();
	}
	catch(Exception io) {
	    //throw new ServletException(e);
	}
	if(hm.containsKey(username)){
	 showPage(response, "User alreday exists.");
	}
	else{  
	//User user = new User(username,password);
	hm.put(username, password);
	FileOutputStream fileOutputStream = new FileOutputStream(TOMCAT_HOME+"\\webapps\\csj\\source\\UserDetails.ser");
	ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
	objectOutputStream.writeObject(hm);
	objectOutputStream.flush();
	objectOutputStream.close();
	fileOutputStream.close();
	showPage(response, "Congratulations! Your account has been successfully created.");

	}
	}
	protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login Servlet Result</title>");  
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>" + message + "</h2>");
        out.println("</body>");
        out.println("</html>");
        out.close();
 
    }
}
