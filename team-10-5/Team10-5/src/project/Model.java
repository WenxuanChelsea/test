package project;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

public class Model {
	private ArrayList<Plant> garden = new ArrayList<Plant>();
	private ArrayList<Plant> database = new ArrayList<Plant>();
	int length, width;

	/**
	 * the constructor of Model
	 * 
	 * @param Garden garden view
	 * @param Length garden length
	 * @param Width  garden width
	 */
//	public Model(ArrayList Garden, int Length, int Width) {
//		garden = Garden;
//		length = Length;
//		width = Width;
//	}
//	
	/**
	 * getter
	 * 
	 * @return ArrayList of garden
	 */
	public ArrayList<Plant> get_garden() {
		return garden;
	}

	/**
	 * initialize database
	 * 
	 */
	public void UpdatePlant() {
		try {
			File myObj = new File("bin/database/Plant.txt");
			Scanner scanner = new Scanner(myObj);

			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				String[] line = data.split(",");
				Plant newplant = new Plant(line[0], line[1], line[2], line[3], line[4]);
				database.add(newplant);
			}
			// String filename = scanner.next();
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public ArrayList<Plant> getDatabase() {
		return database;
	}

	/**
	 * update garden length
	 * 
	 * @param length length of the plant
	 * @return int length
	 */
	public void UpdateGardenlength(int length) {
		this.length = length;
	}

	/**
	 * update garden width
	 * 
	 * @param width
	 * @return int width
	 */
	public void UpdateGardenwidth(int width) {
		this.width = width;
	}

	/**
	 * get a list of possible plants
	 * 
	 * @param light  light condition of the plant
	 * @param water  water condition of the plant
	 * @param season season for the bloomtime
	 * @param Color  color for the plant
	 * @param soil   soil condition of the plant
	 * @return ArrayList List of the plants
	 */
	public ArrayList<Plant> SelectPlant(String light, String water, Season season, String color) {
		ArrayList<Plant> answer = new ArrayList<Plant>();

		for (Plant i : database) {
			if (i.light.equals(light) && i.water.equals(water) && i.bloomtime.equals(season) && i.color.equals(color)) {
				answer.add(i);
			}
		}
		
		return answer;
	}

	/**
	 * check if the drop location exceed the edge of the garden
	 * 
	 * @param locx x coordinate of the drop location
	 * @param locy y coordinate of the drop location
	 * @return boolean if it exceed the edge or not
	 */
	public boolean CompareLocation(int locx, int locy) {
		return true;
	}
}
