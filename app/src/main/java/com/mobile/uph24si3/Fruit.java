package com.mobile.uph24si3;

import java.io.Serializable;

public class Fruit implements Serializable {
    private String name;
    private String emoji; // Changed from int imageResId to String emoji
    private String origin;
    private String taste;
    private String description;
    private String benefits;

    public Fruit(String name, String emoji, String origin, String taste, String description, String benefits) {
        this.name = name;
        this.emoji = emoji;
        this.origin = origin;
        this.taste = taste;
        this.description = description;
        this.benefits = benefits;
    }

    public String getName() {
        return name;
    }

    public String getEmoji() {
        return emoji;
    }

    public String getOrigin() {
        return origin;
    }

    public String getTaste() {
        return taste;
    }

    public String getDescription() {
        return description;
    }

    public String getBenefits() {
        return benefits;
    }
}
