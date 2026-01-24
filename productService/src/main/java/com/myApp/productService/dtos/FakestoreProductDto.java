package com.myApp.productService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakestoreProductDto {
//  DTO: data transfer object: obj that we use to transfer data between client and application and vice versa
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
