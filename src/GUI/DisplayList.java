package GUI;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.ListDataListener;

import model.Claim;
import model.Client;
import model.Payment;

public class DisplayList<E> extends JFrame {

	private static final long serialVersionUID = 1L;

	private List<E> list;

	public DisplayList(List<E> list) {

		this.list = list;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(500, 500);
		setLocation(100, 100);

		// depends
		setTitle("");

		initGUI();
	}

	private void initGUI() {
		setLayout(new BorderLayout());

		DisplayListModel<E> ourModel = new DisplayListModel<E>(list);
		JList<E> jlist = new JList<>(ourModel);
		JScrollPane scroll = new JScrollPane(jlist);

		jlist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				@SuppressWarnings("unchecked")
				JList<E> source = (JList<E>) e.getSource();
				int index = source.getSelectedIndex();

				if (!list.isEmpty()) {
					if (list.get(0) instanceof Claim) {
						Claim claim = (Claim) list.get(index);
						new ClaimView(claim).setVisible(true);
					}
					else if (list.get(0) instanceof Payment) {
						Payment payment = (Payment) list.get(index);
						new PaymentView(payment).setVisible(true);
					}
				}
			}
		});

		getContentPane().add(scroll, BorderLayout.CENTER);
	}


	public static class DisplayListModel<E> implements ListModel<E> {

		private List<E> list;
		private List<ListDataListener> listeners;

		public DisplayListModel(List<E> list) {
			this.list = list;
			listeners = new ArrayList<>();
		}

		@Override
		public int getSize() {
			return list.size();
		}

		@Override
		public E getElementAt(int index) {
			return list.get(index);
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

	}

	public static void main(String[] args) {
		final List<Claim> claims = new ArrayList<>();
		Client client = new Client("Name", "Surname", 20, "sdf@sdf", "0835548969", "0014HB");
		Client client2 = new Client("asdfsdf", "sfsdf", 18, "sdf@", "0735548969", "0014HB");
		claims.add(new Claim(client, "afsd", 452));
		claims.add(new Claim(client2, "sdaf", 4554));
		claims.add(new Claim(client2, "sdaf", 4554, "ansfjkandf", "police reports"));
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new DisplayList<Claim>(claims).setVisible(true);

			}
		});
	}

}
