package com.myApp.productService.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class Product extends BaseModel {
    private String title;
    private Double price;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)//if we try to save the product object automatically it will save category obj as well
    //@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}) we can even use array of cascade types
    private Category category;

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
