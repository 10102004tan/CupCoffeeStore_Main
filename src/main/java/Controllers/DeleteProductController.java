package Controllers;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ProductDao;

/**
 * Servlet implementation class DeleteProductController
 */
@WebServlet("/DeleteProduct")
public class DeleteProductController extends HttpServlet{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("product-id"));
		String filePathToDelete = request.getServletContext().getRealPath("/images/") + File.separator + ProductDao.getImageById(id);
		if (ProductDao.destroyProduct(id))
		{
			File fileToDelete = new File(filePathToDelete);
			if (fileToDelete.exists())
			{
				try {
					fileToDelete.delete();
				} catch (Exception e) {
					
				}
			}
			HttpSession session = request.getSession();
			session.setAttribute("mess", "Remove product success");
			response.sendRedirect("ManageProduct");
			
		}
	}

}
