package Controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.CategoryDao;
import Entiny.Category;

/**
 * Servlet implementation class ManageCategoryController
 */
@WebServlet("/ManageCategory")
public class ManageCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageCategoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//data
		HttpSession session = request.getSession();
		if (session.getAttribute("nameAdmin") != null)
		{
			ArrayList<Category> categories = CategoryDao.getCategories();
			
			
			
			//set Attribute
			request.setAttribute("categories", categories);
			
			
			//chuyen trang
			request.getRequestDispatcher("manage-category.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
				
	}



}
