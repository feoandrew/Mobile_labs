package com.example.emenu;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {


   private ArrayList<Position> order;
   private int TotalSum=0;


    public Order()
    {
        order=new ArrayList<Position>();
    }
    public Position getPosition(int index)
    {
        return order.get(index);
    }
    public int getSize()
    {
        return order.size();
    }

    public int getTotalSum()
    {

        return TotalSum;
    }
    public int refreshSum()
    {
        TotalSum = 0;
        for(Position p : order)
        {
            TotalSum = TotalSum+p.getCost()*p.getCount();
        }
        return TotalSum;
    }
    @SuppressLint("SuspiciousIndentation")
    public void put(String name, int count, int cost)
    {
        boolean contains=false;
        if(count>0) {
            TotalSum+=count*cost;
            for (Position pos:order)
            {
                if(pos.getName().compareTo(name)==0)
                {
                    contains=true;
                    pos.increase(count);
                }

            }
            if(!contains)
            order.add(new Position(name, count, cost));
        }

    }
    public void removePosition(int Id)
    {
        order.remove(Id);
        TotalSum=0;
        for(Position pos:order)
        {
            TotalSum+=pos.getSum();
        }
    }
}
