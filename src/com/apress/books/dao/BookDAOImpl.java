/**
 * 
 */
package com.apress.books.dao;

/**
 * @author diegomora
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.apress.books.model.Author;
import com.apress.books.model.Book;
import com.apress.books.model.Category;

public class BookDAOImpl implements BookDAO{


	/**
	 * @param args
	 */
	/**Driver For MySql**/
	static {
		
		try {
			/**Call to .JAR driver in the project**/
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		
		}catch (ClassNotFoundException ex) {
		
			
		}
	}

	/**Connection Settings**/
	private Connection getConnection() throws SQLException {
		/** Details of Data Base AWS **/
		return DriverManager.getConnection("jdbc:mysql://bookstore.cemhe8g33h5g.us-east-2.rds.amazonaws.com:3306/books?autoReconnect=true&useSSL=false","admin","1T4LC0Ladmin$$$$");
	
	}
	
	/** Close Connection**/
	private void closeConnection(Connection connection) {
	
		if (connection == null) {
			
			return;
		}
		
		try {
			connection.close();	
		
		}catch(SQLException ex){
			
			
		}
	}
	
	/*** Search Books***/
	public List<Book> findAllBooks() {
		
		List<Book> result = new ArrayList<>();
		List<Author> authorList = new ArrayList<>();
		
		String sql = "select * from BOOK inner join AUTHOR on BOOK.ID = AUTHOR.BOOK_ID";
		
		Connection connection = null;
		try {
			
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				
				Book book = new Book();
				Author author = new Author();
				book.setId(resultSet.getLong("id"));
				book.setBookTitle(resultSet.getString("book_title"));
				book.setCategoryId(resultSet.getLong("category_id"));
				author.setBookId(resultSet.getLong("book_Id"));
				author.setFirstName(resultSet.getString("first_name"));
				author.setLastName(resultSet.getString("last_name"));
				authorList.add(author);
				book.setAuthors(authorList);
				book.setPublisherName(resultSet.getString("publisher"));
				result.add(book);
			}
			
		}catch(SQLException ex) {
			
			ex.printStackTrace();
		}finally {
			closeConnection(connection);
		}
		
		return result;
	}
	
	public List<Book> searchBooksByKeyword(String keyWord) {
		List<Book> result = new ArrayList<>();
		List<Author> authorList = new ArrayList<>();
		
		String sql = "select * from BOOK inner join AUTHOR on BOOK.ID = AUTHOR.BOOK_ID"
		+ " where BOOK_TITLE like '%"
		+ keyWord.trim()
		+ "%'"
		+ " or FIRST_NAME like '%"
		+ keyWord.trim()
		+ "%'"
		+ " or LAST_NAME like '%" + keyWord.trim() + "%'";
	
		Connection connection = null;
		try {
		
		connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			Book book = new Book();
			Author author = new Author();
			book.setId(resultSet.getLong("id"));
			book.setBookTitle(resultSet.getString("book_title"));
			book.setPublisherName(resultSet.getString("publisher"));
			author.setFirstName(resultSet.getString("first_name"));
			author.setLastName(resultSet.getString("last_name"));
			author.setBookId(resultSet.getLong("book_id"));
			authorList.add(author);
			book.setAuthors(authorList);
			result.add(book);
		}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		
		return result;
		}
		
		public List<Category> findAllCategories() {
			
			List<Category> result = new ArrayList<>();
			String sql = "select * from category";
			
			Connection connection = null;
			
			try {
			
				connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery();
			
			
			while (resultSet.next()) {
				
				Category category = new Category();
				category.setId(resultSet.getLong("id"));
				category.setCategoryDescription(resultSet.getString("category_description"));
				result.add(category);
			}
			
			} catch (SQLException ex) {
			
				ex.printStackTrace();
			
			} finally {
			
				closeConnection(connection);
			}
			
			return result;
			
			}
			
			public void insert(Book book) {
			
			}
			
			public void update(Book book) {
			
			}
			
			public void delete(Long bookId) {
			
				
			}
			
			
		}
			
	

