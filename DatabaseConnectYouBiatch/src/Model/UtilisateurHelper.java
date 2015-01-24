package Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashSet;

import Controller.OracleJDBC;

public abstract class UtilisateurHelper
{
	public static HashSet<Utilisateur> getAll()
	{
		//SupaLogga.log("public static HashSet<Utilisateur> getAll()");
		String nom = "";
		String prenom = "";
		String pass = "";
		String hash = "";
		int id	= 0;
		String cp = "";
		boolean isAdmin = false;
		
		String query = "select * from Utilisateur";
		ResultSet rs = OracleJDBC.query(query);
		HashSet<Utilisateur> la = new HashSet<Utilisateur>();
		
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
					id	= rs.getInt("UT_ID");
					nom = rs.getString("UT_NOM");
					prenom = rs.getString("UT_PRENOM");
					pass = rs.getString("UT_PASS");
					cp = rs.getString("UT_CP");
					hash = rs.getString("UT_HASH");
					isAdmin = rs.getBoolean("UT_ISADMIN");
					
					la.add( new Utilisateur(id, nom, prenom, cp, pass, hash, isAdmin, true) );
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
	
	public static Utilisateur getFirst()
	{
		String nom = "";
		String prenom = "";
		String pass = "";
		String hash = "";
		int id	= 0;
		String cp = "";
		boolean isAdmin = false;
		
		String query = "select * from Utilisateur where rownum = 1";
		ResultSet rs = OracleJDBC.query(query);
		
		if(rs == null)
			return null;
		else
		{
			try
			{
				rs.next();
				//Retrieve by column name
				id	= rs.getInt("UT_ID");
				nom = rs.getString("UT_NOM");
				prenom = rs.getString("UT_PRENOM");
				pass = rs.getString("UT_PASS");
				cp = rs.getString("UT_CP");
				hash = rs.getString("UT_HASH");
				isAdmin = rs.getBoolean("UT_ISADMIN");
				
				return new Utilisateur(id, nom, prenom, cp, pass, hash, isAdmin, true);
			}
			catch(SQLException se)
			{
				System.out.print("SQLException...");
			    se.printStackTrace();
			    return null;
			}
		}
	}
	
	public static Utilisateur getOneById(int anId)
	{
		String nom = "";
		String prenom = "";
		String pass = "";
		String hash = "";
		int id	= 0;
		String cp = "";
		boolean isAdmin = false;
		
		String query = "select * from Utilisateur a where a.UT_ID = " + Integer.toString(anId);
		ResultSet rs = OracleJDBC.query(query);
		
		if(rs == null)
			return null;
		else
		{
			try
			{
				rs.next();
				//Retrieve by column name
				id	= rs.getInt("UT_ID");
				nom = rs.getString("UT_NOM");
				prenom = rs.getString("UT_PRENOM");
				pass = rs.getString("UT_PASS");
				cp = rs.getString("UT_CP");
				hash = rs.getString("UT_HASH");
				isAdmin = rs.getBoolean("UT_ISADMIN");
				
				return new Utilisateur(id, nom, prenom, cp, pass, hash, isAdmin, true);
			}
			catch(SQLException se)
			{
				System.out.print("SQLException...");
			    se.printStackTrace();
			    return null;
			}
		}
	}
	
	public static void commitChange(Utilisateur a)
	{
		if(a == null)
			return;
		
		ResultSet res = null;
		if(a.isInBase())
			res = OracleJDBC.query(" UPDATE Utilisateur "
					+ " SET UT_PASS = '" + a.getPass()
					+ "', UT_NOM = '" + a.getNom()
					+ "', UT_PRENOM = '" + a.getPrenom()
					+ "', UT_HASH = '" + a.getHash()
					+ "', UT_CP = '" + a.getCp()
					+ "' WHERE UT_ID = " + a.getId() );
		
		if(res != null)
		{
			System.out.println("updated !");
			try
			{
				res.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		else
			System.out.println("nothing done");
	}
	
	public static void main(String[] argv)
	{
		System.out.println("---- UtilisateurHelper::main() ----\n");
		
		Utilisateur u = UtilisateurHelper.getFirst();
		System.out.println(u.toString());
		u.setPass("123Soleil");
		u.setHash("321Lune");
		u.setCp("2A");
		UtilisateurHelper.commitChange(u);
		System.out.println(u.toString());		
		
		System.out.println("\n-----------------------------------");
	}
}
