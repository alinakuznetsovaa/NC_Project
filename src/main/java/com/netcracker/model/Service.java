package com.netcracker.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "service")
@Data
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id", nullable = false)
    private Integer  id;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String title;

    @Column(name = "s_time", nullable = false)
    private Double time;


}
