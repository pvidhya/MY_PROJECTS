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

public class JoinItinerary extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
		
		int reciever = Integer.parseInt(request.getParameter("recvr"));
		int sender = Integer.parseInt(request.getParameter("sender"));
		int itinryid = Integer.parseInt(request.getParameter("itinryid"));
		MySQLDataStoreUtilities.getInstance().JoinItinerary(itinryid,sender,reciever);
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		out.println("<!doctype html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
        out.println("<title>VExplorer</title>");
        out.println("<meta name=\"viewport\" content=\"width=device-width, minimum-scale=1.0, maximum-scale=1.0\" />");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>Request to Join has been sent.</p>");
		out.println("</body>");
		out.println("</html>");
	}
}