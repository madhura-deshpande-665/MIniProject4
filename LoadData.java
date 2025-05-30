import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LoadData {

	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
		
		Properties pros = new Properties();
		try (InputStream input = new FileInputStream("src/db.properties"))
		{
			pros.load(input);
		
           String url = pros.getProperty("db.url");
		   String user = pros.getProperty("db.user");
		   String password = pros.getProperty("db.password");
		   
		     // Load MySQL driver (optional for newer versions)
				Class.forName("com.mysql.cj.jdbc.Driver");
			
			  // Create connection
			  Connection con = DriverManager.getConnection(url, user, password);
			
			  //creating table Male 
			 // createTableMale(con);
			  
			//creating table Female
			  //createTableFemale(con);
			
			  //Insert Male data in the table 
			//InsertMaleData.insertMaleData(con);
			
			  //Insert Female data in the table 
		    //InsertFemaleData.insertFemaleData(con);
			
			  con.close();

	      }
		catch(Exception e)
		{
			System.out.println(e);
		}

     }
	
	
	public static void createTableMale(Connection connection)
	{
		
		String createTable = " CREATE TABLE male(age int primary key,tenure_2 decimal(10,3), tenure_3 decimal(10,3), tenure_4 decimal(10,3))";
		
		try (Statement st = connection.createStatement()) {
			st.execute(createTable);
			System.out.println("Table Customers created successfully....!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
	}
	
	public static void createTableFemale(Connection connection)
	{
		
		String createTable = " CREATE TABLE Female(age int primary key,tenure_2 decimal(10,3), tenure_3 decimal(10,3), tenure_4 decimal(10,3))";
		
		try (Statement st = connection.createStatement()) {
			st.execute(createTable);
			System.out.println("Table Customers created successfully....!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
	}
	
	
	
	
	
	
	
	
	
	
	
}