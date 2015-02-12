package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.Vector;

import controller.OracleJDBC;
import controller.SupaLogga;

public abstract class ArticleHelper
{
	public static Vector<Article> getAll()
	{
		//SupaLogga.log("public static Vector<Article> getAll()");
		String label = "";
		String ref = "";
		String image = "";
		int id	= 0;
		float prix = 0;
		String query = "select * from Article";
		ResultSet rs = OracleJDBC.query(query);
		Vector<Article> la = new Vector<Article>();
		
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
	
	public static ArticleTableModel toTableModel(Vector<Article> v)
	{
		final int col = v.size();
		int cpt = 0;
		String[][] datas = null;
		ArticleTableModel dt = new ArticleTableModel(v);     	
    	
    	return dt;
	}
	
	public static int getLastId()
	{
		String query = "SELECT AR_ID FROM Article WHERE ROWNUM <=1 ORDER BY AR_ID DESC";
		ResultSet rs = OracleJDBC.query(query);
		int id = -1;
		
		try
		{
			rs.next();
			//Retrieve by column name
			id	= rs.getInt("AR_ID");
		}
		catch(SQLException se)
		{
			SupaLogga.log("SQLException...");
		    se.printStackTrace();
		    return -1;
		}
		
		return id;
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
		String msg = "";
		if(a == null)
			return;
		
		ResultSet res = null;
		if(a.isInBase())
		{
			res = OracleJDBC.query(" UPDATE Article "
					+ " SET AR_REF = '" + a.getRef()
					+ "', AR_LABEL = '" + a.getLabel()
					+ "', AR_PRIX = " + a.getPrix()
					+ ", AR_IMAGE = '" + a.getImage()
					+ "' WHERE AR_ID = " + a.getId() );
			msg = "Updated !";
		}
		else
		{
			res = OracleJDBC.query("INSERT INTO Article values ("
					+ a.getId() 
					+ ", '" + a.getRef()
					+ "', '" + a.getLabel()
					+ "', " + a.getPrix()
					+ ", '" + a.getImage() + "')");
			msg = "Created !";
		}
		
		if(res != null)
		{
			System.out.println(msg);
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
