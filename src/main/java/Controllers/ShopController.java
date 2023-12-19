package Controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CategoryDao;
import Dao.ProductDao;
import Entiny.Category;
import Entiny.Product;

/**
 * Servlet implementation class ShopController
 */
@WebServlet("/Shop")
public class ShopController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//data
		ArrayList<Category> categories = CategoryDao.getCategories();
		//data
		ArrayList<Product> products = new ArrayList<Product>();
		
		int currentPage = 0;
		int perPage = 6;
		int endPage = 0;
		int total = 0;
		String url = ""; 
		
		
		if (request.getParameter("currentPage") == null)
		{
			currentPage = 1;
		}
		else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		if (request.getParameter("category_id") == null)
		{
			
			url = "Shop?";
			total =  ProductDao.getTotalProducts();
			
			
			products =  ProductDao.getAllProduct((currentPage - 1)*perPage, perPage);
		}
		else {
			int category_id = Integer.parseInt(request.getParameter("category_id"));
			total =  ProductDao.getTotalProductsByCategoryId(category_id);
			url = "Shop?category_id=" + category_id + "&";
			//lay account theo perPage
			products =  ProductDao.getAllProductByCategoryId(category_id,(currentPage - 1)*perPage, perPage);
		}
		//tinh endPage
		endPage = total / perPage;
		
		if (total % perPage != 0)
		{
			endPage++;
		}
		
		//set Attribute
		request.setAttribute("products",products);
		request.setAttribute("endPage", endPage);
		request.setAttribute("url", url);
		request.setAttribute("categories", categories);
		//chuyen trang
		request.getRequestDispatcher("shop.jsp").forward(request, response);
		
		
	}


}
