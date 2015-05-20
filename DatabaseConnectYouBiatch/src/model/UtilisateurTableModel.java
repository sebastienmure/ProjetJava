package model;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;


public class UtilisateurTableModel extends DefaultTableModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6263619503524568877L;
	final static String[] colNames = {"Nom", "Prénom", "Code Postal"};
	private Vector<Utilisateur> users = null;

	public UtilisateurTableModel(Vector<Utilisateur> smeUsers)
	{
		super(0, 0);
		setColumnIdentifiers(colNames);
		users = smeUsers;
		
		for (Utilisateur u : smeUsers)
			addRow( u.toVector() );
	}
}
