package com.netcracker.model;

import javax.persistence.*;

@Entity
@Table(name = "service")
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


    public Service() {
    }

    public Service(Integer id, Category category, String title, Double time) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }
}
