package richard.angularservletbeers.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import richard.angularservletbeers.dao.BeerMongoDAO;
import richard.angularservletbeers.model.Beer;

/**
 * Servlet implementation class BeerDetail
 */
@WebServlet("/BeerDetail")
public class BeerDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BeerDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if ((null == request.getParameter("id")) || ("" == request.getParameter("id"))) {
			response.setStatus(402);
			response.getWriter().append("Missing id");
		}
		
		String id = request.getParameter("id");
		Beer beer = BeerMongoDAO.getBeerMongoDAOInstance().getBeer(id);
		ObjectMapper mapper = new ObjectMapper();
		String jsonBeer = mapper.writeValueAsString(beer);
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(jsonBeer);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
