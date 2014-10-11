package model;

import java.util.ArrayList;
import java.util.List;

public class Garage {
	private List<Car> notRepairedCars;
	private List<Car> repairedCars;

	public Garage() {
		notRepairedCars = new ArrayList<Car>();
		repairedCars = new ArrayList<Car>();
	}

	public List<Car> getNotRepairedCars() {
		return notRepairedCars;
	}

	public List<Car> getRepairedCars() {
		return repairedCars;
	}
	
	public void setRepaired(Car car) {
		notRepairedCars.remove(car);
		repairedCars.add(car);
	}
	
	public void setDelivered(Car car) {
		repairedCars.remove(car);
	}
}
