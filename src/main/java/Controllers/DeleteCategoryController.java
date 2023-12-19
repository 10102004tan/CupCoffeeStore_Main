package Controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Dao.CategoryDao;
import Entiny.Category;

/**
 * Servlet implementation class DeleteCategoryController
 */
@WebServlet("/DeleteCategory")
public class DeleteCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCategoryController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// data
		int id = 0;

		if (request.getParameter("category_id") != null) {
			id = Integer.parseInt(request.getParameter("category_id"));
			CategoryDao.deleteCategory(id);
		}

		ArrayList<Category> categories = CategoryDao.getCategories();
		// set Attribute
		request.setAttribute("categories", categories);

		response.sendRedirect("ManageCategory");

	}

}
