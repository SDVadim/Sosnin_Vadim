package org.example;

public class Predator{
    public void eat(String name) {
        System.out.println(name +" ест "+ new Meat().GetTypeMeal());
    }

    public void eat_fish(String name) {
        System.out.println(name + " ест " + new Fish().GetTypeMeal());
    }

    public void eat_beef(String name) {
        System.out.println(name + " ест " + new Beef().GetTypeMeal());
    }
}
