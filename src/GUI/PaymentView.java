package GUI;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import model.Client;
import model.Payment;

public class PaymentView extends JFrame {

	private static final long serialVersionUID = 1L;

	private Payment payment;
	
	public PaymentView(Payment payment) {
		this.payment = payment;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(500, 500);
		setLocation(200, 200);
		
		initGUI();
	}

	private void initGUI() {
		setLayout(new GridLayout(6, 2));
		
		add(new JLabel("id"));
		JTextField id = new JTextField(String.valueOf(payment.getId()));
		id.setEditable(false);
		add(id);
		
		add(new JLabel("Client"));
		JTextField client = new JTextField(payment.getClient().getName() + " " + payment.getClient().getSurname());
		client.setEditable(false);
		add(client);
		
		add(new JLabel("Date"));
		JTextField date = new JTextField(payment.getDate().toString());
		date.setEditable(false);
		add(date);
		
		add(new JLabel("Amount"));
		JTextField amount = new JTextField(String.valueOf(payment.getAmount()));
		amount.setEditable(false);
		add(amount);
		
		add(new JLabel("IBAN"));
		JTextField iban = new JTextField(payment.getIBAN());
		iban.setEditable(false);
		add(iban);
		
		add(new JLabel("Approved"));
		JTextField approved = new JTextField(payment.isApproved() ? "Approved" : "Not yet approved");
		approved.setEditable(false);
		add(approved);
	}

	public static void main(String[] args) {
		final List<Payment> list = new ArrayList<>();
		Client client = new Client("Name", "Surname", 20, "sdf@sdf", "0835548969", "0014HB");
		Client client2 = new Client("asdfsdf", "sfsdf", 18, "sdf@", "0735548969", "0014HB");
		list.add(new Payment(client, 3000));
		list.add(new Payment(client, 4000));
		list.add(new Payment(client2, 5000));
		Payment pay = new Payment(client2, 30393);
		pay.approve();
		list.add(pay);
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new DisplayList<Payment>(list).setVisible(true);;
			}
		});
		
	}
	
}
