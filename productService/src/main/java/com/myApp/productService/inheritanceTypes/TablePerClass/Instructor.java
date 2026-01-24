package com.myApp.productService.inheritanceTypes.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name = "tbc_instructors")
public class Instructor extends User {
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
