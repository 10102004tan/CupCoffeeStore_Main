package Controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Dao.ProductDao;
import Entiny.Order;
import Entiny.Product;

/**
 * Servlet implementation class AddToCartController
 */
@WebServlet("/AddToCart")
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Gson gson = new Gson();
		long totalPrice = 0;
	        Map<Integer, Order> orders = (Map<Integer, Order>) session.getAttribute("orders");
	        if (orders == null) {
	        	orders = new HashMap<>();
	            session.setAttribute("orders", orders);
	        }
	        int quantity = 1;
	        int product_id = Integer.parseInt(request.getParameter("id"));
	        Product product = ProductDao.getProductById(product_id);
	        Order temp = new Order(product_id,product,quantity,product.getPrice());
	        for (Order order : orders.values())
	        {
	        	if (order.getId() == product_id)
	        	{
	        		temp.setQuantity(order.getQuantity()+ temp.getQuantity());
	        		orders.remove(order.getId());
	        		break;
	        	}
	        }
	        
	        orders.put(product_id,temp);
	        for(Order order : orders.values())
	        {
	        	totalPrice+=(order.getPrice()*order.getQuantity());
	        }
	        session.setAttribute("totalPrice", totalPrice);
	        request.getRequestDispatcher("shopping-cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
