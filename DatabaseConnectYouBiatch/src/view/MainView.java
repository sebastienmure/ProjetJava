package view;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import model.Article;
import model.ArticleHelper;
import model.Utilisateur;
import model.UtilisateurHelper;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class MainView extends JFrame
{
	private Utilisateur _user = null;
	
	public MainView()
	{
		initUI();	
	}
	
	public MainView(Utilisateur user)
	{
		_user = user;
		initUI();
	}
	
	private void initUI()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JButton btnNewButton = new JButton("Liste des articles");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Vector<Article> v = ArticleHelper.getAll();
		    	DefaultTableModel dt = ArticleHelper.toTableModel(v);
		    	
		        ListeArticlesView fen = new ListeArticlesView(dt);

		        fen.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Liste des utilisateurs");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		    	Vector<Utilisateur> v = UtilisateurHelper.getAll();
		    	DefaultTableModel dt = UtilisateurHelper.toTableModel(v);
		    	
		        ListeUtilisateursView fen = new ListeUtilisateursView(dt);
		        //fen.loadTableModel(dt);
		        fen.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 1;
		getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Liste des consultations");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 2;
		getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Ajouter un article");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArticleFormView n = new ArticleFormView();
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 0;
		gbc_btnNewButton_3.gridy = 3;
		getContentPane().add(btnNewButton_3, gbc_btnNewButton_3);
		
		JButton btnAjouterUnUtilisateur = new JButton("Ajouter un utilisateur");
		btnAjouterUnUtilisateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UtilisateurFormView n = new UtilisateurFormView();
			}
		});
		GridBagConstraints gbc_btnAjouterUnUtilisateur = new GridBagConstraints();
		gbc_btnAjouterUnUtilisateur.insets = new Insets(0, 0, 5, 5);
		gbc_btnAjouterUnUtilisateur.gridx = 1;
		gbc_btnAjouterUnUtilisateur.gridy = 3;
		getContentPane().add(btnAjouterUnUtilisateur, gbc_btnAjouterUnUtilisateur);
		
		setSize(350, 130);
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		MainView mv = new MainView();
	}

}
