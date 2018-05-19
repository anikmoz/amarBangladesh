package com.example.anik.amarbangladesh.domain;

/**
 * Created by Anik Mazumder on 5/19/2018.
 */

public class Domain {

    private String id;
    private String name;
    private String story;
    private String image;
    private String publishDate;

    public Domain(String id, String name, String story, String image, String publishDate) {
        this.id = id;
        this.name = name;
        this.story = story;
        this.image = image;
        this.publishDate = publishDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStory() {
        return story;
    }

    public String getImage() {
        return image;
    }

    public String getPublishDate() {
        return publishDate;
    }
}
