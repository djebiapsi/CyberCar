package Application;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class LogIn {

	private JFrame frame;
	private JTextField textEmail;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn window = new LogIn();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LogIn() {
		initialize();
	}
	
	
	/**
	 * Premiere connexion, initialisation du mdp
	 * @param id
	 */
	public void firstconnect(int id) {
		
		 JFrame frame1;
		 JPasswordField passwordField;
		 JPasswordField passwordField_1;


		frame1 = new JFrame();
		frame1.setBounds(100, 100, 450, 300);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(null);
		frame1.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("Nouveau mot de passe");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(134, 30, 211, 53);
		frame1.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mot de passe");
		lblNewLabel_1.setBounds(31, 111, 119, 17);
		frame1.getContentPane().add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(217, 109, 126, 17);
		frame1.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(217, 162, 128, 17);
		frame1.getContentPane().add(passwordField_1);
		
		JLabel lblNewLabel_2 = new JLabel("Confirmer le mot de passe");
		lblNewLabel_2.setBounds(31, 164, 164, 14);
		frame1.getContentPane().add(lblNewLabel_2);
		
		JButton btnSave = new JButton("Enregistrer");
		
		btnSave.setBounds(134, 203, 111, 23);
		frame1.getContentPane().add(btnSave);
		
		JButton btnNewButton = new JButton("?");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HelpPasswor.main(null);
			}
		});
		btnNewButton.setBounds(381, 215, 43, 35);
		frame1.getContentPane().add(btnNewButton);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean save = true;
				
				String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,16}$";
				@SuppressWarnings("deprecation")
				String password1 = passwordField.getText();
				@SuppressWarnings("deprecation")
				String password2 = passwordField_1.getText();
				
				
				if(!password1.equals(password2)) {
					 save = false;
					 JOptionPane.showMessageDialog(null, "Mot de passe singulier");
				 }
				 
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(password1);
				 if(!matcher.matches()) {
					 JOptionPane.showMessageDialog(null, "Mot de passe non-conforme");
					 save = false;
				 }
				 
				if (save) {
					
					String password = CreateUser.text2hash(password1, "MD5");
					
					Connection connect; // connexion

					Statement st; // Requete
					
					ResultSet rS; // Stock les resultat de la requete
					
					
					try {
						
						Class.forName("com.mysql.cj.jdbc.Driver");
						
						// Connection à la base de donnée
						connect = DriverManager.getConnection("jdbc:mysql://localhost/cybercar", "root", "");
						st = connect.createStatement();

						// Enreiste le nouveau mdp
					
						st.execute("UPDATE `user` SET `password`='"+password+"'WHERE id = " + id);
						
						JOptionPane.showMessageDialog(null, "Nouveau mot de passe enregistré");
						
						frame1.setVisible(false);
						
						
					}catch (Exception e1) {
						// TODO: handle exception
						System.out.println(e1);
					}
						
				}
				 
				
			}
		});
	
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 335);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(87, 88, 70, 25);
		frame.getContentPane().add(lblNewLabel);
		
		textEmail = new JTextField();
		textEmail.setBounds(166, 90, 144, 20);
		frame.getContentPane().add(textEmail);
		textEmail.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Mot de passe");
		lblNewLabel_1.setBounds(55, 152, 78, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(166, 149, 144, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Connexion");
		btnNewButton.setBounds(129, 208, 122, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("CONNEXION");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(160, 24, 122, 33);
		frame.getContentPane().add(lblNewLabel_2);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String email = textEmail.getText().toLowerCase();
				@SuppressWarnings("deprecation")
				String password = passwordField.getText();
				
				//System.out.println(password);
				
				String hashpass = CreateUser.text2hash(password, "MD5");
				
				boolean connected = true;
				
				Connection connect; // connexion

				Statement st; // Requete
				
				ResultSet rS; // Stock les resultat de la requete
				
				int id = 0;
				
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					// Connection à la base de donnée
					connect = DriverManager.getConnection("jdbc:mysql://localhost/cybercar", "root", "");
					st = connect.createStatement();

					// Récupération des mails
				
					rS = st.executeQuery("SELECT password, id FROM `user` WHERE email = '"+email+"'");
					
					if(rS.next()) {
						
						if (!rS.getString(1).equals(hashpass)) {
							connected = false;
							JOptionPane.showMessageDialog(null, "Mot de pass incorrect");
						}
						
						id = rS.getInt(2);
					
					}else {
						connected = false;
						JOptionPane.showMessageDialog(null, "Email incorrect");
					}
				
					
					if (connected) {
						
						if (password.equals("12345678")) {
							
							firstconnect(id);
							
						}else {
							JOptionPane.showMessageDialog(null, "Connexion réussie");
						}
						
					}
					
				}catch (Exception e1) {
					// TODO: handle exception
				}
				
			}
		});
	}
}
