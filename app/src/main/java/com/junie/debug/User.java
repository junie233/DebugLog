package com.junie.debug;

import java.io.Serializable;

/**
 * Created by niejun on 2017/2/28.
 */

public class User extends Man implements Serializable{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String name;
    private int age;

    public Apple getApple() {
        return apple;
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }

    private Apple apple;

}
