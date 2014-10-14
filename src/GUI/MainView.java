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
		setSize(250, 250);
		setLocation(400, 400);

		initGUI();
	}

	private void initGUI() {
		setLayout(new BorderLayout());

		JPanel north = new JPanel();
		JLabel user = new JLabel(SessionHandler.getCurrentUser().getName() + " "
				+ SessionHandler.getCurrentUser().getSurname());
		JButton logout = new JButton("Logout");
		
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
		panel.setLayout(new GridLayout(3, 1));
		JButton viewClaims = new JButton("See claims");
		JButton registerNewClaim = new JButton("Register new claim");
		JButton phoneGarage = new JButton("Phone the garage");
		
		panel.add(viewClaims);
		panel.add(registerNewClaim);
		panel.add(phoneGarage);
		
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
		
		phoneGarage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// open phone the garage window
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
