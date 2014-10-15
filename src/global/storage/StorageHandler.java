package global.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Car;
import model.Claim;
import model.Client;
import model.Employment;
import model.Payment;
import model.User;

public class StorageHandler {

	private static Map<String, User> users = new HashMap<>();
	private static Map<Integer, Client> clients = new HashMap<>();
	private static Map<Integer, Claim> claims = new HashMap<>();
	private static Map<Integer, Car> cars = new HashMap<>();
	private static Map<Integer, Payment> payments = new HashMap<>();


	public static void loadAll() throws IOException {
		loadUsers("storage/users.txt");
		loadCars("storage/cars.txt");
		loadClaims("storage/claims.txt");
		loadClients("storage/clients.txt");
		loadPayments("storage/payments.txt");
	}

	public static void loadUsers(String filename) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
		for (String line : lines) {
			String[] info = line.split("\t");
			User user = new User(info[0], info[1], Employment.values()[Integer.parseInt(info[2])], info[3], info[4]);
			users.put(user.getUsername(), user);
		}
	}


	public static void loadClients(String filename) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
		for (String line : lines) {
			String[] first = line.split(":");
			if (first.length != 3) {
				throw new IOException("Client file invalid");
			}
			String info = first[0];
			String claims = first[1];
			String cars = first[2];

			String[] claims1 = claims.split("\t");
			List<Claim> listClaim = new ArrayList<>();
			for (String cl : claims1) {
				Integer num = Integer.parseInt(cl);
				Claim kl = StorageHandler.claims.get(num);
				listClaim.add(kl);
			}

			String[] cars1 = cars.split("\t");
			List<Car> listCars = new ArrayList<>();
			for (String s : cars1) {
				Integer num = Integer.parseInt(s);
				listCars.add(StorageHandler.cars.get(num));
			}

			String[] in = info.split("\t");
			Client client = new Client(Integer.parseInt(in[0]), in[1], in[2], Integer.parseInt(in[3]), in[4], in[5],
					in[6]);

			for (int i = 0; i < listClaim.size(); i++) {
				client.addClaim(listClaim.get(i));
			}
			for (int i = 0; i < listCars.size(); i++) {
				client.addCar(listCars.get(i));
			}
			clients.put(client.getId(), client);
		}

	}

	public static void loadPayments(String filename) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
		for (String line : lines) {
			String[] info = line.split("\t");
			Client client = clients.get(Integer.parseInt(info[2]));
			boolean approved = info[4].equals("1") ? true : false;
			Payment payment = new Payment(Integer.parseInt(info[0]), info[1], client, Integer.parseInt(info[3]),
					approved);
			payments.put(payment.getId(), payment);
		}
	}

	public static void loadClaims(String filename) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
		for (String line : lines) {
			String[] info = line.split("\t");
			boolean complex = info[3].equals("1") ? true : false;
			Claim claim;
			Car car = cars.get(Integer.parseInt(info[5]));
			boolean decided = info[6].equals("1") ? true : false;
			boolean ok = info[7].equals("1") ? true : false;
			if (complex) {
				claim = new Claim(Integer.parseInt(info[0]), info[2], null, info[1], Integer.parseInt(info[4]), car,
						info[8], info[9], decided, ok);
			} else {
				claim = new Claim(Integer.parseInt(info[0]), info[2], null, info[1], Integer.parseInt(info[4]), car,
						decided, ok);
			}
			claims.put(claim.getId(), claim);
		}
	}

	public static void loadCars(String filename) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
		for (String line : lines) {
			String[] info = line.split("\t");
			Car car = new Car(Integer.parseInt(info[0]), null, info[1], Integer.parseInt(info[2]), info[3]);
			cars.put(car.getId(), car);
		}
	}


	public static Map<String, User> getUsers() {
		return users;
	}


	public static Map<Integer, Claim> getClaims() {
		return claims;
	}

	public static Map<Integer, Car> getCars() {
		return cars;
	}

	public static Map<Integer, Client> getClients() {
		return clients;
	}

	public static Map<Integer, Payment> getPayments() {
		return payments;
	}

	public static int getBiggestClaimId() {
		Integer id = -1;
		for (Claim cl : claims.values()) {
			if (cl.getId() > id) {
				id = cl.getId();
			}
		}
		return id;
	}
	
	public static int getBiggestPaymentId() {
		Integer id = -1;
		for (Payment pay : payments.values()) {
			if (pay.getId() > id) {
				id = pay.getId();
			}
		}
		return id;
	}

	public static void addNewClaim(Claim claim) {
		claims.put(claim.getId(), claim);
	}
	
	public static void addNewPayment(Payment payment) {
		payments.put(payment.getId(), payment);
	}

}
