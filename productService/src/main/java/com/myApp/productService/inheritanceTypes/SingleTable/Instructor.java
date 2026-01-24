package com.myApp.productService.inheritanceTypes.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity //table will not be created but because on parent class we are using Single table hence we need to let it know for which class the col needs to be created
@DiscriminatorValue(value = "3")
public class Instructor extends User {
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
