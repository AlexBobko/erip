package com.erip.entity;

import java.time.LocalDateTime;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Document(collection = "customer")
public class Customer {

    @Id
    private ObjectId id;
    private String firstName;
    private String lastName;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime lastModified;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

//    public String getId() {
//        return id.toHexString();
//    }

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
            "Customer[id=%s, lastName='%s', thread='%s']", id, lastName, Thread.currentThread().getId());
    }

    /*@Override
    public String toString() {
        return String.format(
            "Customer[id=%s, firstName='%s', lastName='%s', createdAt='%s', lastModified='%s']",
            id, firstName, lastName, createdAt, lastModified);
    }*/
}
