package global;

import global.storage.StorageHandler;

import java.io.IOException;

import javax.swing.SwingUtilities;

import GUI.Authentication;

public class StartUp {

	public static void main(String[] args) throws IOException {
		StorageHandler.loadAll();
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Authentication().setVisible(true);
			}
		});
	}
}
