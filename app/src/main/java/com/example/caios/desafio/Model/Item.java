package com.example.caios.desafio.Model;

import java.io.Serializable;

/**
 * Created by caios on 6/7/16.
 */

@SuppressWarnings("serial")
public class Item implements Serializable {
    private int id;
    private String name;
    private String full_name;
    private GitHub owner;
    private String description;

    public Item() {
    }

    public String getName() {
        return name;
    }

    public GitHub getOwner() {
        return owner;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return full_name;
    }

    public String getDescription() {
        return description;
    }
}