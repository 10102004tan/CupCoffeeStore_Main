package Controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.AccountDao;
import Dao.ProductDao;
import Entiny.Account;
import Entiny.Product;

/**
 * Servlet implementation class ManageProductController
 */
@WebServlet("/ManageProduct")
public class ManageProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//data
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		//lay session email va kiem tra xem phai admin khong
		if (session.getAttribute("nameAdmin") != null)
		{
			ArrayList<Product> products = new ArrayList<Product>();
			String url = "ManageProduct?";
			int currentPage = 0;
			int perPage = 10;
			int endPage = 0;
			if (request.getParameter("currentPage") == null)
			{
				currentPage = 1;
			}
			else {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			
			int total =  ProductDao.getTotalProducts();
			
			//tinh endPage
			endPage = total / perPage;
			
			if (total % perPage != 0)
			{
				endPage++;
			}
			
			//lay account theo perPage
			products =  ProductDao.getAllProduct((currentPage - 1)*perPage, perPage);
			
			//set Attribute
			request.setAttribute("products",products);
			request.setAttribute("endPage", endPage);
			request.setAttribute("url", url);
			// chuyen trang
			request.getRequestDispatcher("manage-product.jsp").forward(request, response);
		}
		else {
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
				
	}


}
