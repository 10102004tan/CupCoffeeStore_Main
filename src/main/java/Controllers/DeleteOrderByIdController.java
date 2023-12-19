package Controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Entiny.Order;

/**
 * Servlet implementation class DeleteOrderByIdController
 */
@WebServlet("/DeleteOrderById")
public class DeleteOrderByIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteOrderByIdController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Map<Integer, Order> orders = (Map<Integer, Order>) session.getAttribute("orders");
		Gson gson = new Gson();
		JsonObject jsonResponse = new JsonObject();
		String json = gson.toJson(false);
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		for (Order order : orders.values()) {
			if (order.getId() == product_id){
				
				orders.remove(order.getId());
				break;
			}
		}
		
		long totalPrice = 0;
		for(Order order : orders.values())
	    {
	        totalPrice+=(order.getPrice()*order.getQuantity());
	    }
		session.setAttribute("totalPrice", totalPrice);
		jsonResponse.addProperty("sizeOrders", orders.size());
		jsonResponse.addProperty("totalPrice", totalPrice);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(jsonResponse));
		
		
		

	}

}
