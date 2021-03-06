package com.example.fdrury_sizebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * MainActivity controls the main layout page of the app
 */
public class MainActivity extends Activity {
    private static final String FILENAME = "file.sav";

	/*our expandable adapter */
	ExpandableListAdapter expandableListAdapter;
	/*expandable list*/
	ExpandableListView expandableListView;
	/*list items*/
	ArrayList<Record> records = new ArrayList<Record>();

    public void addRecord(Record newRecord){
        records.add(newRecord);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.main);
    	/*generate data for list view*/
        loadFromFile();
    	/*instantiate adapter with our item list*/
    	expandableListAdapter=new ExpandableListAdapter(this, records);
    	/*we get list view*/
        expandableListView=(ExpandableListView) findViewById(R.id.expandableListView);
        /*set adapter to list view*/
        expandableListView.setAdapter(expandableListAdapter);

        updateCounter();

        Button newButton = (Button) findViewById(R.id.newButton);

        newButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    public void updateCounter(){
        TextView recordCounter = (TextView)findViewById(R.id.textView);
        recordCounter.setText("# of records: " + records.size());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                Record newRecord = (Record) data.getSerializableExtra("newRecord");
                records.add(newRecord);
                expandableListAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * Loads records from specified file.
     * @exception FileNotFoundException if the file is not created first.
     */
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            //2017-01-24 18:19
            Type listType = new TypeToken<ArrayList<Record>>(){}.getType();
            records = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            records = new ArrayList<Record>();
        } catch (IOException e) {
            // TODO Handle the Exception properly later
            throw new RuntimeException(); //crashes app
        }
    }
}
