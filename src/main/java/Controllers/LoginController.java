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
import Entiny.Account;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	HttpSession session = request.getSession();
		if (session.getAttribute("name") == null && session.getAttribute("nameAdmin") == null)
		{
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String email = request.getParameter("email");
		String pw = request.getParameter("password");
		if (AccountDao.checkLogin(email, pw))
		{
			HttpSession session = request.getSession();
			Account account = AccountDao.getAccountByEmail(email);
			
			if (account.getRole() == 0)
			{
				session.setAttribute("nameAdmin", account.getName());
				response.sendRedirect("ManageAccount");
			}
			else {
				session.setAttribute("name", account.getName());
				response.sendRedirect("Home");
			}
			
		}
		else {
			request.setAttribute("mess", "Email or password false");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
