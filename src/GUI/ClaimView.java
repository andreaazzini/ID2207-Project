package GUI;

import global.session.SessionHandler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import model.Claim;
import model.Client;
import model.Employment;

public class ClaimView extends JFrame {

	private static final long serialVersionUID = 1L;

	final Claim claim;

	public ClaimView(Claim claim) {
		this.claim = claim;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(500, 500);
		setLocation(550, 300);
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
		south.setLayout(new GridLayout(3, 2));
		south.add(new JTextField("Amount: " + String.valueOf(claim.getAmount()) + "SEK"));
		south.add(new JTextField(claim.getDate().toString()));

		JButton ok = new JButton("OK");
		ok.setForeground(Color.green);
		JButton nok = new JButton("NOK");
		nok.setForeground(Color.red);
		south.add(ok);
		south.add(nok);

		JButton phoneGarage = new JButton("Phone the garage");
		south.add(phoneGarage);

		if (claim.isDecided()) {
			ok.setVisible(false);
			nok.setVisible(false);
		}

		final ClaimView pointer = this;
		
		JButton sendLetter = new JButton("Send letter");
		south.add(sendLetter);
		
		if (!claim.isDecided()) {
			sendLetter.setVisible(false);
		}
		
		
		
		sendLetter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Letter(claim.getClient()).setVisible(true);
			}
		});

		phoneGarage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(pointer, "Garage has been notified", "Garage",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (SessionHandler.getCurrentUser().getEmployment().equals(Employment.CLAIM_HANDLER_A)) {
					claim.setOK(true);
					displayMessage(pointer, "Claim has been approved");
					displayMessage(pointer, "Finance department has been notified");
					pointer.dispose();
					// make a new payment
				} else {
					warningMessage(pointer);
				}
			}
		});

		nok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (SessionHandler.getCurrentUser().getEmployment().equals(Employment.CLAIM_HANDLER_A)) {
					claim.setOK(false);
					displayMessage(pointer, "Claim has been rejected");
					pointer.dispose();
				} else {
					warningMessage(pointer);
				}
			}
		});

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
			left.setLayout(new GridLayout(4, 1));

			JLabel l1 = new JLabel("Text");
			l1.setBorder(border);
			l1.setHorizontalAlignment(SwingConstants.CENTER);
			JLabel l4 = new JLabel("Insurance type");
			l4.setBorder(border);
			l4.setHorizontalAlignment(SwingConstants.CENTER);
			JLabel l2 = new JLabel("Place");
			l2.setBorder(border);
			l2.setHorizontalAlignment(SwingConstants.CENTER);
			JLabel l3 = new JLabel("Police report");
			l3.setBorder(border);
			l3.setHorizontalAlignment(SwingConstants.CENTER);
			left.add(l1);
			left.add(l4);
			left.add(l2);
			left.add(l3);

			panel.setLayout(new GridLayout(4, 1));

			JTextField text = new JTextField(claim.getText());
			text.setEditable(false);
			text.setBorder(border2);
			text.setHorizontalAlignment(JTextField.CENTER);
			text.setBackground(Color.gray);
			panel.add(text);

			JTextField insurance = new JTextField(claim.getCar().getInsurance());
			insurance.setEditable(false);
			insurance.setBorder(border2);
			insurance.setHorizontalAlignment(JTextField.CENTER);
			insurance.setBackground(Color.gray);
			panel.add(insurance);


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
		} else {
			left.setLayout(new GridLayout(2, 1));
			JLabel l1 = new JLabel("Text");
			l1.setBorder(border);
			l1.setHorizontalAlignment(SwingConstants.CENTER);
			left.add(l1);
			JLabel l2 = new JLabel("Insurance type");
			l2.setBorder(border);
			l2.setHorizontalAlignment(SwingConstants.CENTER);
			left.add(l2);

			panel.setLayout(new GridLayout(2, 1));
			JTextField text = new JTextField(claim.getText());
			text.setBackground(Color.gray);
			text.setEditable(false);
			text.setBorder(border2);
			text.setHorizontalAlignment(JTextField.CENTER);
			panel.add(text);

			JTextField insurance = new JTextField(claim.getCar().getInsurance());
			insurance.setEditable(false);
			insurance.setBorder(border2);
			insurance.setHorizontalAlignment(JTextField.CENTER);
			insurance.setBackground(Color.gray);
			panel.add(insurance);

		}
		add(left, BorderLayout.WEST);
		add(panel, BorderLayout.CENTER);
	}

	private static void displayMessage(JFrame parent, String text) {
		JOptionPane.showMessageDialog(parent, text, "Claim evaluation", JOptionPane.INFORMATION_MESSAGE);
	}

	private static void warningMessage(JFrame parent) {
		JOptionPane.showMessageDialog(parent, "You are not allowed to evaluate the claim", "Claim evaluation",
				JOptionPane.ERROR_MESSAGE);
	}


}
