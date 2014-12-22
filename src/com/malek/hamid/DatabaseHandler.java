package com.malek.hamid;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseHandler extends SQLiteAssetHelper {

	// database version
	private static final int DATABASE_VERSION = 1;

	// database name
	private static final String DATABASE_NAME = "foodsv1";

	// tables name
	private static final String TABLE_FOOD = "foods";
	private static final String TABLE_USERINFO = "userInfo";
	private static final String TABLE_NUTRITION_LOG = "userNutritionLog";
	private static final String TABLE_FOOD_CATEGORIES = "foodCategories";

	// foods table columns names
	private static final String KEY_FOOD_NAME = "FoodName";
	private static final String KEY_FOOD_ID_FOOD_TABLE = "FoodId";
	private static final String KEY_UNIT = "Unit";
	private static final String KEY_ENERGY = "Energy";
	private static final String KEY_SIUNIT = "SiUnit";
	private static final String KEY_SIENERGY = "SiEnergy";
	private static final String KEY_CATEGORY = "Category";
	private static final String KEY_CATEGORY_ID = "CategoryId";
	private static final int FOOD_RESULT_LIMIT = 10;

	// userInfo table columns names
	private static final String KEY_SEX = "Sex";
	private static final String KEY_WEIGHT = "Weight";
	private static final String KEY_HEIGHT = "Height";
	private static final String KEY_BIRTHDATE = "BirthDate";
	private static final String KEY_FULFILLED = "Fulfilled";

	// user nutrition log table columns names
	private static final String KEY_FOOD_ID_LOG_TABLE = "foodId";
	private static final String KEY_DATE_ADDED = "DateAdded";
	private static final String KEY_SIZE = "size";

	// constructor
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO
		setForcedUpgrade(1);
	}

	public ArrayList<String> getData() {
		ArrayList<String> ret = new ArrayList<String>();
		String selectQuery = "SELECT  * FROM " + TABLE_FOOD;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				ret.add(cursor.getString(cursor.getColumnIndex(KEY_FOOD_NAME)));
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return ret;
	}

	/**
	 * 
	 * this function insert the information about user into database
	 * 
	 * @param weight
	 *            : the unit is centimeter
	 * @param height
	 *            : the unit is kilogram
	 * @param sex
	 *            : 0 means male and 1 means female
	 * @param birthDate
	 *            : birth date should be in format yyyy/mm/dd
	 * 
	 */
	public void setUserInfo(int weight, int height, int sex, String birthDate,
			int fulfilled) {
		String query = "INSERT INTO " + TABLE_USERINFO + "(" + KEY_WEIGHT + ","
				+ KEY_HEIGHT + "," + KEY_SEX + "," + KEY_BIRTHDATE + ","
				+ KEY_FULFILLED + ") values (" + "'" + weight + "'," + "'"
				+ height + "'," + "'" + sex + "'," + "'" + birthDate + "',"
				+ "'" + fulfilled + "');";
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(query);
		db.close();
	}

	/**
	 * @param person
	 *            : the object of the user that contains the info
	 */
	public void setuserInfo(Person person) {
		int sex = 0;
		if (person.getSex())
			sex = 1;
		setUserInfo(person.getWeight(), person.getHeight(), sex,
				person.getBirthday(), 1);
	}

	/**
	 * this function returns true if user filled his information in advance.
	 * 
	 * @return
	 */
	public boolean getUserFulfilled() {
		String query = "SELECT " + KEY_FULFILLED + " FROM " + TABLE_USERINFO;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(query, null);
		if (c.getCount() != 0) {
			c.moveToFirst();
			if (c.getString(0).equals("1")) {
				c.close();
				db.close();
				return true;
			} else {
				c.close();
				db.close();
				return false;
			}
		} else {
			c.close();
			db.close();
			return false;
		}
	}

	/**
	 * this function returns the list of foods in an array list
	 * 
	 * @return
	 */
	public ArrayList<Food> getFoodList() {
		ArrayList<Food> foods = new ArrayList<Food>();
		String query = "SELECT * FROM " + TABLE_FOOD;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(query, null);
		if (c.getCount() != 0) {
			c.moveToFirst();
			do {
				String name = c.getString(c.getColumnIndex(KEY_FOOD_NAME));
				// int id = c.getInt(c.getColumnIndex(KEY_FOOD_ID_FOOD_TABLE));
				String unit = c.getString(c.getColumnIndex(KEY_UNIT));
				int energy = c.getInt(c.getColumnIndex(KEY_ENERGY));
				String siUnit = c.getString(c.getColumnIndex(KEY_SIUNIT));
				int siEnergy = c.getInt(c.getColumnIndex(KEY_SIENERGY));
				System.out.println(name + unit + energy + siEnergy + siUnit);
			} while (c.moveToNext());
		}
		c.close();
		db.close();
		return foods;
	}

	/**
	 * insert food to database of user food log
	 * 
	 * @param food
	 *            : food is used for its id
	 * @param size
	 *            : size is used to calculate calorie
	 * @param dateAdded
	 *            : the time when food added to database
	 */
	public void insertFoodinUserLog(Food food, int size, String dateAdded) {
		String query = "INSERT INTO " + TABLE_NUTRITION_LOG + "(" + KEY_SIZE
				+ "," + KEY_DATE_ADDED + "," + KEY_FOOD_ID_LOG_TABLE
				+ ") values (" + "'" + size + "'," + "'" + dateAdded + "',"
				+ "'" + food.getId() + "');";
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(query);
		db.close();
	}

	/**
	 * 
	 * @param query
	 * @return
	 */
	public ArrayList<String> getFoodList(String query) {
		ArrayList<String> temp = new ArrayList<String>();
		String str = "SELECT * FROM " + TABLE_FOOD + " WHERE " + KEY_FOOD_NAME
				+ " LIKE '%" + query + "%' LIMIT " + FOOD_RESULT_LIMIT;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(str, null);
		if (c.getCount() != 0) {
			c.moveToFirst();
			do {
				String name = c.getString(c.getColumnIndex(KEY_FOOD_NAME));
				// int id = c.getInt(c.getColumnIndex(KEY_FOOD_ID_FOOD_TABLE));
				// String unit = c.getString(c.getColumnIndex(KEY_UNIT));
				// int energy = c.getInt(c.getColumnIndex(KEY_ENERGY));
				// String siUnit = c.getString(c.getColumnIndex(KEY_SIUNIT));
				// int siEnergy = c.getInt(c.getColumnIndex(KEY_SIENERGY));
				// System.out.println(name + unit + energy + siEnergy + siUnit);
				temp.add(name);
			} while (c.moveToNext());
		}
		c.close();
		db.close();
		return temp;
	}

	/**
	 * 
	 * @param query
	 * @return
	 */
	public ArrayList<Food> getFoodObjectList(String query) {
		query = query.trim();
		ArrayList<Food> temp = new ArrayList<Food>();
		String str = "SELECT * FROM " + TABLE_FOOD + " WHERE " + KEY_FOOD_NAME
				+ " LIKE '%" + query + "%' LIMIT " + FOOD_RESULT_LIMIT;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(str, null);
		if (c.getCount() != 0) {
			c.moveToFirst();
			do {
				String name = c.getString(c.getColumnIndex(KEY_FOOD_NAME));
				 int id = c.getInt(c.getColumnIndex(KEY_FOOD_ID_FOOD_TABLE));
				// String unit = c.getString(c.getColumnIndex(KEY_UNIT));
				 int energy = c.getInt(c.getColumnIndex(KEY_ENERGY));
				// String siUnit = c.getString(c.getColumnIndex(KEY_SIUNIT));
				// int siEnergy = c.getInt(c.getColumnIndex(KEY_SIENERGY));
				 int categoryId = c.getInt(c.getColumnIndex(KEY_CATEGORY_ID));
				// System.out.println(new Food(id,name,energy,categoryId));
				 temp.add(new Food(id, name,energy,categoryId ));
			} while (c.moveToNext());
		}
		c.close();
		db.close();
		return temp;
	}

	/**
	 * this function returns the categories of the foods
	 * 
	 * @return String of food Categories
	 */
	public HashMap<Integer, String> getFoodCategories() {
		SQLiteDatabase db = this.getWritableDatabase();
		HashMap<Integer, String> foodCategories = new HashMap<Integer, String>();
		String query = "SELECT * FROM "
				+ TABLE_FOOD_CATEGORIES;
		Cursor c = db.rawQuery(query, null);
		if (c.getCount() != 0) {
			c.moveToFirst();
			do {
				String name = c.getString(c.getColumnIndex(KEY_CATEGORY));
				int id = c.getInt(c.getColumnIndex(KEY_CATEGORY_ID));
				foodCategories.put(id, name);
			} while (c.moveToNext());
		}
		return foodCategories;
	}

	public ArrayList<Food> getFoods(int categoryId) {
		SQLiteDatabase db = this.getWritableDatabase();
		ArrayList<Food> foods = new ArrayList<Food>();
		String query = "SELECT * FROM " + TABLE_FOOD + " WHERE "
				+ KEY_CATEGORY_ID + "=" + categoryId;
		Cursor c = db.rawQuery(query, null);
		if (c.getCount() != 0) {
			c.moveToFirst();
			do {
				int id = c.getInt(c.getColumnIndex(KEY_FOOD_ID_FOOD_TABLE));
				String name = c.getString(c.getColumnIndex(KEY_FOOD_NAME));
				int calorie = c.getInt(c.getColumnIndex(KEY_SIENERGY));
				foods.add(new Food(id, name, calorie, categoryId));
			} while (c.moveToNext());
		}
		return foods;
	}
}
