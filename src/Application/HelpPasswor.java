package Application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.BorderLayout;

public class HelpPasswor {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpPasswor window = new HelpPasswor();
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
	public HelpPasswor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 179);
		frame.getContentPane().setLayout(null);
		
		JTextPane txtpnGfg = new JTextPane();
		txtpnGfg.setEditable(false);
		txtpnGfg.setText("Le mot de passe doit contenir  :\r\n\r\n\t* entre 8 et 16 caract\u00E8re.\r\n\r\n\t* au moins une majuscule, une minuscule et un chiffre.");
		txtpnGfg.setBounds(10, 32, 414, 86);
		frame.getContentPane().add(txtpnGfg);
	}
}
