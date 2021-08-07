package models;

import java.util.ArrayList;

public class Hero {
    private String name;
    private int age;
    private ArrayList<String> powers = new ArrayList<String>();
    private ArrayList<String> weaknesses = new ArrayList<String>();
    private static ArrayList<Hero> instances = new ArrayList<Hero>();

    public Hero(String name, int age, ArrayList<String> powers, ArrayList<String> weaknesses){
        this.name = name;
        this.age = age;
        this.powers = powers;
        this.weaknesses = weaknesses;
        instances.add(this);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<String> getPowers() {
        return powers;
    }

    public ArrayList<String> getWeaknesses() {
        return weaknesses;
    }

    public static ArrayList<Hero> getAll(){
        return instances;
    }

    public static void clearAllHeros(){
        instances.clear();
    }
}
