package com.example.expenses;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class ExpenseActivity extends Activity {

	private ArrayList<String> GroupData = null;
	private ArrayList<ArrayList<String>> ChildrenData = null;
	private ArrayAdapter<String> adapter = null;
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.expense);

		ArrayList<String> saved_arr = getIntent().getStringArrayListExtra(
				"saved");
		ArrayList<String> submitted_arr = getIntent().getStringArrayListExtra(
				"submitted");

		GroupData = new ArrayList<String>();
		GroupData.add("Saved Items");
		GroupData.add("Submitted Items");
		
		ChildrenData = new ArrayList<ArrayList<String>>();
		ChildrenData.add(saved_arr);
		ChildrenData.add(submitted_arr);
		
		ExpandableListView myExpandableListView = (ExpandableListView)findViewById(R.id.my_listview);   
		final ExpandableAdapter adp = new ExpandableAdapter();
        myExpandableListView.setAdapter(adp); 
        
        myExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //Nothing here ever fires
            	String ExpenseNumber = (String) adp.getChild(groupPosition, childPosition);
                Intent intent = new Intent();
                intent.putExtra("ExpenseNumber", ExpenseNumber);
                intent.setClass(ExpenseActivity.this, ShowActivity.class);
                ExpenseActivity.this.startActivity(intent);
                return true;
            }
        });

	}
	
	private class ExpandableAdapter extends BaseExpandableListAdapter {    
        @Override  
        public Object getChild(int groupPosition, int childPosition) {  
            return ChildrenData.get(groupPosition).get(childPosition);  
        }  
  
        @Override  
        public long getChildId(int groupPosition, int childPosition) {  
            return 0;  
        }  
  
        @Override  
        public View getChildView(int groupPosition, int childPosition,  
                boolean isLastChild, View convertView, ViewGroup parent) {  
            TextView myText = null;    
            if (convertView != null) {    
                myText = (TextView)convertView;    
                myText.setText(ChildrenData.get(groupPosition).get(childPosition));    
            } else {    
                myText = createView(ChildrenData.get(groupPosition).get(childPosition));    
            }   
            return myText;    

        }  
  
        @Override  
        public int getChildrenCount(int groupPosition) {  
            return ChildrenData.get(groupPosition).size();  
        }  
  
        @Override  
        public Object getGroup(int groupPosition) {  
            return GroupData.get(groupPosition);  
        }  
  
        @Override  
        public int getGroupCount() {  
            return GroupData.size();  
        }  
  
        @Override  
        public long getGroupId(int groupPosition) {  
            return 0;  
        }  
  
        @Override  
        public View getGroupView(int groupPosition, boolean isExpanded,  
                View convertView, ViewGroup parent) {  
            TextView myText = null;    
            if (convertView != null) {    
                myText = (TextView)convertView;    
                myText.setText(GroupData.get(groupPosition));    
            } else {    
                myText = createView(GroupData.get(groupPosition));    
            }    
            return myText;  
        }  
  
        @Override  
        public boolean hasStableIds() {  
            return false;  
        }  
  
        @Override  
        public boolean isChildSelectable(int groupPosition, int childPosition) {  
            return true;//enable click on child item  
        }    
        
        private TextView createView(String content) {    
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(      
                    ViewGroup.LayoutParams.FILL_PARENT, 80);      
            TextView myText = new TextView(ExpenseActivity.this);      
            myText.setLayoutParams(layoutParams);      
            myText.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);      
            myText.setPadding(80, 0, 0, 0);      
            myText.setText(content);    
            return myText;    
        }  
	}
}
