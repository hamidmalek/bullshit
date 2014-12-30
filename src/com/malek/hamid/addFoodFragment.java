package com.malek.hamid;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.malek.hamid.handlers.Group;

public class addFoodFragment extends DialogFragment {

	private final SparseArray<Group> foodGroup = new SparseArray<Group>();
	private Food food;
	private TextView foodName;
	private TextView foodCategory;
	private TextView foodUnit;
	private Button insertFood;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_add_foods,
				container, false);
		foodName = (TextView) rootView.findViewById(R.id.add_page_food_name);
		foodCategory = (TextView) rootView
				.findViewById(R.id.add_page_food_category);
		foodUnit = (TextView) rootView.findViewById(R.id.unit_text1);
		insertFood = (Button) rootView.findViewById(R.id.insert_food_to_log);
		foodName.setText(food.getName());
		foodCategory.setText(FoodsFragment.foodCategories.get(food
				.getCategoryId()));
		foodUnit.setText("گرم");
		insertFood.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				DatabaseHandler db = new DatabaseHandler(getActivity());
				db.insertFoodinUserLog(food.getId(), 1000, "dateAdded__",
						"LogDate--", true);
			}

		});
		return rootView;
	}

	public void createData() {
		for (int j = 0; j < 5; j++) {
			Group group = new Group("دسته " + j, j);
			foodGroup.append(j, group);
		}
	}

	public void setFood(Food food) {
		this.food = food;
	}
}
