package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import Entiny.Account;
import Entiny.Product;

public class ProductDao {

	private static Connection connection = DataBaseUtil.getConnection();

	// get all product
	public static ArrayList<Product> getAllProduct(int currentPage, int perPage) {
		ArrayList<Product> products = new ArrayList<Product>();

		try {
			PreparedStatement ps = connection.prepareStatement("select * from product ORDER BY id DESC limit ?,? ");
			ps.setInt(1, currentPage);
			ps.setInt(2, perPage);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// data product
				Product product = new Product(rs.getString("name"), rs.getString("description"), rs.getInt("price"),
						rs.getInt("category_id"));
				product.setCreated_at(rs.getTimestamp("created_at"));
				product.setUpdated_at(rs.getTimestamp("updated_at"));
				product.setId(rs.getInt("id"));
				product.setQuantity(rs.getInt("quantity"));
				product.setUrlImage(rs.getString("image"));

				//
				products.add(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return products;
	}

	// get total product
	public static int getTotalProducts() {
		try {
			PreparedStatement ps = connection.prepareStatement("select count(*) from product");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				return rs.getInt(1);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return 0;
	}

	// add product
	public static boolean addProduct(Product product) {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO `product`(`name`, `description`, `price`, `category_id`, `image`,quantity) VALUES (?,?,?,?,?,?)");
			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setInt(3, product.getPrice());
			ps.setInt(4, product.getCategory_id());
			ps.setString(5, product.getUrlImage());
			ps.setInt(6, product.getQuantity());

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

	// get product by id
	public static Product getProductById(int id) {
		try {
			PreparedStatement ps = connection.prepareStatement("select * from product where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product(rs.getString("name"), rs.getString("description"), rs.getInt("price"),
						rs.getInt("category_id"));
				product.setCreated_at(rs.getTimestamp("created_at"));
				product.setUpdated_at(rs.getTimestamp("updated_at"));
				product.setUrlImage(rs.getString("image"));
				product.setQuantity(rs.getInt("quantity"));
				product.setId(rs.getInt("id"));
				return product;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	// update product
	public static boolean updateProduct(Product product) {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE `product` SET `name`=?,`description`=?,`price`=?,`category_id`=?,`image`=?,quantity=?,`updated_at`=? WHERE id = ?");
			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setInt(3, product.getPrice());
			ps.setInt(4, product.getCategory_id());
			ps.setString(5, product.getUrlImage());
			ps.setInt(6, product.getQuantity());
			Timestamp newTimestamp = new Timestamp(System.currentTimeMillis());
			ps.setTimestamp(7, newTimestamp);
			ps.setInt(8, product.getId());

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

	// delete product
	public static boolean destroyProduct(int id) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM `product` WHERE id=?");
			ps.setInt(1, id);

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

	// get image url by id
	public static String getImageById(int id) {
		try {
			PreparedStatement ps = connection.prepareStatement("select image from product where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getString("image");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}
	
	
	public static ArrayList<Product> getProductByKey(String key)
	{
		ArrayList<Product> products = new ArrayList<Product>();

		try {
			PreparedStatement ps = connection
					.prepareStatement("select * from product where name like ? or description like ? limit 0,4");
			ps.setString(1, "%" + key + "%");
			ps.setString(2, "%" + key + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// data product
				Product product = new Product(rs.getString("name"), rs.getString("description"), rs.getInt("price"),
						rs.getInt("category_id"));
				product.setCreated_at(rs.getTimestamp("created_at"));
				product.setUpdated_at(rs.getTimestamp("updated_at"));
				product.setId(rs.getInt("id"));
				product.setQuantity(rs.getInt("quantity"));
				product.setUrlImage(rs.getString("image"));

				//
				products.add(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return products;
	}

	// find product
	// get all product
	public static ArrayList<Product> findProductByKeyWord(int currentPage, int perPage, String keyWord) {
		ArrayList<Product> products = new ArrayList<Product>();

		try {
			PreparedStatement ps = connection
					.prepareStatement("select * from product where name like ? or description like ? limit ?,?");
			ps.setString(1, "%" + keyWord + "%");
			ps.setString(2, "%" + keyWord + "%");
			ps.setInt(3, currentPage);
			ps.setInt(4, perPage);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// data product
				Product product = new Product(rs.getString("name"), rs.getString("description"), rs.getInt("price"),
						rs.getInt("category_id"));
				product.setCreated_at(rs.getTimestamp("created_at"));
				product.setUpdated_at(rs.getTimestamp("updated_at"));
				product.setId(rs.getInt("id"));
				product.setQuantity(rs.getInt("quantity"));
				product.setUrlImage(rs.getString("image"));

				//
				products.add(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return products;
	}

	// get total product
	public static int getTotalProductsByKeyWord(String keyWord) {
		try {
			PreparedStatement ps = connection
					.prepareStatement("select count(*) from product where name like ? or description like ?");
			ps.setString(1, "%" + keyWord + "%");
			ps.setString(2, "%" + keyWord + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				return rs.getInt(1);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return 0;
	}

	// get prouducts by category_id
	public static ArrayList<Product> getAllProductByCategoryId(int category_id, int currentPage, int perPage) {
		ArrayList<Product> products = new ArrayList<Product>();

		try {
			Connection conn = DataBaseUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from product where category_id = ? limit ?,?");
			ps.setInt(1, category_id);
			ps.setInt(2, currentPage);
			ps.setInt(3, perPage);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				// data product
				Product product = new Product(rs.getString("name"), rs.getString("description"), rs.getInt("price"),rs.getInt("category_id"));
				product.setCreated_at(rs.getTimestamp("created_at"));
				product.setUpdated_at(rs.getTimestamp("updated_at"));
				product.setId(rs.getInt("id"));
				product.setQuantity(rs.getInt("quantity"));
				product.setUrlImage(rs.getString("image"));
				//
				products.add(product);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return products;
	}
	
	// get total product
		public static int getTotalProductsByCategoryId(int category_id) {
			try {
				PreparedStatement ps = connection
						.prepareStatement("select count(*) from product where category_id=?");
				ps.setInt(1, category_id);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					return rs.getInt(1);

				}
			} catch (Exception e) {
				// TODO: handle exception
			}

			return 0;
		}

}
