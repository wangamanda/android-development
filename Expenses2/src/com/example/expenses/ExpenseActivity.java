package com.example.expenses;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ExpenseActivity extends Activity {

	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.expense);

		ArrayList<String> arrayList = getIntent().getStringArrayListExtra(
				"arrayList");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(),
				android.R.layout.simple_expandable_list_item_1, arrayList);

		ListView lListView = (ListView) findViewById(R.id.my_listview);
		lListView.setAdapter(adapter);

	}
}
