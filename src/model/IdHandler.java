package model;

public class IdHandler {

	private int currentId;

	public IdHandler() {
		currentId = 0;
	}

	public int get() {
		return currentId++;
	}
}
