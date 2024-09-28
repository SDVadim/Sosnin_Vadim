package org.example;

public class Dolphin extends Predator implements MoveInWater {

    @Override
    public void move() {
        System.out.println("Дельфин плывет");
    }

    public void eat() {
        super.eat_fish("Дельфин");
    }


}
