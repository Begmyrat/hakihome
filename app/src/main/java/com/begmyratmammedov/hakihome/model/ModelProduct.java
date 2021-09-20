package com.begmyratmammedov.hakihome.model;

public class ModelProduct {
    String title, tag, detail, price, imgURL;

    public ModelProduct() {}

    public ModelProduct(String title, String tag, String detail, String price, String imgURL) {
        this.title = title;
        this.tag = tag;
        this.detail = detail;
        this.price = price;
        this.imgURL = imgURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
