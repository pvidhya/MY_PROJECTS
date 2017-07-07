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

public class RequestJoin extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
		
		
		
		List<Notification> li = MySQLDataStoreUtilities.getInstance().getUserNotification(2);
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		out.println("<!doctype html>");
        out.println("<html>");
        out.println("<head>");
		/*out.println("<style>");
		out.println("div.showity { width:500px; background-color:#677;}");
		out.println("table { background-color:#677; }");
		out.println("Label { font-family:Georgia,serif,Times; font-size:20px; }");
		out.println("ul { list-style:none; }");
		out.println("ul li { font-family:Georgia,serif,Times; font-size:18px; }");
		out.println("ul li a { display:block; width:500px; height:28px; background-color:#333; border-left:5px solid #222; border-right:5px solid #222; padding-left:10px;text-decoration:none; color:#777; }");
		out.println("ul li a:hover { -moz-transform:rotate(-5deg); -moz-box-shadow:10px 10px 20px #000000;-webkit-transform:rotate(-5deg); -webkit-box-shadow:10px 10px 20px #000000;transform:rotate(-5deg); box-shadow:10px 10px 20px #000000; color:#777;}");
		out.println("</style>");*/
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
		/*out.println("<ul>");
		for(int i = 0; i < li.size();i++){
			out.println("<li>");
			out.println("<div class=\"showity\">");
			out.println("<table>");
			out.println("<tr>");
			out.println("<td><a href=\"#\">"+li.get(i).getNotificationText()+"</a></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</div>");
			out.println("</li>");
			out.println("<br />");
		}
		out.println("</ul>");*/
		out.println("</body>");
		out.println("</html>");
	}
}