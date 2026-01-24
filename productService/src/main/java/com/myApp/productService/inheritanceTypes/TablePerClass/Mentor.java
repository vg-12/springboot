package com.myApp.productService.inheritanceTypes.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name = "tbc_mentors")
public class Mentor extends User {
    private String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
