package com.snynzmd.shop.entity;

/**
 * Created by z on 2018/3/12.
 */

public class ShopProductChooseProductHeader {
    private String name;

    public ShopProductChooseProductHeader(String name) {
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
        return "ShopProductChooseProductHeader{" +
                "name='" + name + '\'' +
                '}';
    }
}
