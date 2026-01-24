package com.myApp.productService.inheritanceTypes.JoinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "jt_instructors")
@PrimaryKeyJoinColumn(name="user_id")
public class Instructor extends User{
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
