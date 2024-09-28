package org.example;

public class Horse extends Herbivorous implements MoveOnLand{

    @Override
    public void move() {
        System.out.println("Лошадь бежит");
    }

    public void eat() {
        super.eat("Лошадь");
    }
}
