package com.example.dragonsden;

public class ListItem extends CloudItem{
    public String id;

    public ListItem(String _text, String _id, String _used, String _money,String _species){
        super(_text,_used,_money,_species);
        id = _id;
    }
}
