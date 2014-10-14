package global.session;

import model.User;
import global.storage.StorageHandler;

public class SessionHandler {

	private static User currentUser;
	
	
	public static boolean authenticate (String username, String password) {
		if (!StorageHandler.getUsers().containsKey(username)) {
			return false;
		}
		boolean ret = StorageHandler.getUsers().get(username).getPassword().equals(password);
		if (ret) {
			currentUser = StorageHandler.getUsers().get(username);
		}
		return ret ;
	}


	public static User getCurrentUser() {
		return currentUser;
	}
	
	public static void logout () {
		currentUser = null;
	}
	
	
}
