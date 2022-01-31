package com.netcracker.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "master")
@Data
public class Master {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "master_id", nullable = false)
    private Integer  id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private String email;

    private String adress;
}
