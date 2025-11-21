package com.example.surefitness;

public class SuggestedModel {
   String name,description;
   int imageUrl;

    public SuggestedModel(String name, String description, int imageUrl) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageUrl() {
        return imageUrl;
    }
}
