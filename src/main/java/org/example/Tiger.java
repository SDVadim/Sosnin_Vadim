package org.example;

public class Tiger extends Predator implements MoveOnLand{
    @Override
    public void move() {
        System.out.println("Тигр бежит");
    }

    public void eat() {
        super.eat_beef("Тигр");
    }
}
