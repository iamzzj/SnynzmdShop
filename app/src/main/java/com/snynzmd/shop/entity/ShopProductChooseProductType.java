package com.snynzmd.shop.entity;

/**
 * Created by z on 2018/3/12.
 */

public class ShopProductChooseProductType {
    private String name;
    private boolean select;

    public ShopProductChooseProductType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    @Override
    public String toString() {
        return "ShopProductChooseProductType{" +
                "name='" + name + '\'' +
                ", select=" + select +
                '}';
    }
}
