package GUI;

import global.session.SessionHandler;
import global.storage.StorageHandler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import model.Employment;
import model.Payment;

public class Authentication extends JFrame {

	private static final long serialVersionUID = 1L;

	public Authentication() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(320, 125);
		setLocation(550, 300);
		setTitle("Authentication");
		setResizable(false);
		
		
		initGUI();
	}

	private void initGUI() {
		setLayout(new BorderLayout());
		
		final JLabel label = new JLabel("");
		label.setForeground(Color.red);
		add(label, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		JLabel usr = new JLabel("Username");
		JLabel pass = new JLabel("Password");
		final JTextField usrText = new JTextField();
		usrText.setName("UsernameField");  /* ******************************/
		final JPasswordField passText = new JPasswordField();
		passText.setName("PasswordField"); /* **********************************/
		panel.add(usr);
		panel.add(usrText);
		panel.add(pass);
		panel.add(passText);
		add(panel, BorderLayout.CENTER);
		
		JButton login = new JButton("OK");
		login.setName("OK");
		
		final Authentication pointer = this;
		
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usrText.getText();
				String password = new String(passText.getPassword());
				boolean ok = SessionHandler.authenticate(username, password);
				if (ok) {
					pointer.dispose();
					if (!SessionHandler.getCurrentUser().getEmployment().equals(Employment.FINANACIAL_EMPLOYEE)) {
						new MainView().setVisible(true);
					}
					else {
						List<Payment> payments = new ArrayList<Payment>(StorageHandler.getPayments().values());
						new DisplayList<Payment>(payments, "All payments").setVisible(true);
					}
				}
				else {
					label.setText("Authentication failed, wrong username or password");
				}
			}
		});
		
		add(login, BorderLayout.SOUTH);
		
	}
	

}
