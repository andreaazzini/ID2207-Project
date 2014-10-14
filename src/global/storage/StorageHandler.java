package global.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Client;
import model.Employment;
import model.User;

public class StorageHandler {

	private static Map<String, User> users = new HashMap<>();
	private static List<Client> clients = new ArrayList<>();
	
	
	public static void loadUsers (String filename) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
		for (String line : lines) {
			String[] info = line.split("\t");
			User user = new User(info[0], info[1], Employment.values()[Integer.parseInt(info[2])], info[3], info[4]);
			users.put(user.getUsername(), user);
		}
	}
	
	public static Map<String, User> getUsers () {
		return users;
	}
	
	
	public static void loadClients (String filename) {
		
	}
	
	
	
	
	
	
	
	
	
}
