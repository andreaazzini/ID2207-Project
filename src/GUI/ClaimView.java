package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import model.Claim;
import model.Client;

public class ClaimView extends JFrame{

	private static final long serialVersionUID = 1L;

	final Claim claim;
	
	public ClaimView(Claim claim) {
		this.claim = claim;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(500, 500);
		setLocation(500, 300);
		setTitle("Claim details");
		
		initGUI();
	}

	private void initGUI() {
		setLayout(new BorderLayout());
		JPanel north = new JPanel();
		north.setLayout(new GridLayout(1, 2));
		north.add(new JLabel("Claim #" + String.valueOf(claim.getId())));
		JButton clientButton = new JButton(claim.getClient().getName() + " " + claim.getClient().getSurname());
		clientButton.setBackground(Color.lightGray);
		north.add(clientButton);
		add(north, BorderLayout.NORTH);
		
		JPanel south = new JPanel();
		south.setLayout(new GridLayout(1, 2));
		south.add(new JTextField("Amount: " + String.valueOf(claim.getAmount()) + "SEK" ));
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
		JPanel left = new JPanel();
		Border border = BorderFactory.createLineBorder(Color.gray, 1);
		Border border2 = BorderFactory.createLineBorder(Color.lightGray, 1);
		if (complex) {
			left.setLayout(new GridLayout(3, 1));
			
			JLabel l1 = new JLabel("Text");
			l1.setBorder(border);
			l1.setHorizontalAlignment(SwingConstants.CENTER);
			JLabel l2 = new JLabel("Place");
			l2.setBorder(border);
			l2.setHorizontalAlignment(SwingConstants.CENTER);
			JLabel l3 = new JLabel("Police report");
			l3.setBorder(border);
			l3.setHorizontalAlignment(SwingConstants.CENTER);
			left.add(l1);
			left.add(l2);
			left.add(l3);
			
			panel.setLayout(new GridLayout(3, 1));
			
			JTextField text = new JTextField(claim.getText());
			text.setEditable(false);
			text.setBorder(border2);
			text.setHorizontalAlignment(JTextField.CENTER);
			text.setBackground(Color.gray);
			panel.add(text);
			
			JTextField place = new JTextField(claim.getPlace());
			place.setEditable(false);
			place.setBorder(border2);
			place.setHorizontalAlignment(JTextField.CENTER);
			place.setBackground(Color.gray);
			panel.add(place);
			
			JTextField report = new JTextField(claim.getPoliceReport());
			report.setBorder(border2);
			report.setHorizontalAlignment(JTextField.CENTER);
			report.setEditable(false);
			report.setBackground(Color.gray);
			panel.add(report);
		}
		else {
			left.setLayout(new GridLayout(1, 1));
			JLabel l1 = new JLabel("Text");
			l1.setBorder(border);
			l1.setHorizontalAlignment(SwingConstants.CENTER);
			left.add(l1);
			
			panel.setLayout(new GridLayout(1, 1));
			JTextField text = new JTextField(claim.getText());
			text.setBackground(Color.gray);
			text.setEditable(false);
			text.setBorder(border2);
			text.setHorizontalAlignment(JTextField.CENTER);
			panel.add(text);
		}
		add(left, BorderLayout.WEST);
		add(panel, BorderLayout.CENTER);
	}
	
	
	
	
	
}
