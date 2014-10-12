package model;

import java.util.ArrayList;
import java.util.List;

public class Garage {
	private List<CarTests> notRepairedCars;
	private List<CarTests> repairedCars;

	public Garage() {
		notRepairedCars = new ArrayList<CarTests>();
		repairedCars = new ArrayList<CarTests>();
	}

	public List<CarTests> getNotRepairedCars() {
		return notRepairedCars;
	}

	public List<CarTests> getRepairedCars() {
		return repairedCars;
	}
	
	public void setRepaired(CarTests car) {
		notRepairedCars.remove(car);
		repairedCars.add(car);
	}
	
	public void setDelivered(CarTests car) {
		repairedCars.remove(car);
	}
	
	public void addNotRepairedCar (CarTests car) {//
		notRepairedCars.add(car);
	}
}
