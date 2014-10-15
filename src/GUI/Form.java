package GUI;

import global.storage.StorageHandler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import model.Car;
import model.Claim;
import model.Client;
import model.Payment;

public class Form extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField date;
	private JLabel dateLabel;
	private JTextField amount;
	private JLabel amountLabel;
	private JTextArea text;
	private JLabel textLabel;
	private JTextField place;
	private JLabel placeLabel;
	private JTextArea policeReport;
	private JLabel policeReportLabel;

	private int rows1;
	private int rows2;

	private Client client;
	private boolean severe;
	private Car car;
	private Integer costOfDamage;


	public Form(Client client, boolean severe, Car car, Integer costOfDamage) {
		this.client = client;
		this.severe = severe;
		this.car = car;
		this.costOfDamage = costOfDamage;

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(400, 680);
		setLocation(250, 250);
		setTitle("Claim form");

		rows1 = severe ? 5 : 4;
		rows2 = severe ? 2 : 1;

		initGUI();
		pack();
	}

	private void initGUI() {
		setLayout(new BorderLayout());

		JPanel parent1 = new JPanel();
		parent1.setLayout(new GridLayout(rows1, 1));
		JPanel parent2 = new JPanel();
		parent2.setLayout(new GridLayout(rows2, 1));

		JTextField nameField = new JTextField();
		nameField.setEditable(false);
		JTextField carField = new JTextField();
		carField.setEditable(false);


		amountLabel = new JLabel();
		dateLabel = new JLabel();
		textLabel = new JLabel();
		placeLabel = new JLabel();
		policeReportLabel = new JLabel();
		amountLabel.setForeground(Color.red);
		dateLabel.setForeground(Color.red);
		textLabel.setForeground(Color.red);
		placeLabel.setForeground(Color.red);
		policeReportLabel.setForeground(Color.red);
		amount = new JTextField();
		date = new JTextField();
		text = new JTextArea();
		place = new JTextField();
		policeReport = new JTextArea();


		addTextField(parent1, nameField, client.getName() + " " + client.getSurname(), "Name", new JLabel(), false);
		addTextField(parent1, carField, car.getName() + ", " + car.getPrice() + "SEK", "Car, price", new JLabel(), false);
		addTextField(parent1, date, "", "Date", dateLabel, false);
		addTextField(parent1, amount, costOfDamage.toString(), "Amount", amountLabel, false);
		if (severe) {
			addTextField(parent1, place, "", "Place", placeLabel, false);
		}
		addTextField(parent2, text, "", "Text", textLabel, true);
		if (severe) {
			addTextField(parent2, policeReport, "", "Police report", policeReportLabel, true);
		}
		
		JPanel parent = new JPanel();
		parent.setLayout(new GridLayout(2, 1));
		parent.add(parent1);
		parent.add(parent2);
		
		add(parent, BorderLayout.CENTER);

		JButton submit = new JButton("Submit");
		add(submit, BorderLayout.SOUTH);

		final Form pointer = this;

		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean done = true;
				boolean amOK = true;
				try {
					Integer.parseInt(amount.getText());
				} catch (NumberFormatException en) {
					amOK = false;
				}
				if (date.getText().isEmpty()) {
					dateLabel.setText("Fill in the date of the accident");
					done = false;
				}
				if (amount.getText().isEmpty() || !amOK) {
					amountLabel.setText("Fill in the wanted amount");
					done = false;
				}
				if (text.getText().isEmpty()) {
					textLabel.setText("Fill in the text of the claim");
					done = false;
				}
				if (severe && place.getText().isEmpty()) {
					placeLabel.setText("Fill in the place of the accident");
					done = false;
				}
				if (severe && policeReport.getText().isEmpty()) {
					policeReportLabel.setText("Fill in the police report");
					done = false;
				}

				if (done) {
					Integer newId = StorageHandler.getBiggestClaimId() + 1;
					Integer newPaymentId = StorageHandler.getBiggestPaymentId() + 1;
					Calendar c = Calendar.getInstance();
					String today = c.get(Calendar.DAY_OF_MONTH) + "." + (c.get(Calendar.MONTH)+1) + "."
							+ c.get(Calendar.YEAR) + ".";
					Claim claim;
					if (!severe) {
						claim = new Claim(newId, date.getText(), client, text.getText(), Integer.parseInt(amount
								.getText()), car);
					} else {
						claim = new Claim(newId, date.getText(), client, text.getText(), Integer.parseInt(amount
								.getText()), car, place.getText(), policeReport.getText());
					}
					StorageHandler.addNewClaim(claim);
					Payment payment = new Payment(newPaymentId, today, client, Integer.parseInt(amount.getText()));
					StorageHandler.addNewPayment(payment);
					pointer.dispose();
					JOptionPane.showMessageDialog(pointer, "Claim has been registered", "Claim registration",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	// area or field
	private void addTextField(JPanel parent, Object field, String text, String name, JLabel control, boolean area) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JPanel up = new JPanel();

		JLabel label = new JLabel(name);

		JPanel labelWrap = new JPanel();
		control.setPreferredSize(new Dimension(250, 8));
		control.setSize(250, 8);
		control.setHorizontalAlignment(SwingConstants.CENTER);
		labelWrap.add(control);

		if (area) {
			JTextArea box = ((JTextArea) field);
			box.setText(text);
			box.setSize(250, 60);
			box.setPreferredSize(new Dimension(250, 60));
			JScrollPane pane = new JScrollPane(box);
			up.add(label);
			up.add(pane);
		} else {
			JTextField box = ((JTextField) field);
			box.setText(text);
			box.setSize(140, 20);
			box.setPreferredSize(new Dimension(125, 20));
			up.add(label);
			up.add(box);
		}


		panel.add(up, BorderLayout.CENTER);
		panel.add(labelWrap, BorderLayout.SOUTH);
		parent.add(panel);
	}


}
