package com.example.fdrury_sizebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by fred on 05/02/2017.
 *
 * This activity controls the editing layout and the input/output parsing
 */

public class EditActivity extends Activity {
    private EditText nameText;
    private DatePicker dateText;
    private EditText neckText;
    private EditText bustText;
    private EditText chestText;
    private EditText waistText;
    private EditText hipText;
    private EditText inseamText;
    private EditText commentsText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_values);

        Button saveButton = (Button) findViewById(R.id.saveButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);

        nameText = (EditText) findViewById(R.id.editName);
        dateText = (DatePicker) findViewById(R.id.editDate);
        neckText = (EditText) findViewById(R.id.editNeck);
        bustText = (EditText) findViewById(R.id.editBust);
        chestText = (EditText) findViewById(R.id.editChest);
        waistText = (EditText) findViewById(R.id.editWaist);
        hipText = (EditText) findViewById(R.id.editHip);
        inseamText = (EditText) findViewById(R.id.editInseam);
        commentsText = (EditText) findViewById(R.id.editComments);

        //the Intent extras will be the existing values when editing.
        //for new Records: (extras == null)
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Record existingRecord = (Record) extras.getSerializable("existingRecord");
            ArrayList<String> values = existingRecord.getRawValues();
            nameText.setText(values.get(0));
            if(Float.valueOf(values.get(2)) > 0) {
                neckText.setText(values.get(2));
            }
            if(Float.valueOf(values.get(3)) > 0) {
                bustText.setText(values.get(3));
            }
            if(Float.valueOf(values.get(4)) > 0) {
                chestText.setText(values.get(4));
            }
            if(Float.valueOf(values.get(5)) > 0) {
                waistText.setText(values.get(5));
            }
            if(Float.valueOf(values.get(6)) > 0) {
                hipText.setText(values.get(6));
            }
            if(Float.valueOf(values.get(7)) > 0) {
                inseamText.setText(values.get(7));
            }
            commentsText.setText(values.get(8));

            cancelButton.setText("Delete");
        }

        cancelButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent();
                Record newRecord = new Record(nameText.getText().toString());
                //the '"0" + ' protects against empty fields
                newRecord.setValues(nameText.getText().toString(), dateText.getYear() + "-" + dateText.getMonth() + "-" + dateText.getDayOfMonth(),
                        Float.valueOf("0" + neckText.getText().toString()), Float.valueOf("0" + bustText.getText().toString()),
                        Float.valueOf("0" + chestText.getText().toString()), Float.valueOf("0" + waistText.getText().toString()),
                        Float.valueOf("0" + hipText.getText().toString()), Float.valueOf("0" + inseamText.getText().toString()),
                        commentsText.getText().toString());
                intent.putExtra("newRecord",newRecord);

                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }
}
