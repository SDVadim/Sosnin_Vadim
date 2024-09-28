package org.example;

public class Eagle extends Predator implements IAmFly{
    @Override
    public void move() {
        System.out.println("Орел летит");
    }

    public void eat() {
        super.eat("Орел");
    }

}
