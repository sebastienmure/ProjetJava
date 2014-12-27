import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class ArticleHelper
{
	public static HashSet<Article> getAll()
	{
		//SupaLogga.log("public static HashSet<Article> getAll()");
		String label = "";
		String ref = "";
		String image = "";
		int id	= 0;
		float prix = 0;
		String query = "select * from Article";
		ResultSet rs = OracleJDBC.query(query);
		HashSet<Article> la = new HashSet<Article>();
		
		if(rs == null)
		{
			return null;
		}
		else
		{
			try
			{
				while(rs.next())
				{
					//Retrieve by column name
					id	= rs.getInt("AR_ID");
					label = rs.getString("AR_LABEL");
					ref = rs.getString("AR_REF");
					image = rs.getString("AR_IMAGE");
					prix = rs.getFloat("AR_PRIX");
					
					la.add( new Article(id, prix, ref, label, image, true) );
				}
				return la;
			}
			catch (SQLException se)
			{
				System.out.print("SQLException...");
			    se.printStackTrace();
			    return null;
			}
		}
	}
	
	public static Article getFirst()
	{
		String label = "";
		String ref = "";
		String image = "";
		int id	= 0;
		float prix = 0;
		String query = "select * from Article where rownum = 1";
		ResultSet rs = OracleJDBC.query(query);
		
		if(rs == null)
			return null;
		else
		{
			try
			{
				rs.next();
				//Retrieve by column name
				id	= rs.getInt("AR_ID");
				label = rs.getString("AR_LABEL");
				ref = rs.getString("AR_REF");
				image = rs.getString("AR_IMAGE");
				prix = rs.getFloat("AR_PRIX");
				
				return new Article(id, prix, ref, label, image, true);
			}
			catch(SQLException se)
			{
				System.out.print("SQLException...");
			    se.printStackTrace();
			    return null;
			}
		}
	}
	
	public static Article getOneById(int id)
	{
		String label = "";
		String ref = "";
		String image = "";
		float prix = 0;
		String query = "select * from Article a where a.AR_ID = " + Integer.toString(id);
		ResultSet rs = OracleJDBC.query(query);
		
		if(rs == null)
			return null;
		else
		{
			try
			{
				rs.next();
				//Retrieve by column name
				id	= rs.getInt("AR_ID");
				label = rs.getString("AR_LABEL");
				ref = rs.getString("AR_REF");
				image = rs.getString("AR_IMAGE");
				prix = rs.getFloat("AR_PRIX");
				
				return new Article(id, prix, ref, label, image, true);
			}
			catch(SQLException se)
			{
				System.out.print("SQLException...");
			    se.printStackTrace();
			    return null;
			}
		}
	}
	
	public static void commitChange(Article a)
	{
		if(a == null)
			return;
		
		ResultSet res = null;
		if(a.isInBase())
			res = OracleJDBC.query(" UPDATE Article "
					+ " SET AR_REF = '" + a.getRef()
					+ "', AR_LABEL = '" + a.getLabel()
					+ "', AR_PRIX = " + a.getPrix()
					+ ", AR_IMAGE = '" + a.getImage()
					+ "' WHERE AR_ID = " + a.getId() );
		
		if(res != null)
		{
			try
			{
				res.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

}
