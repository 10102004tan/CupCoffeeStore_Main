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
 * Servlet implementation class AddCategoryController
 */
@WebServlet("/AddCategory")
public class AddCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategoryController() {
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
		
		//Chuan bi du lieu
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");	
				String name = "";
				ArrayList<Category> categories = CategoryDao.getCategories();
				
				if (request.getParameter("name") != null)
				{
					name = request.getParameter("name");
					
					//prosessing dao
					Category category = new Category(name);
					CategoryDao.addCategory(category);
					
				}
				
				
				//chuyen trang
				
				response.sendRedirect("ManageCategory");
				
	}

}
