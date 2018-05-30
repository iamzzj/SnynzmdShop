package com.snynzmd.shop.entity;

import com.snynzmd.shop.widget.Indexable;

/**
 * Created by z on 2018/3/5.
 */

public class Assistant implements Indexable {
    private String name;
    private String sortLetters = "#";

    public Assistant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    @Override
    public String toString() {
        return "Assistant{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public String getIndex() {
        return sortLetters;
    }
}
