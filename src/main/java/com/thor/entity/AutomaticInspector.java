package com.thor.entity;

import java.sql.Date;

/**
 * automatic inspector
 */
public class AutomaticInspector {
    private Integer id;
    private String model;
    private String ip;
    private String port;
    private Integer locationId;
    private Date date = new Date(0);

    public AutomaticInspector() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AutomaticInspector{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", locationId=" + locationId +
                ", date=" + date +
                '}';
    }
}
