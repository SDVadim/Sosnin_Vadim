package org.example;

public class Herbivorous{
    public void eat(String name) {
        System.out.println(name + " ест " + new Grass().GetTypeMeal());
    }
}
