package com.youtube.demo.ecoomerce;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class dataset {

    @Id
    private long id;

    @Column
    private long column;
}
