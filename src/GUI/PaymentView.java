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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import model.Payment;

public class PaymentView extends JFrame {

	private static final long serialVersionUID = 1L;

	private Payment payment;
	
	public PaymentView(Payment payment) {
		this.payment = payment;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(500, 500);
		setLocation(200, 200);
		setTitle("Payment details");
		
		initGUI();
	}

	private void initGUI() {
		setLayout(new BorderLayout());
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(6, 2));
		
		addJLabel(center, "Id");
		addTextField(center, String.valueOf(payment.getId()));
		
		addJLabel(center, "Client");
		addTextField(center, payment.getClient().getName() + " " + payment.getClient().getSurname());
		
		addJLabel(center, "Date");
		final JTextField date = new JTextField(payment.getDate().toString());
		date.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
		date.setBackground(Color.lightGray);
		date.setHorizontalAlignment(SwingConstants.CENTER);
		center.add(date);
		
		addJLabel(center, "Amount");
		addTextField(center, String.valueOf(payment.getAmount()) + "SEK");
		
		addJLabel(center, "IBAN");
		addTextField(center, payment.getIBAN());
		
		addJLabel(center, "Approved");
		addTextField(center, payment.isApproved() ? "Approved" : "Not yet approved");
		
		add(center, BorderLayout.CENTER);
		
		JPanel south = new JPanel();
		south.setLayout(new GridLayout(1, 2));
		
		JButton button = new JButton("Approve");
		south.add(button);
		if (payment.isApproved()) {
			button.setVisible(false);
		}
		
		JButton changeDate = new JButton("Change date");
		south.add(changeDate);
		
		add(south, BorderLayout.SOUTH);

		final PaymentView pointer = this;
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				payment.approve();
				JOptionPane.showMessageDialog(pointer, "Payment has been approved", "Payment", JOptionPane.INFORMATION_MESSAGE);
				pointer.dispose();
			}
		});
		
		changeDate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				payment.setDate(date.getText());
				JOptionPane.showMessageDialog(pointer, "Date of payment changed", "Date", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}


	private void addTextField (JPanel panel, String content) {
		Border border = BorderFactory.createLineBorder(Color.lightGray, 1);
		JTextField text = new JTextField(content);
		text.setHorizontalAlignment(JTextField.CENTER);
		text.setBackground(Color.gray);
		text.setBorder(border);
		text.setEditable(false);
		panel.add(text);
	}
	
	private void addJLabel (JPanel panel, String content) {
		JLabel label = new JLabel(content);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label);
	}
}
