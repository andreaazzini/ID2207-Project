package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import model.Claim;
import model.Client;

public class ClaimView extends JFrame{

	private static final long serialVersionUID = 1L;

	final Claim claim;
	
	public ClaimView(Claim claim) {
		this.claim = claim;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(500, 500);
		setLocation(100, 100);
		
		initGUI();
	}

	private void initGUI() {
		setLayout(new BorderLayout());
		JPanel north = new JPanel();
		north.setLayout(new GridLayout(1, 2));
		north.add(new JLabel("Claim #" + String.valueOf(claim.getId())));
		JButton clientButton = new JButton(claim.getClient().getName() + " " + claim.getClient().getSurname()); 
		north.add(clientButton);
		add(north, BorderLayout.NORTH);
		
		JPanel south = new JPanel();
		south.setLayout(new GridLayout(1, 2));
		south.add(new JTextField(String.valueOf(claim.getAmount())));
		south.add(new JTextField(claim.getDate().toString()));
		add(south, BorderLayout.SOUTH);
		
		initCenter(claim.isComplex());
		
		clientButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Client client = claim.getClient();
				new ClientView(client).setVisible(true);
			}
		});
	}

	private void initCenter(boolean complex) {
		JPanel panel = new JPanel();
		if (complex) {
			panel.setLayout(new GridLayout(3, 1));
			panel.add(new JTextField(claim.getText()));
			panel.add(new JTextField(claim.getPlace()));
			panel.add(new JTextField(claim.getPoliceReport()));
		}
		else {
			panel.setLayout(new GridLayout(1, 1));
			panel.add(new JTextField(claim.getText()));
		}
		add(panel, BorderLayout.CENTER);
	}
	
	
	
	
	
}
