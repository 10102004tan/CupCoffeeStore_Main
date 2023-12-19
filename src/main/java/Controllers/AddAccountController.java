package Controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.AccountDao;
import Entiny.Account;
import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Servlet implementation class AddAccountController
 */
@WebServlet("/AddAccountController")
public class AddAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddAccountController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("add-account.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// lay du lieu
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
		// $2a$12$US00g/uMhoSBm.HiuieBjeMtoN69SN.GE25fCpldebzkryUyopws6
		// result.verified == true
		String phone = request.getParameter("phone");
		int role = Integer.parseInt(request.getParameter("role"));
		Account account = new Account(name, email, bcryptHashString);
		account.setPhone(phone);
		account.setRole(role);
		//
		if (AccountDao.addAccount(account)) {
			response.sendRedirect("ManageAccount");
		}

	}

}
