package View;

import javax.swing.JButton;
import javax.swing.JDialog;

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

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Controller.SupaLogga;
import Model.Article;
import Model.ArticleHelper;

public class NewArticleWindow extends JDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2925017973870016209L;
	private JTextField tfLibelle;
	private JTextField tfReference;
		private JTextField tfPrix;
		private JTextField tfImg;

	public NewArticleWindow()
	{
		super();
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(5);
		this.setSize(316, 200);
		
		JPanel northPanel = new JPanel();
		getContentPane().add(northPanel, BorderLayout.NORTH);
		
		JLabel lblTitre = new JLabel("Ajout d'un nouvel article");
		lblTitre.setFont(new Font("Verdana", Font.PLAIN, 20));
		northPanel.add(lblTitre);
		
		JPanel centerPanel = new JPanel();
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(4, 2, 0, 5));
		
		JLabel lblLibell = new JLabel("Libellé");
		lblLibell.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblLibell);
		
		tfLibelle = new JTextField();
		lblLibell.setLabelFor(tfLibelle);
		tfLibelle.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(tfLibelle);
		tfLibelle.setColumns(10);
		
		JLabel lblReference = new JLabel("Référence");
		lblReference.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblReference);
		
		tfReference = new JTextField();
		lblReference.setLabelFor(tfReference);
		tfReference.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(tfReference);
		tfReference.setColumns(10);
		
		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblPrix);
		
		tfPrix = new JTextField();
		lblPrix.setLabelFor(tfPrix);
		centerPanel.add(tfPrix);
		tfPrix.setColumns(10);
		
		JLabel lblImg = new JLabel("Image");
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblImg);
		
		tfImg = new JTextField();
		lblImg.setLabelFor(tfImg);
		centerPanel.add(tfImg);
		tfImg.setColumns(10);
		
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
		{
			SupaLogga.log("l108 : null pointer for button...");
			this.dispose();
		}
		else
			switch (but.getText())
			{
				case "Annuler":
					SupaLogga.log("clic sur \"Annuler\"");
					this.dispose(); // ferme la fenetre
					break;
				case "Valider":
					SupaLogga.log("clic sur \"Valider\"");
					valider();
					break;
				default:
					SupaLogga.log("default case...");
					break;
			}
	}
	
	private void valider()
	{
		String libelle = this.tfLibelle.getText();
		String image = this.tfImg.getText();
		String ref = this.tfReference.getText();
		float prix = 0;
		int id = -1;
		
		
		// verif que tout est rempli
		if(libelle.length() == 0 || ref.length() == 0)
		{
			SupaLogga.log("Il faut renseigner un libellé et une référence");
			return;
		}
		
		// verif le prix
		try
		{
			prix = Float.parseFloat(this.tfPrix.getText());
		}
		catch(Exception e)
		{
			SupaLogga.log("erreur parse, il faut taper un float pour le prix!");
			return;
		}
		
		id = ArticleHelper.getLastId() + 1;
		Article a = new Article(id, prix, ref, libelle, image, false);
		SupaLogga.log(a.toString());
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		NewArticleWindow n = new NewArticleWindow();
	}
}