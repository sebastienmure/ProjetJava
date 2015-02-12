package model;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Vector;

import controller.OracleJDBC;
import controller.SupaLogga;
import controller.AESencrp;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.table.DefaultTableModel;

public abstract class UtilisateurHelper
{
	public static Vector<Utilisateur> getAll()
	{
		//SupaLogga.log("public static Vector<Utilisateur> getAll()");
		String nom = "";
		String prenom = "";
		String pass = "";
		String hash = "";
		int id	= 0;
		String cp = "";
		boolean isAdmin = false;
		
		String query = "select * from Utilisateur";
		ResultSet rs = OracleJDBC.query(query);
		Vector<Utilisateur> la = new Vector<Utilisateur>();
		
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
	
	public static UtilisateurTableModel toTableModel(Vector<Utilisateur> v)
	{
		final int col = v.size();
		int cpt = 0;
		String[][] datas = null;
		UtilisateurTableModel dt = new UtilisateurTableModel(v);     	
    	
    	return dt;
	}
	
	public static int getLastId()
	{
		String query = "SELECT UT_ID FROM Utilisateur WHERE ROWNUM <=1 ORDER BY UT_ID DESC";
		ResultSet rs = OracleJDBC.query(query);
		int id = -1;
		
		try
		{
			rs.next();
			//Retrieve by column name
			id	= rs.getInt("UT_ID");
		}
		catch(SQLException se)
		{
			SupaLogga.log("SQLException...");
		    se.printStackTrace();
		    return -1;
		}
		
		return id;
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
	
	public static Utilisateur getOneByUsername(String username)
	{
		if(username.length() == 0)
			return null;
		
		String[] uname = username.split("-");
		if(uname.length != 2)
		{
			SupaLogga.log("Le nom d''utilisateur doit être est nom.prenom");
			return null;
		}
		
		String nom = uname[0];
		String prenom = uname[1];
		String pass = "";
		String hash = "";
		int id	= -1;
		String cp = "";
		boolean isAdmin = false;
		
		String query = "select * from Utilisateur a where a.UT_NOM = '" + nom + "' and a.UT_PRENOM = '" + prenom + "'";
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
	
	// Attention avec la gestion des MdP
	// Des Risques lors de l'update
	public static void commitChange(Utilisateur a)
	{
		short admin = 0;
		String msg = "";
		if(a == null)
			return; // Mettre un message d'erreur
		
		if(a.getIsAdmin())
			admin = 1;
		
		ResultSet res = null;
		if(a.isInBase())
		{
			res = OracleJDBC.query(" UPDATE Utilisateur "
					+ " SET UT_PASS = '" + a.getPass()
					+ "', UT_NOM = '" + a.getNom()
					+ "', UT_PRENOM = '" + a.getPrenom()
					+ "', UT_HASH = '" + a.getHash()
					+ "', UT_CP = '" + a.getCp()
					+ "', UT_ISADMIN = " + admin
					+ " WHERE UT_ID = " + a.getId() );
			msg = "updated !";
		}
		else
		{
			if(a.getHash().length() == 0) // si pas de hash
			{
				a.setHash(UUID.randomUUID().toString()); // on en cree un
				//a.setHash("1abc1e35-ee8a-4328-a553-8908b9af148d");
			}
			
			// puis on gere le pass (pass[--lol--]hash)
			String encryptedPass = encryptPass( a.getPass() + "-lol-" + a.getHash() );
			a.setPass(encryptedPass);
			
			res = OracleJDBC.query(" INSERT INTO Utilisateur values ("
					+ a.getId()
					+ ", '" + a.getNom()
					+ "', '" + a.getPrenom()
					+ "', '" + a.getCp()
					+ "', '" + a.getPass()
					+ "', '" + a.getHash()
					+ "', '" + admin
					+ "')");
			msg = "created !";
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
		else
			System.out.println("nothing done");
	}
	
	/*private static String encodePass(String str)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			byte[] bytesOfMessage = str.getBytes("UTF-8");
			byte[] thedigest = md.digest(bytesOfMessage);
			
			SupaLogga.log("pass generated: " + str + " --> " + thedigest.toString());
			return thedigest.toString();
		}
		catch(Exception e)
		{
			SupaLogga.log("Erreur md5encode()..." + e.getMessage());
			e.printStackTrace();
			
			return "";
		}
	}*/
	
	private static String encryptPass(String str)
	{
		String passwordEnc = "";
		
		try
		{
			passwordEnc = AESencrp.encrypt(str);
			
			SupaLogga.log("pass generated: " + str + " --> " + passwordEnc);
			return passwordEnc;
		}
		catch(Exception e)
		{
			SupaLogga.log("Erreur encodePass()...\n\t" + e.getMessage());
			e.printStackTrace();
			
			return "";
		}
	}
	
	public static void main(String[] argv)
	{/*
		System.out.println("---- UtilisateurHelper::main() ----\n");
		
		Utilisateur u = UtilisateurHelper.getFirst();
		System.out.println(u.toString());
		u.setPass("123Soleil");
		u.setHash("321Lune");
		u.setCp("2A");
		UtilisateurHelper.commitChange(u);
		System.out.println(u.toString());		
		
		System.out.println("\n-----------------------------------");
	*/}
}
