import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
 
public class OracleJDBC
{
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@192.168.83.128:1521:JoseSID"; 
	
	//	Database credentials
	static final String USER = "SYSTEM";  // username
	static final String PASS = "system123lol"; // password
	
	static Connection connection = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	
	public static Boolean jdbcRegister()
	{
		System.out.println("-------- Oracle JDBC Connection Testing ------\nRegistering...");
		
		try
		{
			Class.forName(JDBC_DRIVER); 
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return false;
 
		}
 
		System.out.println("Oracle JDBC Driver Registered!\n----------------------------------------------");
		return true;
	}
	
	public static Boolean connect()
	{
		System.out.println("Connecting...");
		try
		{
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		}
		catch (SQLException e)
		{
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return false;
		}
 
		if (connection != null)
		{
			System.out.println("You made it, take control your database now!");
			return true;
		}
		else
		{
			System.out.println("Failed to make connection!");
			return false;
		}
		
	}
	
	public static void disconnect()
	{
		try
		{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
			if(connection != null)
				connection.close();
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
			    if(connection!=null)
			    	connection.close();
			}
			catch(SQLException se)
			{
			    se.printStackTrace();
			}//end finally try
		}//end try

		System.out.println("Disconnected!");
	}
	
	public static void main(String[] argv)
	{
		String sqlQuery = "";
		String label = "";
		int id	= 0;
		float prix = 0;
		DefaultTableModel dtm = null;
		
		
		if( !jdbcRegister() )
			return;
		
		if( !connect() )
			return;
				
		sqlQuery = "SELECT * FROM Article";
		try
		{
			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = connection.createStatement();
			
			System.out.print("Querying articles...");
			rs = stmt.executeQuery(sqlQuery);
			dtm = buildTableModel(rs);
			System.out.println(" OK");
	
			
			System.out.print("ID");
			System.out.print("\tLabel");
			System.out.println("\tPrix");
			
			//STEP 5: Extract data from result set
			while(rs.next())
			{
				//Retrieve by column name
				id	= rs.getInt("AR_ID");
				label = rs.getString("AR_LABEL");
				prix = rs.getFloat("AR_PRIX");
	
				//Display values
				System.out.print(id);
				System.out.print("\t" + label);
				System.out.println("\t" + prix + "€");
			}
			
			/* tableau */
			//Fenetre fen = new Fenetre();
	        //fen.setVisible(true);
			
			// It creates and displays the table
		    JTable table = new JTable(dtm);
		    JOptionPane.showMessageDialog(null, new JScrollPane(table));
		    /* ****** */
			
		}
		catch(SQLException se)
		{
			System.out.print("SQLException...");
		    se.printStackTrace();	
		}
		finally
		{
			//STEP 6: Clean-up environment
			disconnect();
		}
	}

	private static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException
	{
	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++)
	    {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next())
	    {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++)
	        {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
    	}

	    return new DefaultTableModel(data, columnNames);
	}
}