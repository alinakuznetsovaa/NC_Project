package com.netcracker.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;



@Entity
@Table(name = "record")
@Data
public class Record{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_start", nullable = false)
    private Date dateStart;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_end", nullable = false)
    private Date dateEnd;
}
