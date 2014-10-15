package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import model.Car;
import model.Claim;
import model.Client;

public class EvaluateClaim extends JFrame {

	private static final long serialVersionUID = 1L;

	private Client client;

	public EvaluateClaim(Client client) {
		this.client = client;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocation(600, 450);
		setTitle("Report");
		
		initGUI();
		pack();
	}

	private void initGUI() {
		setLayout(new BorderLayout());

		JLabel clientInfo = new JLabel(client.getName() + " " + client.getSurname());
		clientInfo.setHorizontalAlignment(SwingConstants.CENTER);
		add(clientInfo, BorderLayout.NORTH);

		JPanel center = new JPanel();
		center.setLayout(new GridLayout(3, 1));

		JPanel up = new JPanel();

		final JLabel price = new JLabel("");
		CarComboBoxModel ourModel = new CarComboBoxModel(client.getCars());
		final JComboBox<String> combo = new JComboBox<>(ourModel);
		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Integer index = combo.getSelectedIndex();
				price.setText(String.valueOf(client.getCars().get(index).getPrice()));
			}
		});

		up.add(combo);
		up.add(price);

		center.add(up);

		final EvaluateClaim pointer = this;
		
		JPanel wrap = new JPanel();
		
		JButton button = new JButton("See client's claims");
		wrap.add(button);
		center.add(wrap);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DisplayList<Claim>(client.getClaims(), client.getName() + " " + client.getSurname() + "'s claims")
						.setVisible(true);
			}
		});

		JPanel damage = new JPanel();
		JLabel damageText = new JLabel("Cost of damage");
		damage.add(damageText);
		final JTextField amount = new JTextField();
		amount.setPreferredSize(new Dimension(100, 25));
		damage.add(amount);
		
		center.add(damage);
		
		add(center, BorderLayout.CENTER);
		
		JPanel south = new JPanel();
		
		JButton severe = new JButton("Severe");
		JButton notSevere = new JButton("Not severe");
		
		south.add(severe);
		south.add(notSevere);
		
		add(south, BorderLayout.SOUTH);
		
		severe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				evaluateClaim(true, amount, pointer, combo, client);
			}
		});
		
		notSevere.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				evaluateClaim(false, amount, pointer, combo, client);
			}
		});

	}


	public static class CarComboBoxModel implements ComboBoxModel<String> {

		private List<ListDataListener> listeners;
		private List<Car> cars;
		private Object selectedItem;

		public CarComboBoxModel(List<Car> cars) {
			this.cars = cars;
			selectedItem = null;
			listeners = new ArrayList<>();
		}

		@Override
		public void addListDataListener(ListDataListener l) {
			if (!listeners.contains(l)) {
				listeners.add(l);
			}
		}

		@Override
		public void removeListDataListener(ListDataListener l) {
			listeners.remove(l);
		}

		@Override
		public String getElementAt(int index) {
			return cars.get(index).getName();
		}

		@Override
		public int getSize() {
			return cars.size();
		}

		@Override
		public void setSelectedItem(Object anItem) {
			selectedItem = anItem;
			for (ListDataListener l : listeners) {
				l.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, 0));
			}
		}

		@Override
		public Object getSelectedItem() {
			return selectedItem;
		}

	}
	
	private static void evaluateClaim (boolean severe, JTextField amount, JFrame parent, JComboBox<String> combo, Client client) {
		boolean wrong = false;
		Integer damage = null;
		if (!amount.getText().isEmpty()) {
			try {
				damage = Integer.parseInt(amount.getText());
			} catch (NumberFormatException e) {
				wrong = true;
			}
		}
		else {
			wrong = true;
		}
		Integer index = combo.getSelectedIndex();
		if (index == -1) {
			wrong = true;
		}
		
		if (wrong) {
			JOptionPane.showMessageDialog(parent, "Not all fields are correctly filled");
		}
		else {
			Car car = client.getCars().get(index);
			new Form(client, severe, car, damage).setVisible(true);
			parent.dispose();
		}
	}

}
