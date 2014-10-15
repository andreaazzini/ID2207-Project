package GUI;

import global.storage.StorageHandler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	private int rows;

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
		setSize(400, 800);
		setLocation(350, 150);
		setTitle("Claim form");

		rows = severe ? 7 : 5;

		initGUI();
	}

	private void initGUI() {
		setLayout(new BorderLayout());

		JPanel parent = new JPanel();
		parent.setLayout(new GridLayout(rows, 1));
		
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
		

		addTextField(parent, nameField, client.getName() + " " + client.getSurname(), "Name", new JLabel(),
				false);
		addTextField(parent, carField, car.getName() + ", " + car.getPrice() + "SEK", "Car, price",
				new JLabel(), false);
		addTextField(parent, date, "", "Date", dateLabel, false);
		addTextField(parent, amount, costOfDamage.toString(), "Amount", amountLabel, false);
		addTextField(parent, text, "", "Text", textLabel, true);
		if (severe) {
			addTextField(parent, place, "", "Place", placeLabel, false);
			addTextField(parent, policeReport, "", "Police report", policeReportLabel, true);
		}

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
					Claim claim;
					if (!severe) {
						claim = new Claim(newId, date.getText(), client, text.getText(), Integer.parseInt(amount
								.getText()), car);
					} else {
						claim = new Claim(newId, date.getText(), client, text.getText(), Integer.parseInt(amount
								.getText()), car, place.getText(), policeReport.getText());
					}
					StorageHandler.addNewClaim (claim);
					pointer.dispose();
					JOptionPane.showMessageDialog(pointer, "Claim has been registered", "Claim registration", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	// area or field
	private void addTextField(JPanel parent, Object field, String text, String name, JLabel control, boolean area) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));

		JPanel up = new JPanel();

		JLabel label = new JLabel(name);
		
		control.setPreferredSize(new Dimension(250, 25));
		control.setSize(250, 25);
		control.setHorizontalAlignment(SwingConstants.CENTER);

		if (area) {
			JTextArea box = ((JTextArea) field); 
			box.setText(text);
			box.setSize(150, 70);
			box.setPreferredSize(new Dimension(125, 70));
			JScrollPane pane = new JScrollPane(box);
			up.add(label);
			up.add(pane);
		} else {
			JTextField box = ((JTextField) field); 
			box.setText(text);
			box.setSize(140, 25);
			box.setPreferredSize(new Dimension(125, 25));
			up.add(label);
			up.add(box);
		}

		panel.add(up);
		panel.add(control);
		control.setAlignmentX(CENTER_ALIGNMENT);

		parent.add(panel);
	}


}
