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
import net.sf.json.JSONObject;

/**
 * Servlet implementation class UpdateQuantityCartController
 */
@WebServlet("/UpdateQuantityOrder")
public class UpdateQuantityOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateQuantityOrderController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Map<Integer, Order> orders = (Map<Integer, Order>) session.getAttribute("orders");
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		Gson gson = new Gson();
		JsonObject jsonResponse = new JsonObject();
		String json = gson.toJson("0");
		for (Order order : orders.values()) {
			if (order.getId() == product_id){
				order.setQuantity(quantity);
				int updatedTotalPrice = quantity * order.getPrice();
		        jsonResponse.addProperty("updatedPrice", updatedTotalPrice);
				break;
			}
		}
		
		
		long totalPrice = 0;
		for(Order order : orders.values())
	    {
	        totalPrice+=(order.getPrice()*order.getQuantity());
	    }
		session.setAttribute("totalPrice", totalPrice);
		jsonResponse.addProperty("totalPrice", totalPrice);
		 
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(jsonResponse));
		
	}

}
