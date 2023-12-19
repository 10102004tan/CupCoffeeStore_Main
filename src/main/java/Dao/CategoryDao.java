package Dao;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import Entiny.Category;

public class CategoryDao {
	
	
	private static Connection connection = DataBaseUtil.getConnection();
	/*Function : 
	 - Add
	 - Delete
	 - update
	 - Select *
	 - find
	  */
	
	//select *
	public static ArrayList<Category> getCategories()
	{
		ArrayList<Category> categories = new ArrayList<Category>();
		
		//prossesing
		
		try {
			PreparedStatement ps = connection.prepareStatement("select * from category");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Category category = new Category(rs.getString("name"));
				category.setCreated_at(rs.getTimestamp("created_at"));
				category.setUpdated_at(rs.getTimestamp("updated_at"));
				category.setId(rs.getInt("id"));
				
				//output
				categories.add(category);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//output
		return categories;
	}
	
	
	//Add
	public static boolean addCategory(Category category)
	{
		
		
		try {
			PreparedStatement ps = connection.prepareStatement("insert into category (name) values(?)");
			ps.setString(1,category.getName());
			
			return ps.executeUpdate() > 0;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}
	
	//delete
	public static boolean deleteCategory(int id)
	{
		
		
		try {
			PreparedStatement ps = connection.prepareStatement("delete from category where id = ?");
			ps.setInt(1,id);
			
			return ps.executeUpdate() > 0;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}
	
	//update
	public static boolean updateCategory(Category category)
	{
		
		
		try {
			PreparedStatement ps = connection.prepareStatement("update category set name=? , updated_at=? where id=?");
			
			//set data 
			ps.setString(1,category.getName());
			Timestamp newTimestamp = new Timestamp(System.currentTimeMillis());
			ps.setTimestamp(2, newTimestamp);
			ps.setInt(3, category.getId());
			
			return ps.executeUpdate() > 0;

			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}
	
	//find product by id
	public static Category getCategoryById(int id)
	{
		
		
		//prossesing
		
		try {
			PreparedStatement ps = connection.prepareStatement("select * from category where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Category category = new Category(rs.getString("name"));
				category.setCreated_at(rs.getTimestamp("created_at"));
				category.setUpdated_at(rs.getTimestamp("updated_at"));
				category.setId(rs.getInt("id"));
				
				//output
				return category;
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//output
		return null;
	}
	
	//find category by name
	
	public static ArrayList<Category> getCategoryByName(String name)
	{
		
		ArrayList<Category> categories = new ArrayList<Category>();
		//prossesing
		
		try {
			PreparedStatement ps = connection.prepareStatement("select * from category where name like ?");
			ps.setString(1,"%" +  name.trim() + "%");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Category category = new Category(rs.getString("name"));
				category.setCreated_at(rs.getTimestamp("created_at"));
				category.setUpdated_at(rs.getTimestamp("updated_at"));
				category.setId(rs.getInt("id"));
				
				//output
				categories.add(category);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//output
		return categories;
	}
	
	

}
