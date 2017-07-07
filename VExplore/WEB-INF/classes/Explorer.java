
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import explorer.*;

public class Explorer extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GooglePlaces gp = new GooglePlaces();
		HttpSession session = request.getSession(true);
		MySQLDataStoreUtilities ms = new MySQLDataStoreUtilities();
		int id = ms.userID((String)session.getAttribute("username"));

		ArrayList<Place> list = gp.getResultPlaces(id);
		ArrayList<String> latList =  new ArrayList<String>();
		ArrayList<String> longList =  new ArrayList<String>();
		for(Place p : list)
		{		
				Double lati = new Double(p.getLatitude());
				String latitde = lati.toString(); 
				Double longi = new Double(p.getLongitude());
				String longitde = longi.toString(); 
			latList.add(latitde);
			longList.add(longitde);
		}
		request.setAttribute("placesList", list);
		request.setAttribute("latList", latList);
		request.setAttribute("longList", longList);
		request.getRequestDispatcher("/ExplorerPlaces.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}