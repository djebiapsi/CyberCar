package Application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class FirstConnect {

	private JFrame frame1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstConnect window = new FirstConnect();
					window.frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FirstConnect() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame1.
	 */
	private void initialize() {
		frame1 = new JFrame();
		frame1.setBounds(100, 100, 450, 300);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(null);
		
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
					 System.out.println("Mot de passe singulier");
				 }
				 
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(password1);
				 if(!matcher.matches()) {
					 System.out.println("Mot de passe non-conforme");
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
					
						st.execute("UPDATE `user` SET `password`='[value-3]'WHERE id = " + id);
						
						
					}catch (Exception e1) {
						// TODO: handle exception
						System.out.println(e1);
					}
						
				}
				 
				
			}
		});
	}
}
