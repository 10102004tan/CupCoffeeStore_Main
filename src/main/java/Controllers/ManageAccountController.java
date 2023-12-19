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
 * Servlet implementation class ManageAccountController
 */
@WebServlet("/ManageAccount")
public class ManageAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageAccountController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//data
		
		HttpSession session = request.getSession();
		if (session.getAttribute("nameAdmin")!= null)
		{
			ArrayList<Account> accounts = new ArrayList<Account>();
			String url = "ManageAccount?";
			int currentPage = 0;
			int perPage = 12;
			int endPage = 0;
			if (request.getParameter("currentPage") == null)
			{
				currentPage = 1;
			}
			else {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			
			int total =  AccountDao.getTotalAccounts();
			
			//tinh endPage
			endPage = total / perPage;
			
			if (total % perPage != 0)
			{
				endPage++;
			}
			
			//lay account theo perPage
			accounts = AccountDao.getAccounts((currentPage - 1)*perPage, perPage);
			
			//set Attribute
			request.setAttribute("accounts",accounts);
			request.setAttribute("endPage", endPage);
			request.setAttribute("url", url);
			// chuyen trang
			request.getRequestDispatcher("manage-account.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}



}
