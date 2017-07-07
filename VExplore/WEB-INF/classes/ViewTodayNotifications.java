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

public class ViewTodayNotifications extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
		
		String username = (String) request.getSession().getAttribute("username");
		int id = MySQLDataStoreUtilities.userID(username);
		
		List<Notification> li = MySQLDataStoreUtilities.getInstance().getUserNotification(id);
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
		out.println("<section>");
		for(int i = 0; i < li.size();i++){
			out.println("<div style=\"width:500px;height:100px;border:solid\">");
			out.println("<table>");
			out.println("<tr>");
			out.println("<td><Label style=\"font-family:Georgia,serif,Times;font-size:20px\">Sent on: "+li.get(i).getTimeStamp()+"</Label>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><label style=\"font-family:Georgia,serif,Times;font-size:20px\">"+li.get(i).getNotificationText()+"</label></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</div>");
			out.println("<br />");
		}
		out.println("</section>");
		out.println("</body>");
		out.println("</html>");
	}
}