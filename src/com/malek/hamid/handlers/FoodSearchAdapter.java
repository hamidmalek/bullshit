package com.malek.hamid.handlers;

import java.util.ArrayList;

import android.content.Context;
import android.widget.ArrayAdapter;

public class FoodSearchAdapter extends ArrayAdapter<String> {


	public FoodSearchAdapter(Context context, int resource) {
		super(context, resource);
		// TODO Auto-generated constructor stub
	}

	/**
	 *  this function updates the search results
	 * @param foods
	 */
	public void setDataList(ArrayList<String> foods){
		clear();
		addAll(foods);
		notifyDataSetChanged();
	}

}
