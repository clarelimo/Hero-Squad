package models;

import java.util.ArrayList;

public class Squad {
    private int maxSize;
    private String name;
    private String cause;
    private static ArrayList<Squad> instance = new ArrayList<Squad>();

    public Squad(int maxSize, String name, String cause){
        this.maxSize = maxSize;
        this.name = name;
        this.cause = cause;
        instance.add(this);
    }

    public int getMaxSize() {
        return maxSize;
    }

    public String getName() {
        return name;
    }

    public String getCause() {
        return cause;
    }
    public static  ArrayList<Squad> getAll(){
        return instance;
    }
}
