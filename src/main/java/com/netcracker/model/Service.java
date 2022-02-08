package com.netcracker.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    public Service(Category category, String title, Double time) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Objects.equals(id, service.id) && Objects.equals(category, service.category) && Objects.equals(title, service.title) && Objects.equals(time, service.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, title, time);
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", time=" + time +
                '}';
    }
}
