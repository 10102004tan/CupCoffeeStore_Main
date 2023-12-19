package Dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import Entiny.Account;
import at.favre.lib.crypto.bcrypt.BCrypt;


public class AccountDao {
	
	
	private static Connection connection = DataBaseUtil.getConnection();
	
	/*
	 - select * 
	 - add
	 - update
	 - delete
	 - find by id
	 * */
	
	//select *
	public static ArrayList<Account> getAccounts(int currentPage,int perPage)
	{
		
		//khai bao 
		ArrayList<Account> accounts = new ArrayList<Account>();
		
		try {
			PreparedStatement ps = connection.prepareStatement("select * from account limit ?,?");
			ps.setInt(1, currentPage);
			ps.setInt(2, perPage);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Account account = new Account(rs.getString("name"), rs.getString("email"), rs.getString("password"));
				account.setId(rs.getInt("id"));
				account.setPhone(rs.getString("phone"));
				account.setRole(rs.getInt("role"));
				account.setCreated_at(rs.getTimestamp("created_at"));
				account.setUpdated_at(rs.getTimestamp("updated_at"));
				//
				accounts.add(account);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return accounts;
	}
	
	//getTotalAccounts
	public static int getTotalAccounts()
	{
		try {
			PreparedStatement ps = connection.prepareStatement("select count(*) from account");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				return rs.getInt(1);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return 0;
	}
	
	//add account
	public static boolean addAccount(Account account)
	{
		boolean status = false;
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO `account`(`name`, `email`, `password`, `phone`, `role`) VALUES (?,?,?,?,?)");
			ps.setString(1, account.getName());
			ps.setString(2, account.getEmail());
			ps.setString(3, account.getPassword());
			ps.setString(4, account.getPhone());
			ps.setInt(5,account.getRole());
			status = ps.executeUpdate() > 0;
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return status;
	}
	
	//get email account 
	public static ArrayList<String> getAllEmails()
	{
		ArrayList<String> emails = new ArrayList();
		try {
			PreparedStatement ps = connection.prepareStatement("select email from account");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				emails.add(rs.getString("email"));
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return emails;
	}
	
	//update account
	public static boolean updateAccount(Account account)
	{
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE `account` SET name=?,email=?,password=?,phone=?,status=?,role=?,updated_at=? WHERE id=?");
			ps.setString(1, account.getName());
			ps.setString(2, account.getEmail());
			ps.setString(3, account.getPassword());
			ps.setString(4, account.getPhone());
			ps.setInt(5,account.getStatus());
			ps.setInt(6, account.getRole());
			Timestamp newTimestamp = new Timestamp(System.currentTimeMillis());
			ps.setTimestamp(7, newTimestamp);
			ps.setInt(8, account.getId());
			
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}
	
	
	//delete account
	
	public static boolean destroyAccount(int id) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM `account` WHERE id=?");
			ps.setInt(1, id);
			
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}
	
	
	//getAccount by id
	public static Account getAccountById(int id)
	{
		
		try {
			PreparedStatement ps = connection.prepareStatement("select * from account where id = ?");
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Account account = new Account(rs.getString("name"), rs.getString("email"), rs.getString("password"));
				account.setId(rs.getInt("id"));
				account.setPhone((rs.getString("phone")) == null ?"unknown" :rs.getString("phone"));
				account.setRole(rs.getInt("role"));
				account.setStatus(rs.getInt("status"));
				account.setCreated_at(rs.getTimestamp("created_at"));
				account.setUpdated_at(rs.getTimestamp("updated_at"));
				
				return account;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	
	//find account by keyword
	// 4.Find
		public static ArrayList<Account> findAccount(String keyword,int currentPage,int perPage) {
			ArrayList<Account> accounts = new ArrayList<Account>();

			try {
				Connection conn = DataBaseUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement("select * from account where email like ? or name like ? limit ?,?");
				ps.setString(1, "%" + keyword + "%");
				ps.setString(2, "%" + keyword + "%");
				ps.setInt(3, currentPage);
				ps.setInt(4, perPage);

				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					
					Account account = new Account(rs.getString("name"), rs.getString("email"), rs.getString("password"));
					account.setId(rs.getInt("id"));
					account.setPhone((rs.getString("phone")) == null ?"unknown" :rs.getString("phone"));
					account.setRole(rs.getInt("role"));
					account.setStatus(rs.getInt("status"));
					account.setCreated_at(rs.getTimestamp("created_at"));
					account.setUpdated_at(rs.getTimestamp("updated_at"));
					accounts.add(account);
				}

				return accounts;
			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}

		//getTotalAccounts
		public static int getTotalAccountsByKeyWord(String keyword)
		{
			try {
				PreparedStatement ps = connection.prepareStatement("select count(*) from account where email like ? or name like ?");
				ps.setString(1, "%" + keyword + "%");
				ps.setString(2, "%" + keyword + "%");
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					return rs.getInt(1);
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
			return 0;
		}
		
		
		//checklogin
		public static boolean checkLogin(String email, String pw)
		{
			try {
				PreparedStatement ps = connection.prepareStatement("select email, password from account where email = ?");
				ps.setString(1, email);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					BCrypt.Result result = BCrypt.verifyer().verify(pw.toCharArray(), rs.getString("password"));
					return result.verified;
				}
				
				//BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return false;
		}
		
		
		//get account by email
		public static Account getAccountByEmail(String email)
		{
			
			try {
				PreparedStatement ps = connection.prepareStatement("select * from account where email = ?");
				ps.setString(1, email);
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					Account account = new Account(rs.getString("name"), rs.getString("email"), rs.getString("password"));
					account.setId(rs.getInt("id"));
					account.setPhone((rs.getString("phone")) == null ?"unknown" :rs.getString("phone"));
					account.setRole(rs.getInt("role"));
					account.setStatus(rs.getInt("status"));
					account.setCreated_at(rs.getTimestamp("created_at"));
					account.setUpdated_at(rs.getTimestamp("updated_at"));
					
					return account;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return null;
		}
		
		
		//add account
		public static boolean register(Account account)
		{
			boolean status = false;
			try {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO `account`(`name`, `email`, `password`, `phone`) VALUES (?,?,?,?)");
				ps.setString(1, account.getName());
				ps.setString(2, account.getEmail());
				ps.setString(3, account.getPassword());
				ps.setString(4, account.getPhone());
				status = ps.executeUpdate() > 0;
			
			} catch (Exception e) {
				// TODO: handle exception
			}
			return status;
		}
		
		
		//get prouducts by category_id
		public static ArrayList<Account> getAllProductByCategoryId(int category_id,int currentPage,int perPage) {
			ArrayList<Account> accounts = new ArrayList<Account>();

			try {
				Connection conn = DataBaseUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement("select * from account where category_id = ? limit ?,?");
				ps.setInt(1,category_id);
				ps.setInt(2, currentPage);
				ps.setInt(3, perPage);

				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					
					Account account = new Account(rs.getString("name"), rs.getString("email"), rs.getString("password"));
					account.setId(rs.getInt("id"));
					account.setPhone((rs.getString("phone")) == null ?"unknown" :rs.getString("phone"));
					account.setRole(rs.getInt("role"));
					account.setStatus(rs.getInt("status"));
					account.setCreated_at(rs.getTimestamp("created_at"));
					account.setUpdated_at(rs.getTimestamp("updated_at"));
					accounts.add(account);
				}

				return accounts;
			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}

}
