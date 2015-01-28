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

import model.Article;
import model.ArticleHelper;
import model.Utilisateur;
import model.UtilisateurHelper;
import controller.SupaLogga;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;

public class UtilisateurFormView extends JDialog implements ActionListener
{
	private static final long serialVersionUID = -7400971988391256247L;

	private JTextField tfNom;
	private JTextField tfPrenom;
	private JTextField tfCp;
	private JTextField tfPass;
	private JCheckBox chckbxAdministrateur;
	

	public UtilisateurFormView()
	{
		super();
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(5);
		this.setSize(316, 227);
		
		JPanel northPanel = new JPanel();
		getContentPane().add(northPanel, BorderLayout.NORTH);
		
		JLabel lblTitre = new JLabel("Ajout d'un nouvel utilisateur");
		lblTitre.setFont(new Font("Verdana", Font.PLAIN, 20));
		northPanel.add(lblTitre);
		
		JPanel centerPanel = new JPanel();
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(5, 2, 0, 5));
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblNom);
		
		tfNom = new JTextField();
		lblNom.setLabelFor(tfNom);
		tfNom.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(tfNom);
		tfNom.setColumns(10);
		
		JLabel lblPrenom = new JLabel("Prénom");
		lblPrenom.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblPrenom);
		
		tfPrenom = new JTextField();
		lblPrenom.setLabelFor(tfPrenom);
		tfPrenom.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(tfPrenom);
		tfPrenom.setColumns(10);
		
		JLabel lblCp = new JLabel("Code Postal");
		lblCp.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblCp);
		
		tfCp = new JTextField();
		lblCp.setLabelFor(tfCp);
		centerPanel.add(tfCp);
		tfCp.setColumns(10);
		
		JLabel lblPass = new JLabel("Mot de passe");
		lblPass.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblPass);
		
		tfPass = new JTextField();
		lblPass.setLabelFor(tfPass);
		centerPanel.add(tfPass);
		tfPass.setColumns(10);
		
		JLabel lblAdministrateur = new JLabel("Administrateur");
		lblAdministrateur.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblAdministrateur);
		
		chckbxAdministrateur = new JCheckBox("");
		lblAdministrateur.setLabelFor(chckbxAdministrateur);
		chckbxAdministrateur.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(chckbxAdministrateur);
		
		JPanel southPanel = new JPanel();
		FlowLayout fl_southPanel = (FlowLayout) southPanel.getLayout();
		fl_southPanel.setHgap(2);
		fl_southPanel.setVgap(3);
		fl_southPanel.setAlignment(FlowLayout.RIGHT);
		southPanel.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(this);
		southPanel.add(btnAnnuler);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(this);
		southPanel.add(btnValider);
		
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
			SupaLogga.log("l108 : null pointer for button...");
		else
			switch (but.getText())
			{
				case "Annuler":
					SupaLogga.log("clic sur \"Annuler\"");
					break;
				case "Valider":
					SupaLogga.log("clic sur \"Valider\"");
					valider();
					break;
				default:
					SupaLogga.log("default case...");
					break;
			}
		
		this.dispose(); // ferme la fenetre
	}
	
	private void valider()
	{
		String nom = this.tfNom.getText();
		String pass = this.tfPass.getText();
		String prenom = this.tfPrenom.getText();
		String cp = this.tfCp.getText();
		boolean isAdmin = this.chckbxAdministrateur.isSelected();
		int id = -1;
		
		
		// verif que tout est rempli
		if(nom.length() == 0 || prenom.length() == 0 || pass.length() == 0 || cp.length() == 0)
		{
			JOptionPane.showMessageDialog(this, "Il faut renseigner un nom, prénom, code postale et un mot de passe.");
			//SupaLogga.log("Il faut renseigner un libellé et une référence");
			return;
		}
		
		id = UtilisateurHelper.getLastId() + 1;
		if(id == -1)
		{
			JOptionPane.showMessageDialog(this, "Erreur avec l'ID...");
			return;
		}

		
		Utilisateur u = new Utilisateur(id, nom, prenom, cp, pass, "", isAdmin, false);
		UtilisateurHelper.commitChange(u);
		SupaLogga.log(u.toString());
	}

	public static void main(String[] args)
	{
		UtilisateurFormView n = new UtilisateurFormView();
	}
}