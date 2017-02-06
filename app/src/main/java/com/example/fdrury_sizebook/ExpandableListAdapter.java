
package com.example.fdrury_sizebook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * @originalauthor Sanath Nandasiri
 * @author Fred Drury
 * this is expandable list adapter
 * this class handles most of the UI.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private static final String FILENAME = "file.sav";
	LayoutInflater inflater;
    Context context;
	
	/*list of group */
	private ArrayList<Record> records;
	public ExpandableListAdapter(Context context,ArrayList<Record> records) {
	super();
		this.records = records;
		inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
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
		//There are always 8 fields. This shouldn't be hardcoded.
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
            final View myView = convertView;

            Button deleteButton = (Button) convertView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    removeGroup(groupPosition);
                    notifyDataSetChanged();

                }
            });

            Button editButton = (Button) convertView.findViewById(R.id.editButton);
            editButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(context instanceof MainActivity){
                        Intent intent = new Intent(context, EditActivity.class);
                        intent.putExtra("existingRecord",records.get(groupPosition));
                        records.remove(groupPosition);
                        ((MainActivity)context).startActivityForResult(intent, 1);
                    }
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

    @Override
    public void notifyDataSetChanged(){
        super.notifyDataSetChanged();
        if(context instanceof MainActivity){
            ((MainActivity)context).updateCounter();
        }
        saveInFile();
    }

    /**
     * Saves records to a specified file in JSON format.
     * @throws FileNotFoundException if file folder doesn't exist
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,
                    Context.MODE_PRIVATE); //MODE_PRIVATE is also '0'
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(records, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Handle the Exception properly later
            throw new RuntimeException(); //crashes app
        } catch (IOException e) {
            // TODO Handle the Exception properly later
            throw new RuntimeException(); //crashes app
        }
    }
}
