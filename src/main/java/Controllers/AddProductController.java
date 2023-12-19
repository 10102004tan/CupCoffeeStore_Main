package Controllers;

import java.io.File;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import Dao.ProductDao;
import Entiny.Product;

/**
 * Servlet implementation class AddProductController
 */
@MultipartConfig(maxFileSize = 209715200)
@WebServlet("/AddProduct")
public class AddProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("add-product.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String desc = request.getParameter("desc");
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		String fileName  = "";
		Part filePart = request.getPart("image");
		if (filePart != null) {
		// Obtains input stream of the upload file
			String uploadPath = request.getServletContext().getRealPath("/images/") + File.separator + filePart.getSubmittedFileName();
			try {
    			FileOutputStream fos = new FileOutputStream(uploadPath);
    			InputStream is = filePart.getInputStream();
    			byte[] data = new byte[is.available()];
    			fileName = filePart.getSubmittedFileName();
    			is.read(data);
    			fos.write(data);
    			fos.close();

    		} catch (Exception e) {
    			// TODO: handle exception
    		}
		}
		Product product = new Product(name,desc,price,category_id);
		product.setUrlImage(fileName);
		product.setQuantity(quantity);
		
		if (ProductDao.addProduct(product))
		{
			HttpSession session = request.getSession();
			session.setAttribute("mess", "Add product success");
			response.sendRedirect("ManageProduct");
		}

		

		
				
		
	}

}
