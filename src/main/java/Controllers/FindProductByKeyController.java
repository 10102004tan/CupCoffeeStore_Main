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
 * Servlet implementation class FindProductByKeyController
 */
@WebServlet("/FindProductByKey")
public class FindProductByKeyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindProductByKeyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//data
		ArrayList<Category> categories = CategoryDao.getCategories();
		String keyWord = request.getParameter("key");
		ArrayList<Product> products = new ArrayList<Product>();
		String url = "FindProductByKey?key=" + keyWord + "&";
		int currentPage = 0;
		int perPage = 3;
		int endPage = 0;
		if (request.getParameter("currentPage") == null)
		{
			currentPage = 1;
		}
		else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		int total =  ProductDao.getTotalProductsByKeyWord(keyWord);
		
		//tinh endPage
		endPage = total / perPage;
		
		if (total % 3 != 0)
		{
			endPage++;
		}
		
		//lay account theo perPage
		products =  ProductDao.findProductByKeyWord((currentPage - 1)*perPage, perPage,keyWord);
		
		//set Attribute
		request.setAttribute("products",products);
		request.setAttribute("endPage", endPage);
		request.setAttribute("url", url);
		request.setAttribute("categories", categories);
		// chuyen trang
		request.getRequestDispatcher("shop.jsp").forward(request, response);
	}


}
