package view;


import java.awt.event.WindowAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Article;
import model.ArticleHelper;
import model.Utilisateur;
import model.UtilisateurHelper;
import controller.OracleJDBC;

 
// CTRL + SHIFT + O pour generer les imports
public class ListeArticlesView extends JFrame
{
    /**
     * 
     */
    private static final long serialVersionUID = 668229218845723247L;
    DefaultTableModel dtm = null;
    JTable tableau = null;
    //Object[][] data;
    
    public ListeArticlesView(DefaultTableModel dt)
    {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JTable");
        this.setSize(800, 450);
        
        //Les données du tableau
        /*
        Object[][] data =
        {
            {"Cysboy", "28 ans", "1.80 m"},
            {"BZHHydde", "28 ans", "1.80 m"},
            {"IamBow", "24 ans", "1.90 m"},
            {"FunMan", "32 ans", "1.85 m"}
        };
        */
        
        // Les titres des colonnes
        //String    title[] = {"Pseudo", "Age", "Taille"};
        tableau = new JTable(dt);
        tableau.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tableau.rowAtPoint(evt.getPoint());
                int col = tableau.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
            		ArticleView fen = new ArticleView(row);
                	fen.setVisible(true);
                }
            }
        });
        
        
        // Nous ajoutons notre tableau à notre contentPane dans un scroll
        // Sinon les titres des colonnes ne s'afficheront pas !
        this.getContentPane().add(new JScrollPane(tableau));
    }
    
    public void loadTableModel(DefaultTableModel d)
    {
    	if(d == null)
    		return;
    	
    	this.dtm = d;
    	tableau = new JTable(dtm);
    }

    public static void main(String[] args)
    {
    	Vector<Article> v = ArticleHelper.getAll();
    	DefaultTableModel dt = ArticleHelper.toTableModel(v);
    	
        ListeArticlesView fen = new ListeArticlesView(dt);

        fen.setVisible(true);
    }
}