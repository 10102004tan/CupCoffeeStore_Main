package Controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ProductDao;
import Entiny.Product;

/**
 * Servlet implementation class FindProductController
 */
@WebServlet("/FindProduct")
public class FindProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if (session.getAttribute("name") != null)
		{
			String keyWord = request.getParameter("keyword");
			ArrayList<Product> products = new ArrayList<Product>();
			String url = "FindProduct?keyWord=" + keyWord + "&";
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
			// chuyen trang
			request.getRequestDispatcher("manage-product.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	

}
