package GUI;

import global.session.SessionHandler;
import global.storage.StorageHandler;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import model.Claim;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainView() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocation(600, 300);
		setTitle("Main");
		
		initGUI();
		pack();
	}

	private void initGUI() {
		setLayout(new BorderLayout());

		JPanel north = new JPanel();
		JLabel user = new JLabel(SessionHandler.getCurrentUser().getName() + " "
				+ SessionHandler.getCurrentUser().getSurname());
		JButton logout = new JButton("Logout");
		logout.setName("LogoutButton");
		
		final MainView pointer = this;
		
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean open = SessionHandler.getCurrentUser() == null;
				SessionHandler.logout();
				pointer.dispose();
				if (open) {
					new Authentication().setVisible(true);
				}
			}
		});
		
		north.add(user);
		north.add(logout);
		add(north, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		JButton viewClaims = new JButton("See claims");
		viewClaims.setName("ViewAllClaims");
		JButton registerNewClaim = new JButton("Register new claim");
		
		panel.add(viewClaims);
		panel.add(registerNewClaim);
		
		add(panel, BorderLayout.CENTER);
		
		viewClaims.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Claim> claims = new ArrayList<>(StorageHandler.getClaims().values());
				new DisplayList<Claim>(claims, "All claims").setVisible(true);
			}
		});
		
		registerNewClaim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new CheckInsurance().setVisible(true);
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				super.windowClosed(e);
				SessionHandler.logout();
				new Authentication().setVisible(true);
			}
		});
	}

}
