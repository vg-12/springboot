package com.myApp.productService.inheritanceTypes.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name = "tbc_students")
public class Student extends User {
    private String batch;

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
}
