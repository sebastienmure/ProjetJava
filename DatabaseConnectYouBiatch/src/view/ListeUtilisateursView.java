package view;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Utilisateur;
import model.UtilisateurHelper;
import controller.OracleJDBC;

 
// CTRL + SHIFT + O pour generer les imports
public class ListeUtilisateursView extends JFrame
{
    /**
     * 
     */
    private static final long serialVersionUID = 668229218845723247L;
    DefaultTableModel dtm = null;
    JTable tableau = null;
    //Object[][] data;
    
    public ListeUtilisateursView(DefaultTableModel dt)
    {
        this.setLocationRelativeTo(null);
        this.setTitle("JTable");
        this.setSize(800, 450);
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
        	public void windowClosing(java.awt.event.WindowEvent evt) {
        		java.awt.Window win[] = java.awt.Window.getWindows(); 
            	for(int i=0;i<win.length;i++){ 
            	    win[i].dispose(); 
            	    win[i]=null;
            	} 
        		MainView mv = new MainView();
        		mv.setLocationRelativeTo( null );
        		mv.setVisible(true);
    		} 
		});
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
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
                	java.awt.Window win[] = java.awt.Window.getWindows(); 
                	for(int i=0;i<win.length;i++){ 
                	    win[i].dispose(); 
                	    win[i]=null;
                	} 
            		UtilisateurView fen = new UtilisateurView(row);
                	fen.setVisible(true);
                	fen.setLocationRelativeTo( null );
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
    	Vector<Utilisateur> v = UtilisateurHelper.getAll();
    	DefaultTableModel dt = UtilisateurHelper.toTableModel(v);
    	
        ListeUtilisateursView fen = new ListeUtilisateursView(dt);
        fen.setVisible(true);
        fen.setLocationRelativeTo( null );
        
    }
}