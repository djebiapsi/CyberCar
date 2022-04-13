package Application;

import java.awt.EventQueue;
import Application.CreateUser;
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
				
				
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					// Connection à la base de donnée
					connect = DriverManager.getConnection("jdbc:mysql://localhost/cybercar", "root", "");
					st = connect.createStatement();

					// Récupération des mails
				
					rS = st.executeQuery("SELECT password FROM `user` WHERE email = '"+email+"'");
					
					if(rS.next()) {
						
						if (!rS.getString(1).equals(hashpass)) {
							connected = false;
							System.out.println("Mot de pass incorrect");
						}
					
					}else {
						connected = false;
						System.out.println("Email incorrect");
					}
				
					
					if (connected) {
						
						if (password.equals("12345678")) {
							
							JOptionPane.showMessageDialog(null, "First Connexion");
							
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
