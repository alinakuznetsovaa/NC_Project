package com.netcracker.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "favour")
public class Favour {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "favour_id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String title;

    private Double time;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "masters_favours",
            joinColumns = @JoinColumn(name = "favour_id"),
            inverseJoinColumns = @JoinColumn(name = "master_id")
    )
    private List<Master> masters;


    public Favour() {
    }

    public Favour(Category category, String title, Double time, List<Master> masters) {
        this.category = category;
        this.title = title;
        this.time = time;
        this.masters = masters;
    }

    public List<Master> getMasters() {
        return masters;
    }

    public void setMasters(List<Master> masters) {
        this.masters = masters;
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
        Favour favour = (Favour) o;
        return Objects.equals(id, favour.id) && Objects.equals(category, favour.category) && Objects.equals(title, favour.title) && Objects.equals(time, favour.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, title, time);
    }

    @Override
    public String toString() {
        return "Favour{" +
                "id=" + id +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", time=" + time +
                '}';
    }
}
