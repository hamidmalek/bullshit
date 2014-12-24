package com.malek.hamid;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.malek.hamid.handlers.FoodListAdapter;
import com.malek.hamid.handlers.Group;

public class addFoodFragment extends DialogFragment {

	private final SparseArray<Group> foodGroup = new SparseArray<Group>();
	private Food food;
	private TextView foodName;
	private TextView foodCategory;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_add_foods, container,
				false);
		foodName = (TextView)rootView.findViewById(R.id.add_page_food_name);
		foodCategory = (TextView) rootView.findViewById(R.id.add_page_food_category);
		foodName.setText(food.getName());
		foodCategory.setText(FoodsFragment.foodCategories.get(food.getCategoryId()));
		return rootView;
	}

	public void createData() {
		for (int j = 0; j < 5; j++) {
			Group group = new Group("دسته " + j,j);
			foodGroup.append(j, group);
		}
	}
	
	public void setFood(Food food){
		this.food = food;
	}
}

