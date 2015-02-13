package controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Utilisateur;
import model.UtilisateurHelper;
import view.LoginView;
import view.MainView;

public class Controlla
{
	public static Controlla _instance = null;
	private JFrame _currentContaina = null;
	private Utilisateur _currentUsa = null;
	
	public static Controlla get_instance()
	{
		if(_instance == null)
			_instance = new Controlla();
		
		return _instance;
	}
	
	private Controlla()
	{
	}
	
	public void login(String userName, String pass)
	{
		Utilisateur user = UtilisateurHelper.getOneByUsername(userName);
		if(user == null)
			showMessageDialog("L'utilisateur " + userName + " n'existe pas...",
					"Inane error",
				    JOptionPane.ERROR_MESSAGE);
		else
		{
			try
			{
				String userCrystalPass = AESencrp.decrypt(user.getPass()).split("-")[0];
				SupaLogga.log( "real: " + userCrystalPass + "\n\tEntered: " + pass);
				
				if(!pass.equals(userCrystalPass))
					showMessageDialog("Mot de passe errone.",
						    "A plain message",
						    JOptionPane.PLAIN_MESSAGE);
				else
				{
					showMessageDialog("Bienvenu " + user.getPrenom() + "! Vous êtes maintenant connecté.",
						    "A plain message",
						    JOptionPane.PLAIN_MESSAGE);
					
					// on ferme la login view
					_currentContaina.dispose();
					
					// on ouvre la main view des familles
					_currentUsa = user;
					_currentContaina = new MainView(user);
				}
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				SupaLogga.log("Erreur encrypt");
				e.printStackTrace();
			}
		}
	}
	
	private void showMessageDialog(String msg, String type, int optionMessage)
	{
		JOptionPane.showMessageDialog(null,
				msg,
				type,
			    optionMessage);
	}

	public JFrame get_currentContaina()
	{
		return _currentContaina;
	}

	public void set_currentContaina(JFrame _currentContaina)
	{
		this._currentContaina = _currentContaina;
	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		LoginView lv = new LoginView();
	}
}
