package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

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
		setLocation(200, 200);
		
		initGui();
		
	}

	private void initGui() {
		setLayout(new GridLayout(9, 2));
		
		add(new JLabel("id"));
		add(new JTextField(client.getId()));
		
		add(new JLabel("name"));
		add(new JTextField(client.getName()));
		
		add(new JLabel("Surname"));
		add(new JTextField(client.getSurname()));
		
		add(new JLabel("Age"));
		add(new JTextField(client.getAge()));
		
		add(new JLabel("Phone"));
		add(new JTextField(client.getPhone()));
		
		add(new JLabel("Email"));
		add(new JTextField(client.getEmail()));
		
		add(new JLabel("IBAN"));
		add(new JTextField(client.getIBAN()));
		
		add(new JLabel("Claims"));
		JButton claims = new JButton("See claims"); 
		add(claims);
		
		add(new JLabel("Claims"));
		JButton cars = new JButton("See cars"); 
		add(cars);
		
		claims.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new DisplayList<Claim>(client.getClaims()).setVisible(true);;
			}
		});
		
		cars.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new DisplayList<Car>(client.getCars()).setVisible(true);;
			}
		});
		
	}
}
