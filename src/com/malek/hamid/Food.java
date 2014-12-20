package com.malek.hamid;

public class Food {
	private int id;
	private String name;
	private int calorieSI;
	private int calorieUnit;
	private Category category;
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
			Category category) {
		this.id = id ;
		this.name = name;
		this.calorieSI = calorieSI;
		this.calorieUnit = calorieUnit;
		this.category = category;
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
	public Food(int id ,String name, int calorieSI, Category category) {
		this.id = id;
		this.name = name;
		this.calorieSI = calorieSI;
		this.category = category;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	/**
	 * enumeration of the category of the foods
	 * 
	 * @author hamid_
	 * 
	 */
	public enum Category {
		nuts, polo, candy, stew, misc, vegetables, fruit, juice;
		public String getName(){
			return this.toString();
		}
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
}
