package com.netcracker.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "record")
public class Record{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    public Record() {
    }

    public Record(Client client, Master master, Service service, Date dateStart, Date dateEnd) {
        this.client = client;
        this.master = master;
        this.service = service;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(id, record.id) && Objects.equals(client, record.client) && Objects.equals(master, record.master) && Objects.equals(service, record.service) && Objects.equals(dateStart, record.dateStart) && Objects.equals(dateEnd, record.dateEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, master, service, dateStart, dateEnd);
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", client=" + client +
                ", master=" + master +
                ", service=" + service +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                '}';
    }
}
