package com.thor.entity;

import java.sql.Blob;
import java.sql.Timestamp;

/**
 * automatic inspection record
 */
public class AutomaticInspectionRecord {
    private Integer id;
    private Integer inspectorId;
    private Integer taskPointId;
    private float temperature;
    private Blob photo;
    private Blob infraredPhoto;
    private Timestamp timestamp;

    public AutomaticInspectionRecord() {
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

    public Integer getTaskPointId() {
        return taskPointId;
    }

    public void setTaskPointId(Integer taskPointId) {
        this.taskPointId = taskPointId;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    public Blob getInfraredPhoto() {
        return infraredPhoto;
    }

    public void setInfraredPhoto(Blob infraredPhoto) {
        this.infraredPhoto = infraredPhoto;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "AutomaticInspectionRecord{" +
                "id=" + id +
                ", inspectorId=" + inspectorId +
                ", taskPointId=" + taskPointId +
                ", temperature=" + temperature +
                ", photo=" + photo +
                ", infraredPhoto=" + infraredPhoto +
                ", timestamp=" + timestamp +
                '}';
    }
}
