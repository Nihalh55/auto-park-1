package com.autopark.console;

import java.util.ArrayList;

/**
 * Created by gurupunskill on 3/16/18.
 */

public class Destination {
    private int id;
    private String name;

    public Destination(int ID, String Name){
        id = ID;
        name = Name;
    }

    public int getID(){
        return id;
    }

    public String getname(){
        return name;
    }

    public static ArrayList<Destination> getList(int n){
        ArrayList<Destination> list = new ArrayList<Destination>();
        String default_name = "Destination ";
        for (int i = 1; i < n; i++)
        {
            Destination dest = new Destination(i, default_name + String.valueOf(i));
            list.add(dest);
        }
        return list;
    }
}
