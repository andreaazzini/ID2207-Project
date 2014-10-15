package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListDataListener;

import model.Claim;
import model.Payment;

public class DisplayList<E> extends JFrame {

	private static final long serialVersionUID = 1L;

	private List<E> list;

	public DisplayList(List<E> list, String title) {

		this.list = list;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocation(850, 300);

		setTitle(title);

		initGUI();
		pack();
	}

	private void initGUI() {
		setLayout(new BorderLayout());

		DisplayListModel<E> ourModel = new DisplayListModel<E>(list);
		JList<E> jlist = new JList<>(ourModel);
		jlist.setName("JList");
		jlist.setFont(new Font("Monospaced", Font.PLAIN, 14));
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


}
