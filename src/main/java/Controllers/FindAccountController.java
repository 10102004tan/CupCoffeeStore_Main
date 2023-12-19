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
 * Servlet implementation class FindAccountController
 */
@WebServlet("/FindAccount")
public class FindAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAccountController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String keyword = request.getParameter("keyword");
		ArrayList<Account> accounts = new ArrayList<Account>();
		String url = "FindAccount?keyword=" + keyword + "&";
		int currentPage = 0;
		int perPage = 5;
		int endPage = 0;
		if (request.getParameter("currentPage") == null)
		{
			currentPage = 1;
		}
		else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		//lay account theo perPage
		accounts = AccountDao.findAccount(keyword,(currentPage - 1)*perPage, perPage);
		int total = AccountDao.getTotalAccountsByKeyWord(keyword);
		//tinh endPage
		endPage = total / perPage;
		if (total % 5 != 0)
		{
			endPage++;
		}
		//set Attribute
		request.setAttribute("accounts",accounts);
		request.setAttribute("endPage", endPage);
		request.setAttribute("url", url);
		// chuyen trang
		request.getRequestDispatcher("manage-account.jsp").forward(request, response);
	}



}
