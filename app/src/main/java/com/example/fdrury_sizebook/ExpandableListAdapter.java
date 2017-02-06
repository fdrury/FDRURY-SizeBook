
package com.example.fdrury_sizebook;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;

/**
 * @author Sanath Nandasiri
 * this is expandable list adapter
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {
	
	LayoutInflater inflater;
	
	/*list of group */
	private ArrayList<Record> records;
	public ExpandableListAdapter(Context context,ArrayList<Record> records) {
	super();
		this.records = records;
		inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

    public String getChild(int groupPosition, int childPosition) {
		ArrayList<String> ch= records.get(groupPosition).getValues();
		return ch.get(childPosition);
	}
    
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

	@Override
	public int getChildrenCount(int groupPosition) {
		return 8;
	}
	
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
            View convertView, ViewGroup parent) {
    	String child= (String) getChild(groupPosition,childPosition);
    	TextView childName=null;
    	if(convertView==null) {
      	  convertView=inflater.inflate(R.layout.child_view, null);
      	   
      	}
    	childName=(TextView) convertView.findViewById(R.id.textViewChildName);
    	childName.setText(child);
		return convertView;
    }
    public Record getGroup(int groupPosition) {
        return records.get(groupPosition);
    }

    public void removeGroup(int groupPosition){
        records.remove(groupPosition);
    }

    public int getGroupCount() {
        return records.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {
        TextView groupName = null;
    	Record group=(Record) getGroup(groupPosition);
    	if(convertView==null) {
    	    //convertView=inflater.inflate(R.layout.group_view, null);
            convertView=inflater.inflate(R.layout.group_view, null);
            Button deleteButton = (Button) convertView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //setResult(RESULT_OK);
                    removeGroup(groupPosition);
                    notifyDataSetChanged();
                }
            });
    	}
    	groupName=(TextView) convertView.findViewById(R.id.textViewGroupName);
    	groupName.setText(group.getRecordName());
        return convertView;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public boolean hasStableIds() {
        return true;
    }
}
