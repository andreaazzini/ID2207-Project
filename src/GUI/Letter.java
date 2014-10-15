package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import model.Client;

public class Letter extends JFrame {

	private static final long serialVersionUID = 1L;

	private Client client;

	public Letter(Client client) {
		this.client = client;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(400, 400);
		setLocation(300, 300);
		setTitle("Letter");

		initGUI();
	}

	private void initGUI() {
		setLayout(new BorderLayout());

		JLabel north = new JLabel("TO: " + client.getEmail() + " (" + client.getName() + " " + client.getSurname()
				+ ")");
		add(north, BorderLayout.NORTH);


		JTextArea letter = new JTextArea();
		add(letter, BorderLayout.CENTER);

		JButton send = new JButton("Send");
		add(send, BorderLayout.SOUTH);

		final Letter pointer = this;

		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(pointer, "Letter has been sent", "Letter",
						JOptionPane.INFORMATION_MESSAGE);
				pointer.dispose();

			}
		});

	}


}
