package com.example.fdrury_sizebook;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by fred on 04/02/2017.
 */

public class Record implements Serializable {
    private String name;
    private String comment;
    private String validDate;
    private Size neckSize = new Size("neck", "circumference in inches");
    private Size bustSize = new Size("bust", "circumference in inches");
    private Size chestSize = new Size("chest", "circumference in inches");
    private Size waistSize = new Size("waist", "circumference in inches");
    private Size hipSize = new Size("hip", "circumference in inches");
    private Size inseamSize = new Size("inseam", "circumference in inches");


    public Record(String name){
        this.name = name;
    }


    public void setValues(String name, String date, float neckSize, float bustSize, float chestSize, float waistSize, float hipSize, float inseamSize, String comment){
        this.name = name;
        this.neckSize.setValue(neckSize);
        this.bustSize.setValue(bustSize);
        this.chestSize.setValue(chestSize);
        this.waistSize.setValue(waistSize);
        this.hipSize.setValue(hipSize);
        this.inseamSize.setValue(inseamSize);
        this.comment = comment;
        this.validDate = date;
    }

    public ArrayList<String> getValues(){
        ArrayList<String> values = new ArrayList<String>();
        values.add(validDate.toString());
        values.add(neckSize.toString());
        values.add(bustSize.toString());
        values.add(chestSize.toString());
        values.add(waistSize.toString());
        values.add(hipSize.toString());
        values.add(inseamSize.toString());
        values.add(comment);

        return values;
    }

    public String getRecordName(){
        return name;
    }
}
