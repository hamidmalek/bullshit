package com.malek.hamid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.malek.hamid.handlers.FoodListAdapter;
import com.malek.hamid.handlers.Group;

public class FoodsFragment extends Fragment {
	
	private ExpandableListView foodList;
	 private final SparseArray<Group> foodGroup = new SparseArray<Group>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_foods, container, false);
		foodList = (ExpandableListView) rootView.findViewById(R.id.food_list);
//		foodList.setDivider(R.drawable.border);
	    createData();
	    FoodListAdapter adapter = new FoodListAdapter(getActivity(),
	        foodGroup);
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
