package Controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CategoryDao;
import Entiny.Category;

/**
 * Servlet implementation class FindCategoryController
 */
@WebServlet("/FindCategory")
public class FindCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//data
		String name = request.getParameter("name");
		
		
		//prosessing with db
		ArrayList<Category> categories = CategoryDao.getCategoryByName(name);

		
		
		//set Attribute
		request.setAttribute("categories", categories);
		
		
		//chuyen trang
		request.getRequestDispatcher("manage-category.jsp").forward(request, response);
		
	}


}
