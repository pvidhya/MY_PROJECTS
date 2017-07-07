import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import explorer.*;

public class AcceptJoinItinerary extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
		
		int reciever = Integer.parseInt(request.getParameter("reciever"));
		int sender = Integer.parseInt(request.getParameter("sender"));
		int itinryid = Integer.parseInt(request.getParameter("itinry_id"));
		String action = request.getParameter("Request");
		if(action.equals("Accept")){
			MySQLDataStoreUtilities.getInstance().acceptItinryReq(sender,reciever,itinryid);
		}else{
			MySQLDataStoreUtilities.getInstance().rejectItinryReq(sender,reciever,itinryid);
		}
		response.sendRedirect(response.encodeRedirectURL("/Explorer/ItineraryReq.jsp"));
	}
}