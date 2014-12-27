import java.util.HashSet;

/*******************************************************************************
 * 2014, All rights reserved.
 *******************************************************************************/


// Start of user code (user defined imports)

// End of user code

/**
 * Description of Article.
 * 
 * @author Dju
 */
public class Article
{
	/**
	 * Description of the property prix.
	 */
	private float prix;
	
	/**
	 * Description of the property label.
	 */
	private String label;
	
	/**
	 * Description of the property image.
	 */
	private String image;
	
	/**
	 * Description of the property ref.
	 */
	private String ref;
	
	private boolean isInBase;
	
	/**
	 * Description of the property id.
	 */
	private int id;
	
	/**
	 * Description of the property utilisateurs.
	 */
	public HashSet<Utilisateur> utilisateurs = new HashSet<Utilisateur>();
	
	// Start of user code (user defined attributes for Article)
	
	// End of user code
	
	/**
	 * The constructor.
	 */
	public Article()
	{
		// Start of user code constructor for Article)
		super();
		// End of user code
		
		prix = -1;
		id = -1;
		ref = "";
		label = "";
		image = "";
		isInBase = false;
	}
	
	public Article(int id, float prix, String ref, String label, String image, boolean isInBase)
	{
		// Start of user code constructor for Article)
		super();
		// End of user code
		
		this.prix = prix;
		this.id = id;
		this.ref = ref;
		this.label = label;
		this.image = image;
		this.isInBase = isInBase;
	}

	/**
	 * @return the prix
	 */
	public float getPrix()
	{
	    return prix;
	}

	/**
	 * @param prix the prix to set
	 */
	public void setPrix(float prix)
	{
	    this.prix = prix;
	}

	/**
	 * @return the label
	 */
	public String getLabel()
	{
	    return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label)
	{
	    this.label = label;
	}

	/**
	 * @return the image
	 */
	public String getImage()
	{
	    return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image)
	{
	    this.image = image;
	}

	/**
	 * @return the ref
	 */
	public String getRef()
	{
	    return ref;
	}

	/**
	 * @param ref the ref to set
	 */
	public void setRef(String ref)
	{
	    this.ref = ref;
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
	 * @return the utilisateurs
	 */
	public HashSet<Utilisateur> getUtilisateurs()
	{
	    return utilisateurs;
	}

	/**
	 * @param utilisateurs the utilisateurs to set
	 */
	public void setUtilisateurs(HashSet<Utilisateur> utilisateurs)
	{
	    this.utilisateurs = utilisateurs;
	}
	
	public void addUtilisateurs(Utilisateur utilisateur)
	{
		if(utilisateur == null)
			return;
					
	    this.utilisateurs.add(utilisateur);
	}
	
	
	@Override
	public String toString()
	{
		return "Article [prix=" + prix + ", label=" + label + ", image="
				+ image + ", ref=" + ref + ", id=" + id + ", utilisateurs="
				+ utilisateurs + "]";
	}

	public boolean isInBase() {
		return isInBase;
	}

	public void setInBase(boolean isInBase) {
		this.isInBase = isInBase;
	}
	
	// Start of user code (user defined methods for Article)
	
	// End of user code
}
