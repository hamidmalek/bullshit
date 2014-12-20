package com.malek.hamid.handlers;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.malek.hamid.Food;
import com.malek.hamid.R;

public class FoodSearchAdapter extends BaseAdapter {

	private Activity activity;
	private static LayoutInflater inflater = null;
	private ArrayList<Food> data;

	public FoodSearchAdapter(Activity a, ArrayList<Food> d) {
		activity = a;
		data = d;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.search_results, null);

		TextView foodName = (TextView) vi.findViewById(R.id.food_name); // food
																		// name
		TextView foodCategory = (TextView) vi.findViewById(R.id.category); // food
																			// category
		ImageButton addFood = (ImageButton) vi
				.findViewById(R.id.add_food_in_search); // add food button
		Food food = data.get(position);

		foodName.setText(food.getName());
		foodCategory.setText(food.getCategory().getName());
		addFood.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(activity, "SALAM!!", Toast.LENGTH_SHORT).show();
			}
		});
		return vi;
	}

	public void setDataList(ArrayList<Food> foods) {
		data.clear();
		data.addAll(foods);
		notifyDataSetChanged();
	}

}
