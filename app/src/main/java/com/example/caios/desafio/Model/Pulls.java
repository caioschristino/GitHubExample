package com.example.caios.desafio.Model;

/**
 * Created by caios on 6/7/16.
 */
public class Pulls {
    private String url;
    private int id;
    private String html_url;
    private String diff_url;
    private String patch_url;
    private String issue_url;
    private int number;
    private String state;
    private boolean locked;
    private String title;
    private User user;

    public Pulls() {
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public String getHtmlUrl() {
        return html_url;
    }

    public String getDiffUrl() {
        return diff_url;
    }

    public String getPatchUrl() {
        return patch_url;
    }

    public String getIssueUrl() {
        return issue_url;
    }

    public int getNumber() {
        return number;
    }

    public String getState() {
        return state;
    }

    public boolean isLocked() {
        return locked;
    }

    public String getTitle() {
        return title;
    }

    public User getUser() {
        return user;
    }
}
