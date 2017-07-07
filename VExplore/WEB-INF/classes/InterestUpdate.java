import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import explorer.*;
public class InterestUpdate extends HttpServlet {    
    
    public InterestUpdate() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GooglePlaces gp1 = new GooglePlaces();
		HttpSession session = request.getSession(true);
		MySQLDataStoreUtilities ms = new MySQLDataStoreUtilities();
		int id = ms.userID((String)session.getAttribute("username"));
		String hobby  = request.getParameter("interest");
		session.setAttribute("hobby",hobby);
		System.out.println("Hobby is"+hobby);
		ms.insertHobby(id, hobby);
		ArrayList<Place> list = gp1.getResultPlaces(id);
		request.setAttribute("placesList", list);
		request.getRequestDispatcher("/ExplorerPlaces.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
	}

}
