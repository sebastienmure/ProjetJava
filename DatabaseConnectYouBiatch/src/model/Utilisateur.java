package model;
import java.util.HashSet;
import java.util.Vector;

/*******************************************************************************
 * 2014, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Utilisateur.
 * 
 * @author Dju
 */
public class Utilisateur
{
	/**
	 * Description of the property id.
	 */
	private int id;

	/**
	 * Description of the property isAdmin.
	 */
	private boolean isAdmin;

	/**
	 * Description of the property pass.
	 */
	private String pass;

	/**
	 * Description of the property nom.
	 */
	private String nom;

	/**
	 * Description of the property hash.
	 */
	private String hash;

	/**
	 * Description of the property cp.
	 */
	private String cp;

	/**
	 * @brief Not a Column of the Utilisateur table
	 */
	private boolean isInBase;
	
	/**
	 * Description of the property articles.
	 */
	public HashSet<Article> articles = new HashSet<Article>();

	/**
	 * Description of the property prenom.
	 */
	private String prenom;

	/**
	 * The constructor.
	 */
	public Utilisateur()
	{
		// Start of user code constructor for Utilisateur)
		super();
		// End of user code
		
		id = -1;
		nom = "";
		prenom ="";
		cp = "";
		pass = "";
		hash = "";
		isAdmin = false;
	}

	public Utilisateur(int id, String nom, String prenom, String cp, String pass, String hash, boolean isAdmin, boolean isInBase)
	{
		// Start of user code constructor for Utilisateur)
		super();
		// End of user code
		
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.cp = cp;
		this.pass = pass;
		this.hash = hash;
		this.isAdmin = false;
		this.isInBase = isInBase;
	}

	/**
	 * @return the id
	 */
	public int getId()
	{
	    return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id)
	{
	    this.id = id;
	}

	/**
	 * @return the isAdmin
	 */
	public boolean getIsAdmin()
	{
	    return isAdmin;
	}

	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setIsAdmin(boolean isAdmin)
	{
	    this.isAdmin = isAdmin;
	}

	/**
	 * @return the pass
	 */
	public String getPass()
	{
	    return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass)
	{
	    this.pass = pass;
	}

	/**
	 * @return the nom
	 */
	public String getNom()
	{
	    return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom)
	{
	    this.nom = nom;
	}

	/**
	 * @return the hash
	 */
	public String getHash()
	{
	    return hash;
	}

	/**
	 * @param hash the hash to set
	 */
	public void setHash(String hash)
	{
	    this.hash = hash;
	}

	/**
	 * @return the cp
	 */
	public String getCp()
	{
	    return cp;
	}

	/**
	 * @param cp the cp to set
	 */
	public void setCp(String cp)
	{
		if(cp.length() == 0)
			return;
		
		// verifier que c'est bien un code postal
		this.cp = cp;
	}

	/**
	 * @return the articles
	 */
	public HashSet<Article> getArticles()
	{
	    return articles;
	}

	/**
	 * @param articles the articles to set
	 */
	public void setArticles(HashSet<Article> articles)
	{
	    this.articles = articles;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom()
	{
	    return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom)
	{
	    this.prenom = prenom;
	}

	public boolean isInBase()
	{
		return isInBase;
	}

	public void setInBase(boolean isInBase)
	{
		this.isInBase = isInBase;
	}

	@Override
	public String toString()
	{
		return "Utilisateur [id=" + id + ", isAdmin=" + isAdmin + ", pass="
				+ pass + ", nom=" + nom + ", hash=" + hash + ", cp=" + cp
				+ ", isInBase=" + isInBase + ", articles=" + articles
				+ ", prenom=" + prenom + "]";
	}
	
	public Vector<String> toVector()
	{
		Vector<String> v = new Vector<>();
		
		v.add(nom);
		v.add(prenom);
		v.add(cp);
		
		return v;
	}
}
