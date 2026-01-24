package com.myApp.productService.inheritanceTypes.JoinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "jt_mentors")
@PrimaryKeyJoinColumn(name="user_id")
public class Mentor extends User {
    private String company;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
