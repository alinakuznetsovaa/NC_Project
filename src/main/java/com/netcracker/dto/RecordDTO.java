package com.netcracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcracker.model.Client;
import com.netcracker.model.Favour;
import com.netcracker.model.Master;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordDTO {

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private Integer id;
    private Client client;
    private Master master;
    private Favour favour;
    private String dateStart;
    private String dateEnd;


    @JsonIgnore
    public Date getSubmissionDateStartConverted() throws ParseException {
        return dateFormat.parse(this.dateStart);
    }

    public void setSubmissionDateStart(Date date) {
        this.dateStart = dateFormat.format(date);
    }

    @JsonIgnore
    public Date getSubmissionDateEndConverted() throws ParseException {
        return dateFormat.parse(this.dateEnd);
    }

    public void setSubmissionDateEnd(Date date) {
        this.dateEnd = dateFormat.format(date);
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

    public Favour getFavour() {
        return favour;
    }

    public void setFavour(Favour favour) {
        this.favour = favour;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
