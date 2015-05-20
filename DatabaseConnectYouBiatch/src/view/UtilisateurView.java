package view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.table.DefaultTableModel;

import model.Article;
import model.ArticleHelper;
import model.Utilisateur;
import model.UtilisateurHelper;
import controller.SupaLogga;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.util.Vector;

public class UtilisateurView extends JDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2925017973870016209L;

	public UtilisateurView(int v)
	{
		super();
		Utilisateur user = UtilisateurHelper.getOneById(v);
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(5);
		this.setSize(316, 200);
		
		JPanel northPanel = new JPanel();
		getContentPane().add(northPanel, BorderLayout.NORTH);
		
		JLabel lblTitre = new JLabel("User "+user.getId());
		lblTitre.setFont(new Font("Verdana", Font.PLAIN, 20));
		northPanel.add(lblTitre);
		
		JPanel centerPanel = new JPanel();
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(4, 2, 0, 5));
		
		JLabel libUserName = new JLabel(user.getNom() + " " + user.getPrenom());
		libUserName.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(libUserName);
		
		JLabel libUserCP = new JLabel(user.getCp());
		libUserCP.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(libUserCP);
		
		JPanel southPanel = new JPanel();
		FlowLayout fl_southPanel = (FlowLayout) southPanel.getLayout();
		fl_southPanel.setHgap(2);
		fl_southPanel.setVgap(3);
		fl_southPanel.setAlignment(FlowLayout.RIGHT);
		southPanel.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		JButton btnAnnuler = new JButton("Fermer");
		btnAnnuler.addActionListener(this);
		southPanel.add(btnAnnuler);
		
		this.setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // ferme la fenetre
	}
	
	public void actionPerformed(ActionEvent e)
	{
		JButton but = null;

		if(e == null || e.getSource() == null)
		{
			SupaLogga.log("l108 : null pointer for action's source...");
			this.dispose();
		}
		else
			if(e.getSource().getClass() == JButton.class)
				but = (JButton)e.getSource();

		if(but == null)
		{
			SupaLogga.log("l108 : null pointer for button...");
			this.dispose();
		}
		else
			switch (but.getText())
			{
				case "Fermer":
					SupaLogga.log("clic sur \"Annuler\"");
					Vector<Utilisateur> v = UtilisateurHelper.getAll();
			    	DefaultTableModel dt = UtilisateurHelper.toTableModel(v);
			    	
			        ListeUtilisateursView fen = new ListeUtilisateursView(dt);
			        //fen.loadTableModel(dt);
			        fen.setVisible(true);
			        fen.setLocationRelativeTo( null );
					this.dispose(); // ferme la fenetre
					break;
				default:
					SupaLogga.log("default case...");
					break;
			}
	}

	public static void main(String[] args)
	{
		
	}
}