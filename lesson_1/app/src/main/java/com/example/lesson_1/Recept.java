package com.example.lesson_1;

import java.util.List;
class Link
{
    private String Name;
    public int Count=0;
    public String getName()
    {
        return Name;
    }

}
public class Recept {
   private List<Link> list;
   private int sum;
    public String GetString(){
        String s = "";
        for(int i=0; i<list.size(); i++) {

            s = s+list.get(i).getName()+": ";
            char ch = (char)list.get(i).Count;
            s = s+ch+"\n";

        }
        s = s+"END\n\0";
        

        return s;

    }
    public int getSum()
    {
        return sum;
    }
}
