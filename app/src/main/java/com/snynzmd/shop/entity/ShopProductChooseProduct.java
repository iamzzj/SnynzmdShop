package com.snynzmd.shop.entity;

/**
 * Created by z on 2018/3/12.
 */

public class ShopProductChooseProduct {
    private String name;
    private String price;

    public ShopProductChooseProduct(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ShopProductChooseProduct{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
