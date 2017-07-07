import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.*;
import explorer.*;

public class CreateItenirary extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession(true);
		int id = MySQLDataStoreUtilities.getInstance().userID((String)session.getAttribute("username"));
		List<Itinerary> lit = new ArrayList<Itinerary>();
		Itinerary it = null;
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
			String paramValue = paramValues[0];
			String[] v = paramValue.split(";");
			
			it = new Itinerary(paramName,Double.parseDouble(v[0]), Double.parseDouble(v[1]), id); 
			lit.add(it);
        }
		MySQLDataStoreUtilities.getInstance().insertItenirary(lit);
		response.sendRedirect(response.encodeRedirectURL("/Explorer/explorer_userHome.jsp"));
	}
}