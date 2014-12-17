package com.malek.hamid;

import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.astuetz.PagerSlidingTabStrip.IconTabProvider;
import com.malek.hamid.handlers.FoodListAdapter;
import com.malek.hamid.handlers.Group;

public class addFoodFragment extends DialogFragment {

	private ExpandableListView foodList;
	private final SparseArray<Group> foodGroup = new SparseArray<Group>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_foods, container,
				false);
		foodList = (ExpandableListView) rootView.findViewById(R.id.food_list);
		// foodList.setDivider(R.drawable.border);
		createData();
		FoodListAdapter adapter = new FoodListAdapter(getActivity(), foodGroup);
		foodList.setAdapter(adapter);
		return rootView;
	}

	public void createData() {
		for (int j = 0; j < 5; j++) {
			Group group = new Group("دسته " + j);
			for (int i = 0; i < 5; i++) {
				group.children.add("زیردسته" + i);
			}
			foodGroup.append(j, group);
		}
	}
}
