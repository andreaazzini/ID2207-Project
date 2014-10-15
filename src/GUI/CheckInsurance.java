package GUI;

import global.storage.StorageHandler;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import model.Client;

public class CheckInsurance extends JFrame {

	private static final long serialVersionUID = 1L;


	public CheckInsurance() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(300, 125);
		setLocation(300, 300);
		setTitle("Check insurance");

		initGUI();
	}


	private void initGUI() {
		setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));

		JLabel usr = new JLabel("Name");
		JLabel pass = new JLabel("Surname");
		final JTextField name = new JTextField();
		final JTextField surname = new JTextField();
		panel.add(usr);
		panel.add(name);
		panel.add(pass);
		panel.add(surname);
		add(panel, BorderLayout.CENTER);

		JButton check = new JButton("Check");
		add(check, BorderLayout.SOUTH);

		final CheckInsurance pointer = this;
		
		check.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<Client> clients = new ArrayList<>(StorageHandler.getClients().values());
				Client insured = getInsuredClient(name.getText(), surname.getText(), clients);
				if (insured == null) {
					JOptionPane.showMessageDialog(pointer, "Client is not insured", "No insurance",
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					new EvaluateClaim(insured).setVisible(true);
					pointer.dispose();
				}
			}
		});


	}

	private Client getInsuredClient(String name, String surname, List<Client> clients) {
		for (Client client : clients) {
			if (client.getName().equalsIgnoreCase(name) && client.getSurname().equalsIgnoreCase(surname)) {
				return client;
			}
		}
		return null;
	}

}
