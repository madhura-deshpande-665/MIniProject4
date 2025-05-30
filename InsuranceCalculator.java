import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InsuranceCalculator {

	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter your Name :");
		String name = scanner.nextLine();
		
		System.out.print("Enter your Date of Birth (YYYY-MM-DD): ");
		LocalDate dob = LocalDate.parse(scanner.nextLine());

		System.out.print("Enter Loan Amount: ");
		double loanAmount = scanner.nextDouble();

		System.out.print("Enter Loan Tenure (2 to 4): ");
		int tenure = scanner.nextInt();
		
		System.out.println("Enter your Gender : ");
		String gender = scanner.next();
		
       
			Properties pros = new Properties();
			try (InputStream input = new FileInputStream("src/db.properties")) {
				pros.load(input);
			
	            String url = pros.getProperty("db.url");
			    String user = pros.getProperty("db.user");
			    String password = pros.getProperty("db.password");

                // Load MySQL driver (optional for newer versions)
				Class.forName("com.mysql.cj.jdbc.Driver");
				

				// Create connection
				Connection conn = DriverManager.getConnection(url, user, password);

				CallableStatement stmt = conn.prepareCall("{CALL get_multiplier_male(?, ?, ?,?)}");
	            stmt.setDate(1, Date.valueOf(dob));  // LocalDate to java.sql.Date
	            stmt.setInt(2, tenure);
	            stmt.setString(3, gender) ;         
	            stmt.registerOutParameter(4, Types.DECIMAL);
	            
				
	            stmt.execute();

	            double multiplier = stmt.getDouble(4);
	            
	            if (multiplier == 0.0) {
	                System.out.println("No multiplier found for the given age and tenure.");
	            } else {
	                double insurancePremium = (loanAmount / 1000.0) * multiplier;
	                System.out.println("Dear " + name + ", your insurance premium is: ₹" + insurancePremium);
	            }

				conn.close();
				scanner.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
         }
       	          
 			
	
	   
			
			
			
		
	}

