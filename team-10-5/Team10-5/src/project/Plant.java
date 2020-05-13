package project;

import java.util.ArrayList;

// NOTE: will not be needed once lab is complete
import java.util.Random;
public class Plant {
	String name, light , water, color,soil;
	int xlocation, ylocation;
	Season bloomtime;
	
	/**
	 * constructor
	 * @param name
	 * @param light
	 * @param water
	 * @param color
	 * @param soil
	 * @param season
	 */
	public Plant(String n, String l, String w, String c, String s) {
		name = n;
		light = l;
		water = w;
		color = c;
		bloomtime = Season.valueOf(s);
	}
	
	/**
	 * get name for the plant
	 * @return	name of the plant
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * set a name to the plant
	 * @param name	name of the plant
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * get light("strong","weak"...) of the plant
	 * @return	light condition for plant
	 */
	
	public String getLight() {
		return light;
	}
	/**
	 * set a light's condition to the plant
	 * @param light	light conditon for the plant
	 */
	
	public void setLight(String light) {
		this.light = light;
	}
	/**
	 * get water conditions to the plant(more, less...)
	 * @return	water condition for the plant
	 */
	
	public String getWater() {
		return water;
	}
	/**
	 * set water conditions to the plant
	 * @param water	water condition for plant
	 */
	
	public void setWater(String water) {
		this.water = water;
	}
	/**
	 * get color of the plant
	 * @return	color of the plant
	 */
	
	public String getColor() {
		return color;
	}
	/**
	 * set color of the plant
	 * @param color	color of the plant
	 */
	
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * get soil condition of the plant
	 * @return	soil condition
	 */
	
	public String getSoil() {
		return soil;
	}
	/**
	 * set soil condition to the plant
	 * @param soil	soil condition
	 */
	
	public void setSoil(String soil) {
		this.soil = soil;
	}
	/**
	 * get x location of the plant
	 * @return	x coordinate of the plant
	 */
	
	public int getXlocation() {
		return xlocation;
	}
	/**
	 * set x location to the plant
	 * @param xlocation	x coordinate of the plant
	 */
	
	public void setXlocation(int xlocation) {
		xlocation = xlocation;
	}
	/**
	 * get y location to the plant
	 * @return	the y coordinate of the plant
	 */
	
	public int getYlocation() {
		return ylocation;
	}
	
	/**
	 * set y location to the plant
	 * @param ylocation	y coordinate of the plant
	 */
	
	public void setYlocation(int ylocation) {
		this.ylocation = ylocation;
	}
	
	
	/**
	 * get the blooming time of the plant(each season)
	 * @return	name of the plant
	 */
	
	public Season getBloomtime() {
		return Season.valueOf(name);
	}
	
	
	/**
	 * set the blooming time of the plant(each season)
	 * @param bloomtime	bloomtime for plant
	 */
	
	public void setBloomtime(Season bloomtime) {
		this.bloomtime = bloomtime;
	}
	
	
}


