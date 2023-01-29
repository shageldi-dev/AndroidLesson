package com.shageldi.androidlessons.Model;

public class BannerItem {
    private String image;
    private Integer pos;

    public BannerItem(String image, Integer pos) {
        this.image = image;
        this.pos = pos;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }
}
