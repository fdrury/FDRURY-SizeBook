package com.example.fdrury_sizebook;

import java.io.Serializable;

/**
 * Created by fred on 04/02/2017.
 */

public class Size implements Serializable {
    private String bodyPart;
    private String description;
    private float value;


    public Size(String bodyPart, String description){
        this.bodyPart = bodyPart;
        this.description = description;
        value = -1;
    }


    public String getBodyPart(){
        return bodyPart;
    }

    public String getDescription(){
        return description;
    }

    public double getValue(){
        return value;
    }

    public void setValue(float value){
        this.value = value;
    }

    @Override
    public String toString(){
        if(value == -1){
            return bodyPart + ": - " + this.getDescription();
        }
        return bodyPart + ": " + value + " " + this.getDescription();
    }
}
