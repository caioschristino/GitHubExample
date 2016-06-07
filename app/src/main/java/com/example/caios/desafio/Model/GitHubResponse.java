package com.example.caios.desafio.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caios on 6/7/16.
 */
public class GitHubResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Item> items;

    public GitHubResponse() {
        items = new ArrayList<>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
