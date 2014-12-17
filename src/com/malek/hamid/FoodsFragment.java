package com.malek.hamid;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import com.malek.hamid.handlers.FoodListAdapter;
import com.malek.hamid.handlers.FoodSearchAdapter;
import com.malek.hamid.handlers.Group;

public class FoodsFragment extends Fragment {

	private ListView searcResult;
	private SearchView foodSearchView;
	private ExpandableListView foodList;
	private FoodSearchAdapter fsa;
	private final SparseArray<Group> foodGroup = new SparseArray<Group>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_foods, container,
				false);
		foodList = (ExpandableListView) rootView.findViewById(R.id.food_list);
		createData();
		FoodListAdapter adapter = new FoodListAdapter(getActivity(), foodGroup);
		foodList.setAdapter(adapter);
		
		foodSearchView = (SearchView) rootView.findViewById(R.id.search_bar_food);
		searcResult = (ListView) rootView.findViewById(R.id.search_result_food);
		fsa = new FoodSearchAdapter(getActivity(), R.layout.search_results);
		searcResult.setAdapter(fsa);
		foodSearchView.setOnQueryTextListener(new OnQueryTextListener() {

			public boolean onQueryTextSubmit(String query) {
				DatabaseHandler db = new DatabaseHandler(getActivity());
				if (query.length() > 1) {
					fsa.setDataList(db.getFoodList(query));
				}
				return true;
			}

			public boolean onQueryTextChange(String newText) {
				DatabaseHandler db = new DatabaseHandler(getActivity());
				if (newText.length() > 1) {
					fsa.setDataList(db.getFoodList(newText));
				}else{
					fsa.setDataList(new ArrayList<String>());
				}
				return true;
			}
		});
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
