package com.myApp.productService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Category extends BaseModel {
    private String name;
    private String description;
//    @OneToMany- if we do like this then hibernate will treat this as a separate relation and this will add redundancy to it because in product class we have already define the relation
    @OneToMany(mappedBy = "category")//same var name which we have used in the product class for Category object type
    private List<Product> products; //I don't want to save it in the DB
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
