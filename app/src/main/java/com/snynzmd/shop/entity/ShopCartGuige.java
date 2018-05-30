package com.snynzmd.shop.entity;

/**
 * Created by z on 2018/3/13.
 */

public class ShopCartGuige {
    private String name;

    public ShopCartGuige() {
        super();
    }

    public ShopCartGuige(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ShopCartGuige{" +
                "name='" + name + '\'' +
                '}';
    }
}
