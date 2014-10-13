package GUI;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

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
		setTitle("Payment details");
		
		initGUI();
	}

	private void initGUI() {
		setLayout(new GridLayout(6, 2));
		
		addJLabel("Id");
		addTextField(String.valueOf(payment.getId()));
		
		addJLabel("Client");
		addTextField(payment.getClient().getName() + " " + payment.getClient().getSurname());
		
		addJLabel("Date");
		addTextField(payment.getDate().toString());
		
		addJLabel("Amount");
		addTextField(String.valueOf(payment.getAmount()));
		
		addJLabel("IBAN");
		addTextField(payment.getIBAN());
		
		addJLabel("Approved");
		addTextField(payment.isApproved() ? "Approved" : "Not yet approved");
	}

	public static void main(String[] args) {
		final List<Payment> list = new ArrayList<>();
		Client client = new Client("Name", "Surname", 20, "name.surname@gmail.com", "0835548969", "0014HBIBAN");
		Client client2 = new Client("Anne", "Rosalinda", 24, "rosa@hotmail.com", "0735548969", "55555HB");
		list.add(new Payment(client, 3000));
		list.add(new Payment(client, 4000));
		list.add(new Payment(client2, 5000));
		Payment pay = new Payment(client2, 30393);
		pay.approve();
		list.add(pay);
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new DisplayList<Payment>(list, "Payments").setVisible(true);;
			}
		});
	}

	private void addTextField (String content) {
		ClientView.addTextFieldToFrame(this, content);
	}
	
	private void addJLabel (String content) {
		ClientView.addJLabelToFrame(this, content);
	}
}
