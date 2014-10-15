package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import model.Car;
import model.Claim;
import model.Client;

public class ClientView extends JFrame{

	private static final long serialVersionUID = 1L;

	private Client client;
	
	public ClientView(Client client) {
		this.client = client;
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(500, 500);
		setLocation(20, 300);
		setTitle("Client details");
		initGui();
		
	}

	private void initGui() {
		setLayout(new GridLayout(9, 2));
		
		
		addJLabel("Id");
		addTextField(String.valueOf(client.getId()));
		
		addJLabel("Name");
		addTextField(client.getName());
		
		addJLabel("Surname");
		addTextField(client.getSurname());
		
		addJLabel("Age");
		addTextField(String.valueOf(client.getAge()));
		
		addJLabel("Phone");
		addTextField(client.getPhone());
		
		addJLabel("Email");
		addTextField(client.getEmail());
		
		addJLabel("IBAN");
		addTextField(client.getIBAN());
		
		addJLabel("Claims");
		JButton claims = new JButton("See claims"); 
		add(claims);
		
		addJLabel("Cars");
		JButton cars = new JButton("See cars"); 
		add(cars);
		
		claims.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new DisplayList<Claim>(client.getClaims(), client.getName() +" " +
						client.getSurname() + "'s claims").setVisible(true);;
			}
		});
		
		cars.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new DisplayList<Car>(client.getCars(), "Cars").setVisible(true);;
			}
		});
		
	}
	
	protected static void addTextFieldToFrame (JFrame frame, String content) {
		Border border = BorderFactory.createLineBorder(Color.lightGray, 1);
		JTextField text = new JTextField(content);
		text.setHorizontalAlignment(JTextField.CENTER);
		text.setBackground(Color.gray);
		text.setBorder(border);
		text.setEditable(false);
		frame.add(text);
	}
	
	protected static void addJLabelToFrame (JFrame frame, String content) {
		JLabel label = new JLabel(content);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		frame.add(label);
	}
	
	private void addTextField (String content) {
		addTextFieldToFrame(this, content);
	}
	
	private void addJLabel (String content) {
		addJLabelToFrame(this, content);
	}
}
