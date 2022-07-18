package library;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseConnector {
	private static final String url = "jdbc:mysql://localhost:3306/";
	private static final String user = "root";
	private static final String password = "";
	private static final String dbName = "library";
	
	private static String getFileContent(String path){
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(path));
			return new String(encoded, StandardCharsets.UTF_8);
		}
		catch(Exception e) {
			System.out.println("Could not read content from file: " + path);
		}
		return null;
    }
	
	private static Connection getConnection(String dbName) {
		String URL = url;
		if (dbName != null) URL += dbName;
		Connection con = null;
		try{ 
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, user, password);
		}
		catch(Exception e) {
			System.out.println("Could not establish connection with DB!");
			System.out.println(e.getMessage());
		}
		return con;
	}
	
	private static void closeConnection(Connection con) {
		try {
			if (con != null && !con.isClosed()) con.close();
		}
		catch(Exception e) {
			System.out.println("Something went wrong while attempting to close the connection");
			System.out.println(e.getMessage());
		}
	}
	
	public static boolean existsDatabase() {
		boolean found = false;
		Connection con = getConnection(null);
		if (con != null) {
			try {
				ResultSet rs = con.getMetaData().getCatalogs();
				while (rs.next()) {
					String catalog = rs.getString(1);
					if (catalog.equals(dbName)) {
						found = true; break;
					}
				}
			}
			catch(Exception e) {
				System.out.println("Could not receive metadata from DBMS");
				System.out.println(e.getMessage());
			}
			finally {
				closeConnection(con);
			}
		}
		
		return found;
	}
	
	public static boolean createDatabaseTables() {
		boolean created = false;
		
		Connection con = getConnection(null);
		if (con != null) {
			try {
				String stmt = "create database " + dbName + ";";
				Statement st = con.createStatement();
				st.execute(stmt); created = true;
			}
			catch(Exception e) {
				System.out.println("Something went wrong while creating the database");
				System.out.println(e.getMessage());
			}
			finally {
				closeConnection(con);
			}
		}
		if (created) return createTables();
		
		return created;
	}
	
	private static boolean createTables() {
		boolean created = false;
		
		Connection con = getConnection(dbName);
		if (con != null) {
			try {
				con.setAutoCommit(false);
				String stmt = getFileContent("sql/member.sql");
				Statement st = con.createStatement();
				st.execute(stmt);
				stmt = getFileContent("sql/lendingbook.sql");
				st.execute(stmt);
				stmt = getFileContent("sql/sellingbook.sql");
				st.execute(stmt);
				stmt = getFileContent("sql/lendedBy.sql");
				st.execute(stmt);
				stmt = getFileContent("sql/buys.sql");
				st.execute(stmt);
				
				con.commit();
				created = true;
			}
			catch(Exception e) {
				System.out.println("Something went wrong while creating the tables");
				System.out.println(e.getMessage());
				rollBack(con);
			}
			finally {
				closeConnection(con);
			}
		}
		
		return created;
	}
	
	public static boolean insertMember(LibraryMember lm) {
		boolean created = false;
		Connection con = getConnection(dbName);
		if (con != null) {
			try {
				String stmt = getFileContent("sql/insert_member.sql");
				PreparedStatement st = con.prepareStatement(stmt);
				st.setString(1,lm.getMemberId());
				st.setString(2,lm.getId());
				st.setString(3,lm.getName());
				st.execute(); created = true;
			}
			catch(Exception e) {
				System.out.println("Something went wrong while inserting a library member");
				System.out.println(e.getMessage());
			}
			finally {
				closeConnection(con);
			}
		}
		
		return created;
	}
	
	public static boolean insertLendingBook(LendingBook lb) {
		boolean created = false;
		Connection con = getConnection(dbName);
		if (con != null) {
			try {
				String stmt = getFileContent("sql/insert_lending_book.sql");
				PreparedStatement st = con.prepareStatement(stmt);
				st.setString(1,lb.getIsbn());
				st.setString(2,lb.getTitle());
				st.setInt(3,lb.getCopyNumber());
				st.execute(); created = true;
			}
			catch(Exception e) {
				System.out.println("Something went wrong while inserting a lending book");
				System.out.println(e.getMessage());
			}
			finally {
				closeConnection(con);
			}
		}
		
		return created;
	}
	
	public static boolean insertSellingBook(SellingBook sb) {
		boolean created = false;
		Connection con = getConnection(dbName);
		if (con != null) {
			try {
				String stmt = getFileContent("sql/insert_selling_book.sql");
				PreparedStatement st = con.prepareStatement(stmt);
				st.setString(1,sb.getIsbn());
				st.setString(2,sb.getTitle());
				st.setInt(3,sb.getCopyNumber());
				st.execute();
				created = true;
			}
			catch(Exception e) {
				System.out.println("Something went wrong while inserting a selling book");
				System.out.println(e.getMessage());
			}
			finally {
				closeConnection(con);
			}
		}
		
		return created;
	}
	
	private static void rollBack(Connection con) {
		try {
			con.rollback();
		}
		catch(Exception e) {
			System.out.println("Something went wrong while trying to rollback the connection");
		}
	}
	
	private static String getCurrentDate() {
		Date d = new Date();
		SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dmyFormat.format(d);
	}
	
	public static boolean sellBook(String isbn, String memberId) {
		boolean created = false;
		Connection con = getConnection(dbName);
		if (con != null) {
			try {
				con.setAutoCommit(false);
				String stmt = getFileContent("sql/insert_buys.sql");
				PreparedStatement st = con.prepareStatement(stmt);
				st.setString(1,isbn);
				st.setString(2,memberId);
				st.setString(3,getCurrentDate());
				st.execute();
				
				stmt = getFileContent("sql/increase_items_sold.sql");
				st = con.prepareStatement(stmt);
				st.setString(1,isbn);
				st.execute();
				
				con.commit();
				created = true;
			}
			catch(Exception e) {
				System.out.println("Something went wrong while inserting a book selling event");
				System.out.println(e.getMessage());
				rollBack(con);
			}
			finally {
				closeConnection(con);
			}
		}
		
		return created;
	}
	
	public static boolean loanBook(String isbn, String memberId) {
		boolean created = false;
		Connection con = getConnection(dbName);
		if (con != null) {
			try {
				con.setAutoCommit(false);
				String stmt = getFileContent("sql/insert_lendedby.sql");
				PreparedStatement st = con.prepareStatement(stmt);
				st.setString(1,isbn);
				st.setString(2,memberId);
				st.setString(3,getCurrentDate());
				st.execute();
				
				stmt = getFileContent("sql/increase_itemsLended.sql");
				st = con.prepareStatement(stmt);
				st.setString(1,isbn);
				st.execute();
				
				con.commit();
				created = true;
			}
			catch(Exception e) {
				System.out.println("Something went wrong while inserting a book lending event");
				System.out.println(e.getMessage());
				rollBack(con);
			}
			finally {
				closeConnection(con);
			}
		}
		
		return created;
	}
	
	public static boolean returnBook(String isbn, String memberId) {
		boolean created = false;
		Connection con = getConnection(dbName);
		if (con != null) {
			try {
				con.setAutoCommit(false);
				String stmt = getFileContent("sql/delete_lendedby.sql");
				PreparedStatement st = con.prepareStatement(stmt);
				st.setString(1,isbn);
				st.setString(2,memberId);
				st.execute();
				
				//stmt = getFileContent("sql/decrease_itemsLended.sql");
				stmt = "update lendingBook set itemsLended=itemsLended-1 where isbn=?;";
				st = con.prepareStatement(stmt);
				st.setString(1,isbn);
				st.execute();
				
				con.commit();
				created = true;
			}
			catch(Exception e) {
				System.out.println("Something went wrong while anticipating a return book event");
				System.out.println(e.getMessage());
				rollBack(con);
			}
			finally {
				closeConnection(con);
			}
		}
		
		return created;
	}
	
	public static void printLibraryMembers() {
		Connection con = getConnection(dbName);
		if (con != null) {
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from member");
				while (rs.next()) {
					String memberId = rs.getString(1); String id = rs.getString(2);
					String name = rs.getString(3);
					LibraryMember lm = new LibraryMember(id, name, memberId);
					System.out.println(lm);
				}
			}
			catch(Exception e) {
				System.out.println("Something went wrong while accessing library members");
				System.out.println(e.getMessage());
				rollBack(con);
			}
			finally {
				closeConnection(con);
			}
		}
	}
	
	public static void printLendingBooks() {
		Connection con = getConnection(dbName);
		if (con != null) {
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from lendingBook");
				while (rs.next()) {
					String isbn = rs.getString(1); String title = rs.getString(2);
					int copyNum = rs.getInt(3); int itemsLended = rs.getInt(4);
					LendingBook lb = new LendingBook(isbn,title,copyNum);
					lb.setItemsLended(itemsLended);
					System.out.println(lb);
				}
			}
			catch(Exception e) {
				System.out.println("Something went wrong while accessing lending books");
				System.out.println(e.getMessage());
				rollBack(con);
			}
			finally {
				closeConnection(con);
			}
		}
	}
	
	public static void printSellingBooks() {
		Connection con = getConnection(dbName);
		if (con != null) {
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from sellingBook");
				while (rs.next()) {
					String isbn = rs.getString(1);
					String title = rs.getString(2);
					int copyNum = rs.getInt(3);
					int itemsSold = rs.getInt(4);
					SellingBook sb = new SellingBook(isbn,title,copyNum);
					sb.setItemsSold(itemsSold);
					System.out.println(sb);
				}
			}
			catch(Exception e) {
				System.out.println("Something went wrong while accessing selling books");
				System.out.println(e.getMessage());
				rollBack(con);
			}
			finally {
				closeConnection(con);
			}
		}
	}
}
