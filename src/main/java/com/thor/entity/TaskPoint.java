package com.thor.entity;

import java.sql.Date;

/**
 * task point
 */
public class TaskPoint {
    private Integer id;
    private Integer inspectorId;
    private String rotationAngle;
    private String pitchAngle;
    /**
     * default duration is 5 second
     */
    private Integer duration = 5;
    /**
     * default temperature threshold is 50.0
     */
    private float temperatureThreshold = 50.0f;
    private Integer order;
    private Date date = new Date(0);

    public TaskPoint() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInspectorId() {
        return inspectorId;
    }

    public void setInspectorId(Integer inspectorId) {
        this.inspectorId = inspectorId;
    }

    public String getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(String rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public String getPitchAngle() {
        return pitchAngle;
    }

    public void setPitchAngle(String pitchAngle) {
        this.pitchAngle = pitchAngle;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public float getTemperatureThreshold() {
        return temperatureThreshold;
    }

    public void setTemperatureThreshold(float temperatureThreshold) {
        this.temperatureThreshold = temperatureThreshold;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TaskPoint{" +
                "id=" + id +
                ", inspectorId=" + inspectorId +
                ", rotationAngle='" + rotationAngle + '\'' +
                ", pitchAngle='" + pitchAngle + '\'' +
                ", duration=" + duration +
                ", temperatureThreshold=" + temperatureThreshold +
                ", order=" + order +
                ", date=" + date +
                '}';
    }
}
