package model;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import sun.security.util.Length;

public class ArticleTableModel extends DefaultTableModel
{
	/**
	 * 
	 */
	final static String[] colNames = {"Label", "Référence", "Prix"};
	private Vector<Article> articles = null;

	public ArticleTableModel(Vector<Article> smeArtcls)
	{
		super(0, 0);
		setColumnIdentifiers(colNames);
		articles = smeArtcls;
		
		for (Article u : smeArtcls)
			addRow( u.toVector() );
	}
}
