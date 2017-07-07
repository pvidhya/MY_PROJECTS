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

public class AcceptFrndReq extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
		
		int sender = Integer.parseInt(request.getParameter("sender"));
		int reciever = Integer.parseInt(request.getParameter("reciever"));
		
		MySQLDataStoreUtilities.getInstance().acceptFrndReq(sender,reciever);
		response.sendRedirect(response.encodeRedirectURL("/Explorer/notifications"));
	}
}