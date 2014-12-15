package com.malek.hamid;

import java.util.ArrayList;

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

	// foods table columns names
	private static final String KEY_FOODNAME = "FoodName";
	private static final String KEY_UNIT = "Unit";
	private static final String KEY_ENERGY = "Energy";
	private static final String KEY_SIUNIT = "SiUnit";
	private static final String KEY_SIENERGY = "SiEnergy";
	private static final String KEY_CATEGORY = "Category";

	// userInfo table columns names
	private static final String KEY_SEX = "Sex";
	private static final String KEY_WEIGHT = "Weight";
	private static final String KEY_HEIGHT = "Height";
	private static final String KEY_BIRTHDATE = "BirthDate";
	private static final String KEY_FULFILLED = "Fulfilled";

	// food category enumeration
	private enum Category {
		nuts, polo, candy, stew, misc, vegetables, fruit, juice;
	}

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
				ret.add(cursor.getString(cursor.getColumnIndex(KEY_FOODNAME)));
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
		String query = "SELECT Fulfilled FROM userInfo";
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
}
