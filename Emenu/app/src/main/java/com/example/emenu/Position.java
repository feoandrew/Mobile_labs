package com.example.emenu;

import java.io.Serializable;

public class Position implements Serializable {
    Position(String name, int count, int cost) {
        Name = name;
        Count = count;
        Cost = cost;
    }

    private String Name;
    private int Count = 0;
    private int Cost = 0;

    public int getSum() {
        return Cost * Count;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public String getName() {
        return Name;
    }

    public void increase(int count) {
        Count += count;
    }

    public void decrease(int count) {
        Count -= count;
    }

    public int getCost() {
        return Cost;
    }

}
