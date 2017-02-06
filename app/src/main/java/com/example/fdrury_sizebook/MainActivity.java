package com.example.fdrury_sizebook;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

public class MainActivity extends Activity {

	/*our expandable adapter */
	ExpandableListAdapter expandableListAdapter;
	/*expandable list*/
	ExpandableListView expandableListView;
	/*list items*/
	ArrayList<Record> records = new ArrayList<Record>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.main);
    	/*generate data for list view*/
    	generateData();
    	/*instantiate adapter with our item list*/
    	expandableListAdapter=new ExpandableListAdapter(this, records);
    	/*we get list view*/
        expandableListView=(ExpandableListView) findViewById(R.id.expandableListView);
        /*set adapter to list view*/
        expandableListView.setAdapter(expandableListAdapter);

        Button newButton = (Button) findViewById(R.id.newButton);

        newButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    //Generate dummy data for list view
    public void generateData() {
    	Record testRecord = new Record("Fred");
        records.add(testRecord);
        testRecord = new Record("Kenz");
        records.add(testRecord);
        testRecord = new Record("Sarah");
        records.add(testRecord);
        testRecord = new Record("Mum");
        records.add(testRecord);
        testRecord = new Record("Dad");
        records.add(testRecord);
    }
}
