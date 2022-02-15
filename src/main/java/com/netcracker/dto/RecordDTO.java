package com.netcracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcracker.model.Client;
import com.netcracker.model.Master;
import com.netcracker.model.Service;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class RecordDTO {

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("dd-MM-yyyy hh:mm");

    private Integer id;
    private Client client;
    private Master master;
    private Service service;
    private String  dateStart;
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
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
