package view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;

import model.Utilisateur;
import model.UtilisateurHelper;
import controller.AESencrp;
import controller.Controlla;
import controller.SupaLogga;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView extends JFrame
{
	private JTextField tfNomDutilisateur;
	private JTextField tfMotDePasse;
	private JButton btnValider;
	private JButton btnQuitter;	
	
	public LoginView()
	{
		super();
		initUI();
		Controlla.get_instance().set_currentContaina(this);
	}

	private void initUI()
	{
		this.setSize(316, 227);
		
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(5);
		
		JPanel pNorth = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pNorth.getLayout();
		flowLayout.setVgap(30);
		flowLayout.setHgap(50);
		pNorth.setSize(WIDTH, 50);
		pNorth.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(pNorth, BorderLayout.NORTH);
		
		JPanel pCenter = new JPanel();
		getContentPane().add(pCenter, BorderLayout.CENTER);
		GridBagLayout gbl_pCenter = new GridBagLayout();
		gbl_pCenter.columnWidths = new int[] {212, 212};
		gbl_pCenter.rowHeights = new int[] {50, 50};
		gbl_pCenter.columnWeights = new double[]{0.0, 1.0};
		gbl_pCenter.rowWeights = new double[]{0.0, 0.0};
		pCenter.setLayout(gbl_pCenter);
		
		JLabel lblNomDutilisateur = new JLabel("Nom d'utilisateur :");
		lblNomDutilisateur.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNomDutilisateur = new GridBagConstraints();
		gbc_lblNomDutilisateur.fill = GridBagConstraints.BOTH;
		gbc_lblNomDutilisateur.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomDutilisateur.gridx = 0;
		gbc_lblNomDutilisateur.gridy = 0;
		pCenter.add(lblNomDutilisateur, gbc_lblNomDutilisateur);
		lblNomDutilisateur.setLabelFor(tfNomDutilisateur);
		
		tfNomDutilisateur = new JTextField();
		GridBagConstraints gbc_tfNomDutilisateur = new GridBagConstraints();
		gbc_tfNomDutilisateur.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNomDutilisateur.insets = new Insets(0, 0, 5, 0);
		gbc_tfNomDutilisateur.gridx = 1;
		gbc_tfNomDutilisateur.gridy = 0;
		pCenter.add(tfNomDutilisateur, gbc_tfNomDutilisateur);
		tfNomDutilisateur.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblMotDePasse = new GridBagConstraints();
		gbc_lblMotDePasse.fill = GridBagConstraints.BOTH;
		gbc_lblMotDePasse.insets = new Insets(0, 0, 0, 5);
		gbc_lblMotDePasse.gridx = 0;
		gbc_lblMotDePasse.gridy = 1;
		pCenter.add(lblMotDePasse, gbc_lblMotDePasse);
		lblMotDePasse.setLabelFor(tfMotDePasse);
		
		tfMotDePasse = new JTextField();
		GridBagConstraints gbc_tfMotDePasse = new GridBagConstraints();
		gbc_tfMotDePasse.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfMotDePasse.gridx = 1;
		gbc_tfMotDePasse.gridy = 1;
		pCenter.add(tfMotDePasse, gbc_tfMotDePasse);
		tfMotDePasse.setColumns(10);
		
		JPanel pEast = new JPanel();
		getContentPane().add(pEast, BorderLayout.EAST);
		
		JPanel pSouth = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pSouth.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(pSouth, BorderLayout.SOUTH);
		
		btnValider = new JButton("Valider");
		btnValider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				if(tfMotDePasse.getText().length() == 0 || tfNomDutilisateur.getText().length() == 0)
					JOptionPane.showMessageDialog(LoginView.this, "Veuillez renseigner les champs",
							"Inane warning",
						    JOptionPane.WARNING_MESSAGE);
				else
					login();
				
				SupaLogga.log("validé");
			}
		});
		pSouth.add(btnValider);
		
		btnQuitter = new JButton("Quitter");
		btnQuitter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				LoginView.this.dispose();
				SupaLogga.log("quitté");
			}
		});
		pSouth.add(btnQuitter);
		
		this.setVisible(true);
	}
		
	private void login()
	{
		Controlla.get_instance().login(tfNomDutilisateur.getText(), tfMotDePasse.getText());
	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		LoginView lv = new LoginView();
	}

}