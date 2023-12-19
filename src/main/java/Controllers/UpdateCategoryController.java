package Controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Dao.CategoryDao;
import Entiny.Category;

/**
 * Servlet implementation class UpdateCategoryController
 */
@WebServlet("/UpdateCategory")
public class UpdateCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCategoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// data
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
				int id = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				Category category = new Category(name);
				category.setId(id);
				Gson gson = new Gson();
				String json ;
				if (CategoryDao.updateCategory(category))
				{
					json =  gson.toJson(true);
				}
				else {
					json = gson.toJson(false);
				}
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
				
				
	}

}
