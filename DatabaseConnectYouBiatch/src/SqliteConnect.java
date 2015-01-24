//STEP 1. Import required packages
import java.sql.*;

public class SqliteConnect
{
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";	// org.sqlite.JDBC
	static final String DB_URL = "jdbc:oracle:thin:@192.168.83.128:1521:JoseSID"; 	// jdbc:sqlite:test.db 
	
	//	Database credentials
	static final String USER = "SYSTEM";  // username
	static final String PASS = "system123lol"; // password
	
	public static void main(String[] args)
	{
    	Connection conn = null;
		Statement stmt = null;
		try
		{
			//STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL); //,USER,PASS);

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			
			
			/*
			sql = "SELECT * FROM COMPANY";
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next())
			{
				//Retrieve by column name
				int id	= rs.getInt("ID");
				int name = rs.getInt("NAME");
				String age = rs.getString("AGE");
				String address = rs.getString("ADDRESS");
				int salary = rs.getInt("SALARY");

				//Display values
				System.out.print("ID: " + id);
				System.out.print(", Name: " + name);
				System.out.print(", Age: " + age);
				System.out.println(", Address: " + address);
				System.out.println(", Salary: " + salary);
			}
			*/
			
			
			sql = "SELECT * FROM Article";
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next())
			{
				//Retrieve by column name
				int id	= rs.getInt("AR_ID");
				String label = rs.getString("AR_LABEL");
				float prix = rs.getFloat("AR_PRIX");

				//Display values
				System.out.print("ID: " + id);
				System.out.print(", Label: " + label);
				System.out.print(", Prix: " + prix);
			}
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		}
		catch(SQLException se)
		{
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e)
		{
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally
		{
			//finally block used to close resources
			try
			{
			    if(stmt != null)
				stmt.close();
			}
			catch(SQLException se2)
			{
			    if( se2.getErrorCode() != 1 )
			    {
				System.out.println("An error occured Jim... [" + Integer.toString( se2.getErrorCode() ) + "]");
				se2.printStackTrace();
			    }
			}// nothing we can do
			try
			{
			    if(conn!=null)
				conn.close();
			}
			catch(SQLException se)
			{
			    se.printStackTrace();
			}//end finally try
		}//end try

		System.out.println("Goodbye!");
		
	}//end main 
}//end FirstExample