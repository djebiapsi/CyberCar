package Application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.List;

import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class CreateUser {

	private JFrame frame;
	private JTextField textNom;
	private JTextField textEmail;
	private JTextField textDDN;
	private JTextField textNDT;
	private JTextField textSalaire;
	private JTextField textAdresse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUser window = new CreateUser();
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
	public CreateUser() {
		initialize();
	}
	
	/**
	 * Fonction de hashage : prend en entrée le text a hasher ainsi que l'algorithme de  hashage
	 * et retourne le texte hashé.
	 * 
	 * @param text
	 * @param algo
	 * @return text hasher
	 */
	static String text2hash(String text, String algo){
		
		try {
			MessageDigest digest = MessageDigest.getInstance(algo);
			byte[] hash = text.getBytes();
			digest.update(hash);
			String x = new BigInteger(hash).toString(16);
			return x;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "pas de texte";
		
	}
	
	/**
	 * Fonction de vérification du format de la date : prend en entrée une date en format
	 * String ainsi que le format de la date voulu exemple "YYYY-MM-dd" et retourne
	 * True si la date est conforme er False si la date n'est pas conforme
	 * @param date
	 * @param pattern
	 * @return True si date conforme/False si date non conforme
	 */
	public static boolean check(String date, String pattern) {
		try {
			java.util.Date simple = new SimpleDateFormat(pattern).parse(date);
			Format format = new SimpleDateFormat(pattern);

			if (!date.equals(format.format(simple))) {
				return false;
			} else {
				return true;
			}
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 560, 381);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nouvel Utilisateur");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(228, 11, 143, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setBounds(10, 69, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setBounds(10, 112, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Date de naissance");
		lblNewLabel_3.setBounds(10, 157, 122, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Num\u00E9ro de t\u00E9l\u00E9phone");
		lblNewLabel_4.setBounds(267, 69, 122, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Adresse");
		lblNewLabel_5.setBounds(10, 210, 72, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Departement");
		lblNewLabel_6.setBounds(267, 157, 104, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Salaire");
		lblNewLabel_7.setBounds(267, 112, 46, 14);
		frame.getContentPane().add(lblNewLabel_7);
		
		JEditorPane dtrpnLutilisateurDevraConfigrer = new JEditorPane();
		dtrpnLutilisateurDevraConfigrer.setEditable(false);
		dtrpnLutilisateurDevraConfigrer.setText("L'utilisateur devra configrer son mot de passe lors de sa premi\u00E8re connexion. Par d\u00E9fault, son mot de passe sera 12345678");
		dtrpnLutilisateurDevraConfigrer.setBounds(139, 271, 250, 60);
		frame.getContentPane().add(dtrpnLutilisateurDevraConfigrer);
		
		textNom = new JTextField();
		textNom.setBounds(42, 66, 131, 20);
		frame.getContentPane().add(textNom);
		textNom.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(66, 109, 131, 20);
		frame.getContentPane().add(textEmail);
		
		textDDN = new JTextField();
		textDDN.setColumns(10);
		textDDN.setBounds(126, 154, 131, 20);
		frame.getContentPane().add(textDDN);
		
		textNDT = new JTextField();
		textNDT.setColumns(10);
		textNDT.setBounds(403, 66, 131, 20);
		frame.getContentPane().add(textNDT);
		
		textSalaire = new JTextField();
		textSalaire.setColumns(10);
		textSalaire.setBounds(323, 109, 131, 20);
		frame.getContentPane().add(textSalaire);
		
		textAdresse = new JTextField();
		textAdresse.setBounds(62, 207, 448, 20);
		frame.getContentPane().add(textAdresse);
		textAdresse.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Vendeur", "Comptabilit\u00E9", "RH", "Admin"}));
		comboBox.setBounds(359, 153, 131, 22);
		frame.getContentPane().add(comboBox);
		
		JButton btnSave = new JButton("Enregistrer");
		btnSave.setBounds(399, 308, 135, 23);
		frame.getContentPane().add(btnSave);
		
		JLabel lblNewLabel_8 = new JLabel("(YYYY-MM-dd)");
		lblNewLabel_8.setBounds(20, 171, 92, 14);
		frame.getContentPane().add(lblNewLabel_8);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection connect; // connexion

				Statement st; // Requete
				
				ResultSet rS; // Stock les resultat de la requete
				
				boolean save = true;
				
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					// Connection à la base de donnée
					String passwordDb = System.getProperty("database.password");
					connect = DriverManager.getConnection("jdbc:mysql://localhost/cybercar", "root", passwordDb);
					st = connect.createStatement();

					// Récupération des mails
				
					rS = st.executeQuery("SELECT email FROM `user`");
					
					ArrayList<String> emails = new ArrayList<String>();
					
					while (rS.next()) {
						emails.add(rS.getString(1));;// Recupere les emails existant
					}
					System.out.println(emails);
					String email = textEmail.getText().toLowerCase();
					for (String emailt : emails) {
						
						if (emailt.equalsIgnoreCase(email) ) {
							save = false;
							JOptionPane.showMessageDialog(null, "Email existe déjà");
						}
				
					}
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					System.err.println(e2);
				}
				
				
				try {
					
					double salary = Double.parseDouble(textSalaire.getText());
					
				} catch (Exception e2) {
					save = false;
				}
				
				String salary = textSalaire.getText();
				
				
				String email = textEmail.getText();
				String regex = "^(.+)@(.+)$";
				
				 
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(email);
				 if(!matcher.matches()) {
					 JOptionPane.showMessageDialog(null, "Format email invalide");
					 save = false;
				 }
				 
				String DDN = textDDN.getText();
					 
				 if(!check(DDN, "yyyy-MM-dd")) {
					 JOptionPane.showMessageDialog(null, "Format date invalide");
					 save = false;
				 }
				
				 String nom = textNom.getText();
				 
				 if (nom.length() > 20) {
					 JOptionPane.showMessageDialog(null, "Nom Trop long");
					save = false;
				}
				 
				String tel = textNDT.getText();
				
				if (tel.length() > 15) {
					JOptionPane.showMessageDialog(null, "Numéro Trop long");
						save = false;
				}
				 
				String adresse = textAdresse.getText();
				String [] dpts = {"Vendeur", "Comptabilité", "RH", "Admin"};
				String dpt = dpts[comboBox.getSelectedIndex()];
				
				 if (save) {
					String password = "12345678";
					
					password = text2hash(password, "MD5");
					
					
					

					try {
						String passwordDb = System.getProperty("database.password");
						connect = DriverManager.getConnection("jdbc:mysql://localhost/CyberCar", "root", passwordDb);
						st = connect.createStatement();
						
						st.execute("INSERT INTO `user`(`email`, `password`, `roles`, `dept`) VALUES ('"+ email +"','"+ password+"','1','"+dpt+"')");
						
						// Récupération des mails
						
						rS = st.executeQuery("SELECT id FROM `user` WHERE email = '"+email+"'");
						
						
						rS.next();
						int id = rS.getInt("id");// Recupere l'id
						System.out.println("ok");
						st.execute("INSERT INTO `user_detail`(`id`, `name`, `birthdate`, `tel_num`, `address`, `salary`) VALUES ('"+id+"','"+nom+"','"+DDN+"','"+tel+"','"+adresse+"','"+salary+"')");
						
						JOptionPane.showMessageDialog(null, "Utilisateur sauvgardé.");
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.err.println(e1);
					}finally {
						
					}
					
					
					
				}
				
			}
		});
	}
}
