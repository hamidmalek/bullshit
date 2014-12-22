package com.malek.hamid;

public class Food {
	private int id;
	private String name;
	private int calorieSI;
	private int calorieUnit;
	private int categoryId;
	private Unit unit;

	/**
	 * 
	 @param name
	 *            : the food name
	 * @param calorieSI
	 *            : calorie of food per 100 gram
	 * @param category
	 *            : category of food which is from an enumeration provided in
	 *            this class
	 * @param unit
	 *            : non SI unit of the food such as glass, spoon, ... .
	 *            enumeration is provided in this class
	 * 
	 * @param category
	 */
	public Food(int id ,String name, int calorieSI, int calorieUnit, Unit unit,
			int categoryId) {
		this.id = id ;
		this.name = name;
		this.calorieSI = calorieSI;
		this.calorieUnit = calorieUnit;
		this.setCategoryId(categoryId);
		this.unit = unit;
	}

	/**
	 * 
	 * @param name
	 *            : the food name
	 * @param calorieSI
	 *            : calorie of food per 100 gram
	 * @param category
	 *            : category of food which is from an enumeration provided in
	 *            this class
	 */
	public Food(int id ,String name, int calorieSI, int categoryId) {
		this.id = id;
		this.name = name;
		this.calorieSI = calorieSI;
		this.categoryId = categoryId;
		this.unit = Unit.none;
		this.calorieUnit = -1;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public int getCalorieSI() {
		return calorieSI;
	}

	public void setCalorieSI(int calorieSI) {
		this.calorieSI = calorieSI;
	}

	public int getCalorieUnit() {
		return calorieUnit;
	}

	public void setCalorieUnit(int calorieUnit) {
		this.calorieUnit = calorieUnit;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCalorie() {
		return calorieSI;
	}

	public void setCalorie(int calorie) {
		this.calorieSI = calorie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	/**
	 * enumeration of the non SI units for foods
	 * 
	 * @author hamid_
	 * 
	 */
	public enum Unit {
		none, spoon, glass;
	}
	@Override
	public String toString() {
		return getName();
	}
}
