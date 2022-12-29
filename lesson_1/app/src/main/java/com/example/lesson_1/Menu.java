package com.example.lesson_1;

import android.content.Context;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Category {
    private String Name;
    private ArrayList<Meal> meals;
    private int MaxSize;
    private int Size;

    Category(String name, int size)
    {
        Name =name;
        meals=new ArrayList<Meal>();
        MaxSize=size;
        Size=0;
    }
    public void put(Meal meal){
        if(Size<=MaxSize)
            meals.add(meal);
            Size++;
    }
    public int GetSize(){
        return Size;
    }
    public String getName()
    {
        return Name;
    }


}
class Meal {
    Meal(String name, int cost, String Descr)
    {
        Name=name;
        Cost=cost;
        Description= Descr;

    }
    private String Name;
    private int Cost;
    private String Description;

    public String getName()
    {
        return Name;
    }
    public int getCost()
    {
        return Cost;
    }
    public String getDescription()
    {
        return Description;

    }


}

public class Menu {

    private BufferedReader br;
    private ArrayList<Category> categories;
    private Context fileContext;
    private int CategorySize=25;
    Menu(Context context)
    {
        fileContext = context;
    }

    public boolean Build(String configFilename)
    {
        try {
            boolean end=false;
            String s;
            br=new BufferedReader(new InputStreamReader(fileContext.openFileInput(configFilename)));
            int CatID=-1;
            int Cost=0;
            String Description=" ";
            s=br.readLine();
            if(s.compareTo("MENU")==0)
            while(!end)
            {
                s=br.readLine();
                char []dst=new char[s.length()];
                s.getChars(0, 0, dst, 0);
                if(dst[0]=='/')
                {
                    CatID++;
                    s.getChars(1, s.length(), dst, 0);
                    s=new String(dst);
                    if(s.compareTo("END")!=0) {
                        categories.add(new Category(s, CategorySize));
                    }
                    else
                    {
                        end=true;
                    }

                }
                else {

                    Cost=br.read();
                    Description=br.readLine();

                    if(CatID>=0)
                    categories.get(CatID).put(new Meal(s, Cost, Description));
                }


            }
            return true;
        }
        catch (FileNotFoundException e)
        {
            return false;
        } catch (IOException e) {
            return false;
        }

    }
    public Category GetCategory(int i)
    {
        return categories.get(i);
    }

}
