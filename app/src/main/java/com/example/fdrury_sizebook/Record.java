package com.example.fdrury_sizebook;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by fred on 04/02/2017.
 */

public class Record {
    private String name;
    private String comment;
    private Date validDate;
    private Size neckSize = new Size("neck", "circumference in inches");
    private Size bustSize = new Size("bust", "circumference in inches");
    private Size chestSize = new Size("chest", "circumference in inches");
    private Size waistSize = new Size("waist", "circumference in inches");
    private Size hipSize = new Size("hip", "circumference in inches");
    private Size inseamSize = new Size("inseam", "circumference in inches");


    public Record(String name){
        this.name = name;
        this.validDate = new Date(); //current date
    }


    public void setValues(Size neckSize, Size bustSize, Size chestSize, Size waistSize, Size hipSize, Size inseamSize, String comment){
        this.neckSize = neckSize;
        this.bustSize = bustSize;
        this.chestSize = chestSize;
        this.waistSize = waistSize;
        this.hipSize = hipSize;
        this.inseamSize = inseamSize;
        this.comment = comment;
        this.validDate = new Date(); //current date
    }

    public void setValues(String name, Size neckSize, Size bustSize, Size chestSize, Size waistSize, Size hipSize, Size inseamSize, String comment, Date date){
        this.name = name;
        this.neckSize = neckSize;
        this.bustSize = bustSize;
        this.chestSize = chestSize;
        this.waistSize = waistSize;
        this.hipSize = hipSize;
        this.inseamSize = inseamSize;
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
