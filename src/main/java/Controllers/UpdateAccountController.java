package Controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.AccountDao;
import Entiny.Account;
import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Servlet implementation class UpdateAccountController
 */
@WebServlet("/UpdateAccount")
public class UpdateAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAccountController() {
        super();
        // TODO Auto-generated constructor stub
    }



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//lay du lieu
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
		Account temp = AccountDao.getAccountById(id);
		if (temp.getPassword().equals(password))
		{
			bcryptHashString = temp.getPassword();
		}
		
		String phone = request.getParameter("phone");
		int role = Integer.parseInt(request.getParameter("role"));
		int status = Integer.parseInt(request.getParameter("status"));
		Account account = new Account(name, email, bcryptHashString);
		account.setPhone(phone);
		account.setRole(role);
		account.setId(id);
		account.setStatus(status);
		
		
		if (AccountDao.updateAccount(account))
		{
			response.sendRedirect("ManageAccount");
		}
		else {
			response.getWriter().print("loi");
		}
	}

}
