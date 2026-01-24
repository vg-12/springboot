package com.myApp.productService.inheritanceTypes.JoinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "jt_students")
@PrimaryKeyJoinColumn(name="user_id")
public class Student extends User {
    private String batch;

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
}
