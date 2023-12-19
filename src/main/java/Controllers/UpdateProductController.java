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

import Dao.ProductDao;
import Entiny.Product;

/**
 * Servlet implementation class UpdateProductController
 */
@MultipartConfig(maxFileSize = 209715200)
@WebServlet("/UpdateProduct")
public class UpdateProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProductController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("edit-product.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String desc = request.getParameter("desc");
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		// Input stream of the upload file
		
		String fileName = ProductDao.getImageById(id);
		Part filePart = request.getPart("image");
		if (filePart != null) {
		
			String uploadPath = request.getServletContext().getRealPath("/images/") + File.separator + filePart.getSubmittedFileName();
			try {
    			FileOutputStream fos = new FileOutputStream(uploadPath);
    			InputStream is = filePart.getInputStream();
    			byte[] data = new byte[is.available()];
    			is.read(data);
    			fos.write(data);
    			fileName = filePart.getSubmittedFileName();
    			fos.close();

    		} catch (Exception e) {
    			// TODO: handle exception
    		}
		}
		Product product = new Product(name,desc,price,category_id);
		product.setUrlImage(fileName);
		product.setQuantity(quantity);
		product.setId(id);
		if (ProductDao.updateProduct(product))
		{
			HttpSession session = request.getSession();
			session.setAttribute("mess", "Update product success");
			response.sendRedirect("ManageProduct");
		}
	}

}
