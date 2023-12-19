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
 * Servlet implementation class CartController
 */
@WebServlet("/Cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		long totalPrice = 0;
		Map<Integer, Order> orders = (Map<Integer, Order>) session.getAttribute("orders");
		if (orders == null) {
			orders = new HashMap<>();
			session.setAttribute("orders", orders);
		}
		for (Order order : orders.values()){
			totalPrice += (order.getPrice() * order.getQuantity());
		}
		session.setAttribute("totalPrice", totalPrice);
		request.getRequestDispatcher("shopping-cart.jsp").forward(request, response);
	}

}
