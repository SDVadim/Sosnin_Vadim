package org.example;

public class Camel extends Herbivorous implements MoveOnLand{
    @Override
    public void move() {
        System.out.println("Верблюд ходит");
    }

    public void eat() {
        super.eat("Верблюд");
    }
}
