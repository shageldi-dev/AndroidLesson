package com.shageldi.androidlessons.Model;

public class GameModel {
    private int id;
    private String image;
    private String name;
    private String category;
    private Double star;

    public GameModel(int id, String image, String name, String category, Double star) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.category = category;
        this.star = star;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }
}
