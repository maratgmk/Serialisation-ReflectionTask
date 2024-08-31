package org.example.character;

import org.example.magic.method.Fight;
import org.example.magic.method.Heal;
import org.example.magic.method.Spell;

import java.util.concurrent.ThreadLocalRandom;

public class SuperHero implements Spell , Heal , Fight {
    private final String name;
    private int health;
    private int force;
    public boolean isAngry;



    public SuperHero(String name, int health, int force,boolean isAngry) {
        this.name = name;
        this.health = health;
        this.force = force;
        this.isAngry = isAngry;
    }

    public void introduce() {
        System.out.println("Hello, I'm " + name);
    }

    @Override
    public int fight() {
        return ThreadLocalRandom.current().nextInt(11);
    }

    @Override
    public void heal() {
        System.out.println("Live!");
    }
    @Override
    public void spell() {
        System.out.println("Die!");
    }
    public void flight(){
        if(health > 5) {
            System.out.println("I'm flying");
        }else {
            heal();
        }
    }
    public int eat(){
       return force++;
    }
    public void beat() {
        if (force > 5) {
            System.out.println("I'm fighting " + fight());
        } else {
            System.out.println("I need eat");
        }
    }
    public void checkMood() {
        if (isAngry) {
            spell();
        } else {
            heal();
        }
    }
    public void studyJava() {
        if (force > 17) {
            System.out.println("I can make solve Telran's Tasks without AI!");
        } else {
            System.out.println("I need eat more!");
        }
    }

    @Override
    public String toString() {
        return "SuperHero{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", force=" + force +
                ", isAngry=" + isAngry +
                '}';
    }
}
